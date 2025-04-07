<script setup>
import doAjaxRequest from '@/util/util.js'
import {onMounted, reactive, ref} from 'vue'
import router from "@/router/index.js";

// Constantes de stockage
const listSalon = reactive([])
const selectedSalon = ref(null);
const editing = ref(false);
const editedName = ref("");

const ajouter = ref(false);

let data = reactive({
  // Les données saisies dans le formulaire
  formulaire: {
    nom: "",
    date: "",
    archive: ""
  },
})

// Fonctions

// ---------------- Fonctions de base --------------- //

function getSalon() {
  let premierTour = false;
  doAjaxRequest('/api/salons')
    .then((result) => {
      console.log(result._embedded)
      for (let elmnt of result._embedded.salons) {
        console.log(elmnt);
        if (premierTour == false) {
          selectedSalon.value = elmnt.idSalon
          premierTour = !premierTour
        }
        listSalon.push(elmnt)
      }
      console.log(listSalon)
    })
    .catch((error) => alert(error.message))
}

function handleModifAffich() {
  editing.value = true;
  const selected = listSalon.find(salon => salon.idSalon === selectedSalon.value);
  if (selected) {
    editedName.value = selected.nom;
  }
}

function handleAjoutAffich() {
  ajouter.value = !ajouter.value
}

function handleArchive() {
  if (confirm("Archiver ce salon rend les formulaires liés inaccessibles pour les utilisateurs. Êtes vous sûr de vouloir archiver ?")) {
    archiverSalon()
  }
  ;
}

// Fonction qui appelle la suppression des salons
function handleDelete() {
  if (confirm("Êtes vous sûr de vouloir supprimer ce salon ? Cela va entraîner la suppression de tous les prospects liés à ce salon")) {
    deleteSalon(selectedSalon.value)
  }
  ;
}

// ----------------- Fonctions CRUD ------------------- //

// Fonction qui permet l'archivage
function archiverSalon() {
  console.log(`Salon sélectionné : ${selectedSalon.value}`)
  const options = {
    method: 'PUT',
  }
  fetch(`/rest/archiver/${selectedSalon.value}`, options)
    .then((result) => {
      console.log(result);
      listSalon.length = 0;
      getSalon();
    })
    .catch((error) => alert(error.message));
}

// Fonction de delete des salons
function deleteSalon(id) {
  const options = {
    method: 'DELETE',
  }
  fetch(`/rest/deleteSalon/${id}`, options)
    .then(() => {
      // Met à jour la liste des salons
      listSalon.length = 0;
      getSalon();
    })
    .catch((error) => alert(error.message));
}

// Fonction ajout de salon
function ajouteSalon() {
  const options = {
    method: 'POST',
    body: JSON.stringify(data.formulaire),
    headers: {
      'Content-Type': 'application/json',
    },
  }
  console.log(JSON.stringify(data.formulaire))

  // On appelle l'API REST générée par les repositories Spring Data REST
  doAjaxRequest('/api/salons', options)
    .then((result) => {
      console.log('Salon ajouté :', result)

      // MAJ de la liste des salons
      listSalon.length = 0;
      getSalon();

      // MAJ de l'affichage du form d'ajout
      handleAjoutAffich();
      data.formulaire = {};
    })
    .catch((error) => alert(error.message))
}

// Fonction modif du nom du salon
function modificationSalon() {
  const options = {
    method: 'PUT',
    headers: {'Content-Type': 'application/json'},
    body: JSON.stringify({nom: editedName.value})
  };
  fetch(`/rest/modifNomSalon/${selectedSalon.value}`, options)
    .then(() => {
      editing.value = false;
      listSalon.find(salon => salon.idSalon === selectedSalon.value).nom = editedName.value;
    })
    .catch((error) => alert(error.message));
}

onMounted(() => getSalon())
</script>

<template>
  <div class="component-section">
    <h3>Salons</h3>
    <div v-if="editing" class="edit-container">
      <input v-model="editedName" type="text"/>
      <div class="action-buttons">
        <button @click="modificationSalon">Enregistrer</button>
        <button @click="editing = false">Annuler</button>
      </div>
    </div>
    <div v-else class="option-header">
      <select v-model="selectedSalon">
        <option v-for="salon of listSalon" :key="salon.idSalon" :value="salon.idSalon">
          {{ salon.nom }} <span v-if="salon.archive">(Archivé)</span>
        </option>
      </select>
      <div class="action-buttons">
        <button class="btn-modifier" @click="handleModifAffich">
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24"
               fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
               stroke-linejoin="round" class="icon-edit">
            <path d="M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7"></path>
            <path d="M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z"></path>
          </svg>
          Modifier
        </button>
        <button @click="handleArchive">Archiver</button>
        <button class="btn-delete" @click="handleDelete">
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24"
               fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
               stroke-linejoin="round" class="icon-delete">
            <polyline points="3 6 5 6 21 6"></polyline>
            <path
              d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"></path>
            <line x1="10" y1="11" x2="10" y2="17"></line>
            <line x1="14" y1="11" x2="14" y2="17"></line>
          </svg>
          Supprimer
        </button>
        <button class="btn-add" @click="handleAjoutAffich">Ajouter</button>
      </div>
    </div>
    <div v-if="ajouter" class="option-container">
      <div class="option-title">Ajouter un salon</div>
      <form @submit.prevent="ajouteSalon">
        <input v-model="data.formulaire.nom" type="text" placeholder="Nom du salon">
        <input v-model="data.formulaire.date" type="date">
        <div class="checkbox-container">
          <input type="checkbox" id="archive-checkbox" v-model="data.formulaire.archive">
          <label for="archive-checkbox" class="checkbox-label">Archiver ?</label>
        </div>
        <div class="action-buttons">
          <button type="submit">Ajouter</button>
          <button @click="handleAjoutAffich">Annuler</button>
        </div>
      </form>
    </div>
  </div>
</template>

<style scoped>
/* Import du CSS commun */
@import '../../assets/css/options.css';
</style>

<script setup>
import doAjaxRequest from "@/util/util.js";
import {onMounted, reactive, ref} from "vue";

// Constantes de stockage de données
const listFormation = reactive([])
const selectedFormation = ref(null);
const editing = ref(false);
const editedName = ref("");

const ajouter = ref(false);

let data = reactive({
  // Les données saisies dans le formulaire
  formation: {
    nom: "",
  }
})

// Fonctions

// ----------------- Fonctions basiques -------------------- //

function getFormation() {
  let premierTour = false;
  doAjaxRequest('/api/formations')
    .then((result) => {
      console.log(result._embedded)
      for (let elmnt of result._embedded.formations) {
        console.log(elmnt);
        if (premierTour == false) {
          selectedFormation.value = elmnt.id
          premierTour = !premierTour
        }
        listFormation.push(elmnt)
      }
      console.log(listFormation)
    })
    .catch((error) => alert(error.message))
}

function handleModif() {
  editing.value = true;
  const selected = listFormation.find(formation => formation.id === selectedFormation.value);
  if (selected) {
    editedName.value = selected.nom;
  }
}

function handleAjoutAffich() {
  ajouter.value = !ajouter.value
}

function handleDelete() {
  if (confirm("Êtes vous sûr de vouloir supprimer cette formation ? Les prospects liés à cette formation ne seront plus comptés dans les graphiques sans pour autant être supprimés")) {
    deleteFormation(selectedFormation.value)
  }
  ;
}

// ---------------- Fonctions CRUD ---------------- //

function ajouteFormation() {
  const options = {
    method: 'POST',
    body: JSON.stringify(data.formation),
    headers: {
      'Content-Type': 'application/json',
    },
  }
  console.log(JSON.stringify(data.formation))

  doAjaxRequest('/api/formations', options)
    .then((result) => {
      console.log('formation ajouté :', result)

      // MAJ de la liste des salons
      listFormation.length = 0;
      getFormation();

      // MAJ de l'affichage du form d'ajout
      handleAjoutAffich();
      data.formation = {};
    })
    .catch((error) => alert(error.message))
}

function deleteFormation(id) {
  const options = {
    method: 'DELETE',
  }
  fetch(`/rest/deleteFormation/${id}`, options)
    .then(() => {
      // Met à jour la liste des salons
      listFormation.length = 0;
      getFormation();
    })
    .catch((error) => alert(error.message));
}

function handleSave() {
  const options = {
    method: 'PUT',
    headers: {'Content-Type': 'application/json'},
    body: JSON.stringify({nom: editedName.value})
  };
  fetch(`/rest/modifNomFormation/${selectedFormation.value}`, options)
    .then(() => {
      editing.value = false;
      listFormation.find(formation => formation.id === selectedFormation.value).nom = editedName.value;
    })
    .catch((error) => alert(error.message));
}

onMounted(() => getFormation())
</script>

<template>
  <div class="component-section">
    <h3>Formations</h3>
    <div v-if="editing" class="edit-container">
      <input v-model="editedName" type="text"/>
      <div class="action-buttons">
        <button @click="handleSave">Enregistrer</button>
        <button @click="editing = false">Annuler</button>
      </div>
    </div>
    <div v-else class="option-header">
      <select v-model="selectedFormation">
        <option v-for="formation of listFormation" :key="formation.id" :value="formation.id">
          {{ formation.nom }}
        </option>
      </select>
      <div class="action-buttons">
        <button class="btn-modifier" @click="handleModif">
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24"
               fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
               stroke-linejoin="round" class="icon-edit">
            <path d="M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7"></path>
            <path d="M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z"></path>
          </svg>
          Modifier
        </button>
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
      <div class="option-title">Ajouter une option d'autre type</div>
      <form @submit.prevent="ajouteFormation">
        <input v-model="data.formation.nom" type="text" placeholder="Nom de la formation">
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

<script setup>
import doAjaxRequest from '@/util/util.js'
import {onMounted, reactive, ref} from 'vue'
import router from "@/router/index.js";

const listSalon = reactive([])
const selectedSalon = ref(null);
const editing = ref(false);
const editedName = ref("");

const ajouter = ref(false);


let data = reactive({
  // Les données saisies dans le formulaire
  formulaire: {
    nom : "",
    date : "",
    archive : ""},
})

function getSalon() {
  let premierTour = false;
  doAjaxRequest('/api/salons')
    .then((result) => {
      console.log(result._embedded)
      for (let elmnt of result._embedded.salons) {
        console.log(elmnt);
        if (premierTour == false){
          selectedSalon.value = elmnt.idSalon
          premierTour = !premierTour
        }
        listSalon.push(elmnt)
      }
      console.log(listSalon)
    })
    .catch((error) => alert(error.message))
}

function handleModif() {
  editing.value = true;
  const selected = listSalon.find(salon => salon.idSalon === selectedSalon.value);
  if (selected) {
    editedName.value = selected.nom;
  }
}

function handleSave() {
  const options = {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ nom: editedName.value })
  };
  fetch(`/rest/modifNomSalon/${selectedSalon.value}`, options)
    .then(() => {
      editing.value = false;
      listSalon.find(salon => salon.idSalon === selectedSalon.value).nom = editedName.value;
    })
    .catch((error) => alert(error.message));
}

// Fonction qui permet l'archivage
function handleArchivage() {
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

// Fonction qui appelle la suppression des salons
function handleDelete(){
  if(confirm("Êtes vous sûr de vouloir supprimer ce salon ? Cela va entraîner la suppression de tous les prospects liés à ce salon")){
    deleteSalon(selectedSalon.value)
  };
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

function handleAjoutAffich(){
  ajouter.value = !ajouter.value
}

function ajouteSalon(){
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

onMounted(() => getSalon())
</script>

<template>
  <div v-if="editing">
    <input v-model="editedName" type="text" />
    <button @click="handleSave">Enregistrer</button>
    <button @click="editing = false">Annuler</button>
  </div>
  <div v-else>
    <select v-model="selectedSalon">
      <option v-for="salon of listSalon" :key="salon.idSalon" :value="salon.idSalon">
        {{ salon.nom }} <span v-if="salon.archive">(Archivé)</span>
      </option>
    </select>
    <button @click="handleModif">Modifier</button>
    <button @click="handleArchivage">Archiver</button>
    <button @click="handleDelete">Supprimer</button>
    <button @click="handleAjoutAffich">Ajouter</button>
  </div>
  <div v-if="ajouter">
    <form @submit.prevent="ajouteSalon">
      <input v-model="data.formulaire.nom" type="text" placeholder="Nom du salon">
      <input v-model="data.formulaire.date" type="date">
      <input type="checkbox" v-model="data.formulaire.archive"> Archiver ?
      <button type="submit">Ajouter</button>
      <button @click="handleAjoutAffich"> Annuler</button>
    </form>
  </div>
</template>

<style scoped></style>

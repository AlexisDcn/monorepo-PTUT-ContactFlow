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
    nom : "",
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
        if (premierTour == false){
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

function handleAjoutAffich(){
  ajouter.value = !ajouter.value
}

function handleDelete(){
  if(confirm("Êtes vous sûr de vouloir supprimer cette formation ? Les prospects liés à cette formation ne seront plus comptés dans les graphiques sans pour autant être supprimés")){
    deleteFormation(selectedFormation.value)
  };
}

// ---------------- Fonctions CRUD ---------------- //

function ajouteFormation(){
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

onMounted(()=>
getFormation()
)

</script>

<template>

  <h3>Formations</h3>
  <div v-if="editing">
    <input v-model="editedName" type="text" />
    <button @click="handleSave">Enregistrer</button>
    <button @click="editing = false">Annuler</button>
  </div>
  <div v-else>
    <select v-model="selectedFormation">
      <option v-for="formation of listFormation" :key="formation.id" :value="formation.id">
        {{ formation.nom }}</option>
    </select>
    <button @click="handleModif">Modifier</button>
    <button @click="handleDelete">Supprimer</button>
    <button @click="handleAjoutAffich">Ajouter</button>
  </div>
  <div v-if="ajouter">
    <form @submit.prevent="ajouteFormation">
      <input v-model="data.formation.nom" type="text" placeholder="Nom de la formation">
      <button type="submit">Ajouter</button>
      <button @click="handleAjoutAffich"> Annuler</button>
    </form>
  </div>

</template>

<style scoped>

</style>

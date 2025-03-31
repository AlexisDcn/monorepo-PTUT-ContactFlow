<script setup>
import doAjaxRequest from '@/util/util.js'
import {onMounted, reactive, ref} from 'vue'

const listSalon = reactive([])
const selectedSalon = ref(null);

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


onMounted(() => getSalon())
</script>

<template>
  <select v-model="selectedSalon">
    <option v-for="salon of listSalon" :value="salon.idSalon">{{ salon.nom }} <span v-if="salon.archive">(Archivé)</span>
    </option>
  </select>
  <button @click="handleArchivage">Archiver</button>
  <button @click="handleDelete"> Supprimer </button>
</template>

<style scoped></style>

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

function handleClick() {
  console.log(`Salon sélectionné : ${selectedSalon.value}`)
}

function handleDelete(){
  if(confirm("Êtes vous sûr de vouloir supprimer ce salon ? Cela va entraîner la suppression de tous les prospects liés à ce salon")){
    deleteSalon(selectedSalon.value)
  };
}

function deleteSalon(id){
  deleteRelatif(id)
  console.log(id);
  const options = {
    method: 'DELETE',
  }
  doAjaxRequest(`/api/salons/${id}`, options)
    .then((result) => {
      console.log(result)
    })
    .catch((error) => alert(error.message))
}

function deleteRelatif(id){
  console.log('ahah', id);
}

onMounted(() => getSalon())
</script>

<template>
  <select v-model="selectedSalon">
    <option v-for="salon of listSalon" :value="salon.idSalon">{{ salon.nom }}</option>
  </select>
  <button @click="handleClick">Modifier</button>
  <button @click="handleDelete"> Supprimer </button>
</template>

<style scoped></style>

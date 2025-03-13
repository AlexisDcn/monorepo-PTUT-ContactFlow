<script setup>


import {onMounted, reactive} from "vue";
import doAjaxRequest from "@/util/util.js";
import { useRouter } from 'vue-router';

// Définition de la liste des formulaires réactive
const listeForm = reactive([]);

// Définition du router
const router = useRouter();

function getFormulaires(){
  doAjaxRequest('/rest/formulairesActifs')
    .then((result) => {
      console.log(result)
      for (let elmnt of result.formulaire){
          listeForm.push(elmnt)
      }
    })
    .catch((error) => alert(error.message))
}

function navFormu(id){
  router.push("/formulaire/" + id)
}

onMounted(()=>
getFormulaires()
)

</script>

<template>
<h1>Test</h1>
  <button v-for="formu in listeForm" @click="navFormu(formu.salon.idSalon)" :idSalon="formu.salon.idSalon"> Formulaire {{ formu.idForm }}</button>

</template>

<style scoped>

</style>

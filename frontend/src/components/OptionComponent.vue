<script setup>

// Récupération de toutes les données modifiables

import doAjaxRequest from "@/util/util.js";
import {onMounted} from "vue";
import SalonModif from "@/components/Option/SalonModif.vue";
import FormationModif from "@/components/Option/FormationModif.vue";

function getFormations(){
  doAjaxRequest('/api/formations')
    .then((result) => {
      console.log(result._embedded)
    })
    .catch((error) => alert(error.message))
}

function getChamps(){
  doAjaxRequest('/api/champs')
    .then((result)=> {
      console.log(result._embedded)
    })
    .catch((error) => alert(error.message))
}

onMounted(()=>{
  // getChamps()
  // getFormations()
})

</script>

<template>
  <SalonModif/>
  <FormationModif/>
</template>

<style scoped>

</style>

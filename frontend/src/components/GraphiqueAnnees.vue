<script setup>


import {onMounted, reactive, ref, watch} from "vue";
import doAjaxRequest from "@/util/util.js";

function debut(){
  doAjaxRequest('/rest/getPersParAnnee')
    .then((result) => {
      console.log(result);
    })
    .catch((error) => alert(error.message))
}

onMounted(()=>
  debut()
)

</script>

<template>

</template>

<style scoped>

</style>

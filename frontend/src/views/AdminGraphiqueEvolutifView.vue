<template>
  <v-container>
    <HeaderAdmin />
    <h1>Graphique évolutif</h1>
    <VueApexCharts :options="chartOptions" :series="chartOptions.series" />
  </v-container>
</template>

<script setup>
import HeaderAdmin from "@/components/HeaderAdmin.vue";
import VueApexCharts from "vue3-apexcharts";
import {reactive} from "vue";

const dataRecup = reactive([]);

const chartOptions = {
  chart: { type: "bar" },
  series: [{ name: "Ventes", data: dataRecup }]
};

function getPersonneParSalon(){
  doAjaxRequest('/rest/formulairesActifs')
    .then((result) => {
      console.log(result)
      for (let elmnt of result.formulaire){
        listeForm.push(elmnt)
      }
    })
    .catch((error) => alert(error.message))
}

function getSalon(){

}
</script>

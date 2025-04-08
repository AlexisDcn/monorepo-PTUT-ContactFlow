<template>
  <v-btn @click="boutonRetour" color="4" class="mb-4">Retour</v-btn>
  <v-container>
    <h1>Graphique évolutif</h1>
    <VueApexCharts :options="chartOptions" :series="chartOptions.series" />
  </v-container>

  <div>
    <label v-for="ville in listVille" :key="ville" style="display: block; margin-bottom: 8px;">
      <input
        type="checkbox"
        :value="ville"
        v-model="selectedVilles"
        @change="handleClick(ville)"
      />
      {{ ville }}
    </label>
  </div>

</template>

<script setup>
import VueApexCharts from "vue3-apexcharts";
import {onMounted, reactive, ref, watch} from "vue";
import doAjaxRequest from "@/util/util.js";
import router from "@/router/index.js";
import boutonRetour from "@/components/BoutonRetour.vue";

const dataRecup = reactive([]);
const nameList = reactive([]);
const listVille = reactive([]);
const selectedVilles = ref([]);

const chartOptions = {
  chart: { type: "bar" },
  series: [{ name: "Nombre de prospects", data: dataRecup }],
  xaxis: { categories: nameList }
};

function getPersonneParVille(){
  doAjaxRequest('/rest/getPersonneParVille')
    .then((result) => {
      // Itération sur le dictionnaire de base (Salon : {Dico})
      for (const [clefDicoGen, dicoSecondaire] of Object.entries(result)) {
        console.log(clefDicoGen, dicoSecondaire);
        // Itération sur le dico contenant les valeurs
        for (const [keyNom, valeurNb] of Object.entries(dicoSecondaire)) {
          console.log(keyNom);
          dataRecup.push(valeurNb);
          nameList.push(keyNom);
        }
      }
    })
    .catch((error) => alert(error.message))
}

async function getPersonneParVilleSpe(ville){
  return doAjaxRequest(`/rest/getCountVille/${ville}`)
    .then((result) =>{
      return result
    })
    .catch((error) => alert(error.message))
}

async function handleClick(ville) {
  if (selectedVilles.value.includes(ville)) {
    try {
      // On attends les résultats des fonctions get pour s'assurer de ne pas avoir un undefined
      let nbpers = await getPersonneParVilleSpe(ville);

      // Quand c'est récup on ajoute aux listes => nbpers.Salon car renvoie un dictionnaire
      dataRecup.push(nbpers)
      nameList.push(ville);
    } catch (error) {
      console.error(error);
    }
  } else {
    // Récup de l'index du nom du salon
    let index = nameList.indexOf(ville);
    // Puisque les indexs sont identiques entre les 2 listes on supprime dans les 2 listes au même index
    if (index !== -1) {
      dataRecup.splice(index, 1);
      nameList.splice(index, 1);
    }
  }
}


watch(selectedVilles, (newValue) => {
  console.log("Villes sélectionnés :", newValue);
  console.log("Datas des trucs", dataRecup);
  console.log("Bien garçon", selectedVilles);

});

function getVille() {
  doAjaxRequest('/rest/getVille')
    .then((result) => {
      console.log(result)
      for (let elmnt of result) {
        listVille.push(elmnt)
      }
      selectedVilles.value = listVille.map(ville => ville);
      console.log(listVille)
    })
    .catch((error) => alert(error.message))
}

onMounted(()=>
  getPersonneParVille(),
  getVille()
)

</script>


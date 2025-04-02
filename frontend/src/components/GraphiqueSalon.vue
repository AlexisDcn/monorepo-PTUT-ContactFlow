<template>
  <v-btn @click="boutonRetour" color="4" class="mb-4">Retour</v-btn>
  <v-container>
    <h1>Graphique évolutif</h1>
    <VueApexCharts :options="chartOptions" :series="chartOptions.series" />
  </v-container>

  <div>
    <v-checkbox
      @change="handleClick(salon.idSalon)"
      type="checkbox"
      v-for="salon in listSalon"
      :key="salon.idSalon"
      v-model="selectedSalons"
      :label="salon.nom"
      :value="salon.idSalon"
    />
  </div>

</template>

<script setup>
import VueApexCharts from "vue3-apexcharts";
import {onMounted, reactive, ref, watch} from "vue";
import doAjaxRequest from "@/util/util.js";
import router from "@/router/index.js";

const dataRecup = reactive([]);
const nameList = reactive([]);
const listSalon = reactive([]);
const selectedSalons = ref([]);

const chartOptions = {
  chart: { type: "bar" },
  series: [{ name: "Nombre de prospects", data: dataRecup }],
  xaxis: { categories: nameList }
};

function getPersonneParSalon(){
  doAjaxRequest('/rest/getCountSalon')
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

async function getPersonneParSalonSpe(idSalon){
  return doAjaxRequest(`/rest/getCountSalon/${idSalon}`)
    .then((result) =>{
      return result
    })
    .catch((error) => alert(error.message))
}

function getSalon(){
  doAjaxRequest('/api/salons')
    .then((result) => {
      for (let elmnt of result._embedded.salons){
        listSalon.push(elmnt);
      }
      selectedSalons.value = listSalon.map(salon => salon.idSalon);
      console.log(listSalon);
    })
    .catch((error) => alert(error.message))
}

async function getSalonName(idSalon){
  return doAjaxRequest(`/api/salons/${idSalon}`)
    .then((result) => {
      return result.nom
    })
}

function boutonRetour() {
  router.back();
}

async function handleClick(idRecup) {
  if (selectedSalons.value.includes(idRecup)) {
    try {
      // On attends les résultats des fonctions get pour s'assurer de ne pas avoir un undefined
      let nbpers = await getPersonneParSalonSpe(idRecup);
      let nomSalon = await getSalonName(idRecup);

      // Quand c'est récup on ajoute aux listes => nbpers.Salon car renvoie un dictionnaire
      dataRecup.push(nbpers.Salon)
      nameList.push(nomSalon);
    } catch (error) {
      console.error(error);
    }
  } else {
    // Récup de l'index du nom du salon
    let index = nameList.indexOf(await getSalonName(idRecup));
    // Puisque les indexs sont identiques entre les 2 listes on supprime dans les 2 listes au même index
    if (index !== -1) {
      dataRecup.splice(index, 1);
      nameList.splice(index, 1);
    }
  }
}


watch(selectedSalons, (newValue) => {
  console.log("Salons sélectionnés :", newValue);
  console.log("Datas des trucs", dataRecup);
  console.log("Bien garçon", selectedSalons._value);

});

// function handleChange(truc){
//   console.log(truc.checked);
// }

onMounted(()=>
    getPersonneParSalon(),
  getSalon()
)
</script>

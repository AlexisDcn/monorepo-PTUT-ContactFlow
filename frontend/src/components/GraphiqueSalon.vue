<template>
  <div class="page-container">
    <h1 class="title">Graphique évolutif par salon</h1>

    <div class="chart-wrapper">
      <VueApexCharts
        class="chart"
        :options="chartOptions"
        :series="chartOptions.series"
      />
    </div>

    <div class="checkbox-container">
      <div class="filter-label">Filtrer par salon :</div>
      <div class="checkbox-group">
        <label v-for="salon in listSalon" :key="salon.idSalon" class="checkbox-label">
          <input
            type="checkbox"
            :value="salon.idSalon"
            v-model="selectedSalons"
            @change="handleClick(salon.idSalon)"
          />
          {{ salon.nom }}
        </label>
      </div>
    </div>
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
  chart: {
    type: "bar",
    fontFamily: 'Arial, sans-serif',
    background: 'transparent',
    toolbar: {
      show: true,
      tools: {
        download: true,
        selection: true,
        zoom: true,
        zoomin: true,
        zoomout: true,
        pan: true,
        reset: true
      }
    }
  },
  colors: ["#5f4e9b"],
  plotOptions: {
    bar: {
      borderRadius: 6,
      columnWidth: '60%',
      distributed: false,
      dataLabels: {
        position: 'center'
      }
    }
  },
  dataLabels: {
    enabled: true,
    formatter: function (val) {
      return val;
    },
    style: {
      fontSize: '12px',
      colors: ["#ffffff"]  // Texte en blanc
    }
  },
  series: [{ name: "Nombre de prospects", data: dataRecup }],
  xaxis: {
    categories: nameList,
    labels: {
      style: {
        colors: "#333",
        fontSize: '12px'
      }
    },
    axisBorder: {
      show: true,
      color: '#e0e0e0'
    },
    axisTicks: {
      show: true,
      color: '#e0e0e0'
    }
  },
  yaxis: {
    labels: {
      style: {
        colors: "#333",
        fontSize: '12px'
      }
    },
  },
  grid: {
    borderColor: '#f2f2f2',
    row: {
      colors: ['transparent', 'transparent']
    }
  },
  tooltip: {
    y: {
      formatter: function(val) {
        return val + " prospects"
      }
    }
  }
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

onMounted(()=>
    getPersonneParSalon(),
  getSalon()
)
</script>

<style scoped>
.page-container {
  padding: 30px;
  background-color: white;
  border-radius: 15px;
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
  max-width: 1000px;
  margin: 30px auto;
  border-top: 5px solid #5f4e9b;
}

.title {
  font-size: 28px;
  font-weight: bold;
  color: #2f2769;
  text-align: center;
  margin-bottom: 30px;
  padding-bottom: 15px;
  border-bottom: 2px solid #f2f2f2;
}

.chart-wrapper {
  display: flex;
  justify-content: center;
  margin-bottom: 40px;
  background-color: #f9f9f9;
  padding: 20px;
  border-radius: 10px;
  border: 1px solid #eaeaea;
}

.chart {
  max-width: 800px;
  width: 100%;
  height: 400px;
}

.checkbox-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 12px;
  margin-top: 30px;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 10px;
  border: 1px solid #eaeaea;
}

.filter-label {
  width: 100%;
  margin-bottom: 10px;
  font-weight: 500;
  text-align: center;
  color: #2f2769;
}

.checkbox-group {
  display: flex;
  flex-wrap: wrap;
  gap: 12px;
  justify-content: center;
}

.checkbox-label {
  display: flex;
  align-items: center;
  background-color: white;
  padding: 10px 16px;
  border-radius: 8px;
  border: 1px solid #e0e0e0;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.06);
  font-weight: 500;
  cursor: pointer;
  transition: all 0.2s ease-in-out;
  color: #333;
}

.checkbox-label:hover {
  background-color: #f5f5ff;
  transform: scale(1.03);
  border-color: #5f4e9b;
}

/* Style personnalisé pour les checkboxes */
.checkbox-label input[type="checkbox"] {
  appearance: none;
  -webkit-appearance: none;
  width: 18px;
  height: 18px;
  border: 2px solid #5f4e9b;
  border-radius: 4px;
  margin-right: 10px;
  position: relative;
  cursor: pointer;
  vertical-align: middle;
  transition: all 0.2s;
}

.checkbox-label input[type="checkbox"]:checked {
  background-color: #5f4e9b;
}

.checkbox-label input[type="checkbox"]:checked::after {
  content: "";
  position: absolute;
  left: 5px;
  top: 1px;
  width: 5px;
  height: 10px;
  border: solid white;
  border-width: 0 2px 2px 0;
  transform: rotate(45deg);
}

.checkbox-label input[type="checkbox"]:focus {
  outline: none;
  box-shadow: 0 0 0 3px rgba(95, 78, 155, 0.2);
}

/* Responsive */
@media (max-width: 768px) {
  .page-container {
    padding: 20px;
  }

  .title {
    font-size: 24px;
  }

  .chart {
    height: 300px;
  }

  .checkbox-container {
    padding: 15px;
  }

  .checkbox-label {
    padding: 8px 12px;
    font-size: 14px;
  }
}
</style>

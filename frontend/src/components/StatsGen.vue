<template>
  <div class="container">
    <div>
      <label>Filtrer par salon :</label>
      <div v-for="salon in listSalon" :key="salon.idSalon">
        <input
          type="checkbox"
          :value="salon.idSalon"
          v-model="selectedSalons"
          @change="handleCheckboxChange"
        />
        {{ salon.nom }}
      </div>
    </div>

    <CsvDownloader :data="filteredProspects" :headers="headers" />
    <table class="data-table">
      <thead>
      <tr>
        <th
          v-for="header in headers"
          :key="header.value"
          @click="sortBy(header.value)"
          :class="{ 'sorted-asc': sortKey === header.value && sortOrder === 'asc', 'sorted-desc': sortKey === header.value && sortOrder === 'desc' }"
        >
          {{ header.text }}
          <span v-if="sortKey === header.value">
              {{ sortOrder === 'asc' ? '▲' : '▼' }}
            </span>
        </th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="prospect in sortedProspects" :key="prospect.idProspect">
        <td v-for="header in headers" :key="header.value">{{ prospect[header.value] }}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { onMounted, reactive, ref, computed } from "vue";
import doAjaxRequest from "@/util/util.js";
import CsvDownloader from "@/components/CsvDownloader.vue";

const prospectVide = {
  nom: "",
  prenom: "",
  numTel: "",
  mail: "",
  ville: "",
  departement: "",
  codePostal: "",
};

let data = reactive({
  prospects: [],
});

const headers = [
  { text: 'ID', value: 'idProspect' },
  { text: 'Nom', value: 'nom' },
  { text: 'Prénom', value: 'prenom' },
  { text: 'Numéro de Téléphone', value: 'numTel' },
  { text: 'Email', value: 'mail' },
  { text: 'Ville', value: 'ville' },
  { text: 'Département', value: 'departement' },
  { text: 'Code Postal', value: 'codePostal' },
];

const listSalon = reactive([]);
const selectedSalons = ref([]);
const sortKey = ref(null);
const sortOrder = ref('asc');

const filteredProspects = computed(() => {
  if (selectedSalons.value.length === 0) return data.prospects;
  return data.prospects.filter(prospect =>
    selectedSalons.value.includes(prospect.salon)
  );
});

const sortedProspects = computed(() => {
  let prospects = filteredProspects.value;
  if (!sortKey.value) return prospects;
  return [...prospects].sort((a, b) => {
    const aValue = a[sortKey.value];
    const bValue = b[sortKey.value];
    if (aValue < bValue) return sortOrder.value === 'asc' ? -1 : 1;
    if (aValue > bValue) return sortOrder.value === 'asc' ? 1 : -1;
    return 0;
  });
});

function sortBy(key) {
  if (sortKey.value === key) {
    sortOrder.value = sortOrder.value === 'asc' ? 'desc' : 'asc';
  } else {
    sortKey.value = key;
    sortOrder.value = 'asc';
  }
}

/*function getProspect() {
  doAjaxRequest("/api/prospects")
    .then((result) => {
      // Filtrer les données pour exclure la propriété _links
      data.prospects = result._embedded.prospects.map(prospect => {
        const { _links, ...rest } = prospect;
        return rest;
      });
      console.log(data.prospects);
    })
    .catch((error) => alert(error.message));
}
*/

function getProspects(){
  doAjaxRequest('/rest/getProspectsSalon')
    .then((result) => {
      // Itération sur le dictionnaire de base (Salon : {Dico})
      for (const [clefDicoGen, dicoSecondaire] of Object.entries(result)) {
        console.log(clefDicoGen, dicoSecondaire);
        // Itération sur le dico contenant les valeurs
        for (const [salonNom, prospectList] of Object.entries(dicoSecondaire)) {
          console.log(salonNom);
          console.log(prospectList[0]);
          for (let prospect of prospectList) {
            console.log(prospect);
            data.prospects.push(prospect);
          }

          console.log(data.prospects);
        }
      }
    })
    .catch((error) => alert(error.message))
}

async function getProspectsSpe(idSalon){
  return doAjaxRequest(`/rest/getProspectsSalon/${idSalon}`)
    .then((result) =>{
      console.log("blabla",result.type);
      return result
    })
    .catch((error) => alert(error.message))
}



function getSalon() {
  doAjaxRequest('/api/salons')
    .then((result) => {
      console.log(result._embedded);
      for (let elmnt of result._embedded.salons) {
        listSalon.push(elmnt);
      }
      console.log(listSalon);
    })
    .catch((error) => alert(error.message));
}

function handleCheckboxChange() {
  console.log(`Salons sélectionnés : ${selectedSalons.value}`);
}

onMounted(() => {
  getSalon();
  getProspects();
  getProspectsSpe(1);
});
</script>

<style scoped>
.container {
  max-width: 100%;
  background-color: #f9f9f9;
  padding: 20px;
  border-radius: 10px;
  overflow-x: auto;
}

.data-table {
  width: 100%;
  border-collapse: collapse;
  background-color: #5F4E9B; /* Fond violet */
  border-radius: 15px; /* Coins arrondis */
  overflow: hidden;
}

.data-table thead {
  background-color: transparent;
}

.data-table th, .data-table td {
  padding: 12px;
  text-align: center;
  cursor: pointer;
}

.data-table th {
  color: white;
  font-weight: bold;
  font-size: 16px;
}

.data-table td {
  background-color: white;
  font-size: 14px;
}

</style>

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

    <CsvDownloader :data="data.prospects" :headers="headers" />
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
import { watch } from "vue";

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

// const filteredProspects = computed(() => {
//   if (selectedSalons.value.length === 0) return data.prospects;
//   return data.prospects.filter(prospect =>
//     selectedSalons.value.includes(prospect.salon)
//   );
// });

const sortedProspects = computed(() => {
  let prospects = data.prospects;  // On utilise directement data.prospects
  if (!sortKey.value) return prospects;  // Si aucun tri, retourner directement
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

function getProspects() {
  doAjaxRequest('/rest/getProspectsSalon')
    .then((result) => {
      // Itération sur le dictionnaire de base (Salon : {Dico})
      for (const [clefDicoGen, dicoSecondaire] of Object.entries(result)) {
        // Itération sur chaque salon et les prospects associés
        for (const [salonNom, prospectList] of Object.entries(dicoSecondaire)) {
          for (let prospect of prospectList) {
            // Vérification pour éviter les doublons (en fonction de l'idProspect)
            if (!data.prospects.some(p => p.idProspect === prospect.idProspect)) {
              data.prospects.push(prospect);  // Ajout uniquement si le prospect n'est pas déjà présent
            }
          }
        }
      }
    })
    .catch((error) => alert(error.message));
}

async function getProspectsSpe(idSalon){
  return doAjaxRequest(`/rest/getProspectsSalon/${idSalon}`)
    .then((result) =>{
      return result
    })
    .catch((error) => alert(error.message))
}

function getSalon() {
  doAjaxRequest('/api/salons')
    .then((result) => {
      for (let elmnt of result._embedded.salons) {
        listSalon.push(elmnt);
      }

      selectedSalons.value = listSalon.map(salon => salon.idSalon);
    })
    .catch((error) => alert(error.message));
}

async function handleCheckboxChange() {
  if (selectedSalons.value.length === listSalon.length) {
    await getProspects();
  } else {

    let allProspects = [];
    for (let idSalon of selectedSalons.value) {
      let prospects = await getProspectsSpe(idSalon);
      allProspects.push(...prospects);
    }


    // Suppression des doublons
    let uniqueProspects = Array.from(new Map(allProspects.map(p => [p.idProspect, p])).values());

    data.prospects = uniqueProspects;
  }
}



watch(selectedSalons, (newVal) => {
  if (newVal.length === listSalon.length) {
    getProspects();
  }
});

onMounted(() => {
  getSalon();
  getProspects();
  //getProspectsSpe(1);
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

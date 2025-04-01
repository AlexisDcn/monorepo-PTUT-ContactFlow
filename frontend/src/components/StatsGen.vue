<template>
  <div class="container">
    <div class="salon-filter">
      <label>Filtrer par salon :</label>
      <div class="checkbox-container">
        <div class="checkbox-item" v-for="salon in listSalon" :key="salon.idSalon">
          <label class="custom-checkbox">
            <input
              type="checkbox"
              :value="salon.idSalon"
              v-model="selectedSalons"
              @change="handleCheckboxChange"
            />
            <span class="checkmark"></span>
          </label>
          <span>{{ salon.nom }}</span>
        </div>
      </div>
    </div>

    <div class="year-filter">
      <label>Filtrer par année :</label>
      <div class="checkbox-container">
        <div class="checkbox-item" v-for="year in availableYears" :key="year">
          <label class="custom-checkbox">
            <input
              type="checkbox"
              :value="year"
              v-model="selectedYears"
              @change="handleYearFilterChange"
            />
            <span class="checkmark"></span>
          </label>
          <span>{{ year }}</span>
        </div>
      </div>
    </div>

    <div class="csv-download">
      <CsvDownloader :data="data.prospects" :headers="headers"/>
    </div>

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
      <tr v-for="prospect in paginatedProspects" :key="prospect.idProspect">
        <td v-for="header in headers" :key="header.value">{{ prospect[header.value] }}</td>
      </tr>
      </tbody>
    </table>

    <!-- Table Footer avec pagination -->
    <div class="data-table-footer">
      <div class="rows-per-page">
        <span>Prospects par page:</span>
        <select v-model="rowsPerPage" @change="currentPage = 1">
          <option :value="10">10</option>
          <option :value="25">25</option>
          <option :value="50">50</option>
          <option :value="100">100</option>
        </select>
      </div>

      <div class="pagination">
        <button
          @click="currentPage = 1"
          :disabled="currentPage === 1"
          title="Première page"
        >
          &laquo;
        </button>
        <button
          @click="currentPage--"
          :disabled="currentPage === 1"
          title="Page précédente"
        >
          &lsaquo;
        </button>

        <span class="page-info">
          Page {{ currentPage }} sur {{ totalPages }}
        </span>

        <button
          @click="currentPage++"
          :disabled="currentPage === totalPages"
          title="Page suivante"
        >
          &rsaquo;
        </button>
        <button
          @click="currentPage = totalPages"
          :disabled="currentPage === totalPages"
          title="Dernière page"
        >
          &raquo;
        </button>
      </div>

      <div>
        Affichage de {{ startItem }}-{{ endItem }} sur {{ totalItems }} éléments
      </div>
    </div>
  </div>
</template>

<script setup>
import {onMounted, reactive, ref, computed} from "vue";
import doAjaxRequest from "@/util/util.js";
import CsvDownloader from "@/components/CsvDownloader.vue";
import {watch} from "vue";

let data = reactive({
  prospects: [],
});

const headers = [
  {text: 'ID', value: 'idProspect'},
  {text: 'Nom', value: 'nom'},
  {text: 'Prénom', value: 'prenom'},
  {text: 'Numéro de Téléphone', value: 'numTel'},
  {text: 'Email', value: 'mail'},
  {text: 'Ville', value: 'ville'},
  {text: 'Département', value: 'departement'},
  {text: 'Code Postal', value: 'codePostal'},
];

const listSalon = reactive([]);
const selectedSalons = ref([]);
const availableYears = ref([]);
const selectedYears = ref([]);
const sortKey = ref(null);
const sortOrder = ref('asc');

// Pagination
const currentPage = ref(1);
const rowsPerPage = ref(10);

const sortedProspects = computed(() => {
  let prospects = data.prospects;
  if (!sortKey.value) return prospects;
  return [...prospects].sort((a, b) => {
    const aValue = a[sortKey.value];
    const bValue = b[sortKey.value];
    if (aValue < bValue) return sortOrder.value === 'asc' ? -1 : 1;
    if (aValue > bValue) return sortOrder.value === 'asc' ? 1 : -1;
    return 0;
  });
});

// Computed properties pour la pagination
const totalItems = computed(() => sortedProspects.value.length);

const totalPages = computed(() => {
  return Math.ceil(totalItems.value / rowsPerPage.value) || 1;
});

const startItem = computed(() => {
  if (totalItems.value === 0) return 0;
  return (currentPage.value - 1) * rowsPerPage.value + 1;
});

const endItem = computed(() => {
  const end = currentPage.value * rowsPerPage.value;
  return end > totalItems.value ? totalItems.value : end;
});

const paginatedProspects = computed(() => {
  const start = (currentPage.value - 1) * rowsPerPage.value;
  const end = start + rowsPerPage.value;
  return sortedProspects.value.slice(start, end);
});

// Surveiller les changements de taille de données pour ajuster la page courante si nécessaire
watch(totalPages, (newTotalPages) => {
  if (currentPage.value > newTotalPages) {
    currentPage.value = newTotalPages;
  }
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
      // Réinitialiser le tableau avant d'ajouter de nouveaux prospects
      data.prospects = [];

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

      // Réinitialiser la pagination
      currentPage.value = 1;
    })
    .catch((error) => alert(error.message));
}

async function getProspectsSpe(idSalon) {
  return doAjaxRequest(`/rest/getProspectsSalon/${idSalon}`)
    .then((result) => {
      return result
    })
    .catch((error) => alert(error.message))
}

function getAvailableYears() {
  doAjaxRequest('/rest/getAvailableYears')
    .then((result) => {
      availableYears.value = result;
      selectedYears.value = [...result]; // Sélectionner toutes les années par défaut
    })
    .catch((error) => alert(error.message));
}

async function getProspectsByYear(year) {
  return doAjaxRequest(`/rest/getProspectsByYear/${year}`)
    .then((result) => {
      return result;
    })
    .catch((error) => alert(error.message));
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
  // Réinitialiser la pagination lors du changement de filtre
  currentPage.value = 1;

  if (selectedSalons.value.length === listSalon.length &&
    selectedYears.value.length === availableYears.value.length) {
    await getProspects();
  } else {
    let allProspects = [];

    // Si toutes les années sont sélectionnées, utiliser la logique existante du filtre salon
    if (selectedYears.value.length === availableYears.value.length) {
      for (let idSalon of selectedSalons.value) {
        let prospects = await getProspectsSpe(idSalon);
        allProspects.push(...prospects);
      }
    } else {
      // Filtrer par année et salon
      for (let year of selectedYears.value) {
        let yearProspects = await getProspectsByYear(year);

        // Filtrer par salons sélectionnés
        yearProspects = yearProspects.filter(p =>
          selectedSalons.value.includes(p.salon.idSalon));

        allProspects.push(...yearProspects);
      }
    }

    // Suppression des doublons
    let uniqueProspects = Array.from(new Map(allProspects.map(p => [p.idProspect, p])).values());

    data.prospects = uniqueProspects;
  }
}

async function handleYearFilterChange() {
  // Réinitialiser la pagination
  currentPage.value = 1;

  // Suppression de la condition qui réinitialise selectedYears
  // if (selectedYears.value.length === 0) {
  //   selectedYears.value = [...availableYears.value];
  //   return;
  // }

  if (selectedSalons.value.length === listSalon.length &&
    selectedYears.value.length === availableYears.value.length) {
    // Si tous les salons et toutes les années sont sélectionnés, obtenir tous les prospects
    await getProspects();
  } else {
    // Sinon, filtrer par salons et années sélectionnés
    let allProspects = [];

    // Obtenir les prospects pour chaque année sélectionnée
    for (let year of selectedYears.value) {
      let yearProspects = await getProspectsByYear(year);

      // Si des salons spécifiques sont sélectionnés, filtrer les résultats
      if (selectedSalons.value.length !== listSalon.length) {
        yearProspects = yearProspects.filter(p =>
          selectedSalons.value.includes(p.salon.idSalon));
      }

      allProspects.push(...yearProspects);
    }

    // Si aucune année n'est sélectionnée, la liste sera vide

    // Supprimer les doublons
    let uniqueProspects = Array.from(
      new Map(allProspects.map(p => [p.idProspect, p])).values()
    );

    data.prospects = uniqueProspects;
  }
}

watch(selectedSalons, (newVal) => {
  if (newVal.length === listSalon.length &&
    selectedYears.value.length === availableYears.value.length) {
    getProspects();
  }
});

watch(selectedYears, (newVal) => {
  if (newVal.length === availableYears.value.length &&
    selectedSalons.value.length === listSalon.length) {
    getProspects();
  }
});

onMounted(() => {
  getSalon();
  getAvailableYears();
  getProspects();
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

/* Style des checkboxes */
.salon-filter, .year-filter {
  margin-bottom: 20px;
}

.salon-filter label, .year-filter label {
  font-weight: bold;
  margin-bottom: 10px;
  display: block;
}

.checkbox-container {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  margin-bottom: 15px;
}

.checkbox-item {
  display: flex;
  align-items: center;
}

.custom-checkbox {
  position: relative;
  display: inline-block;
  width: 18px;
  height: 18px;
  margin-right: 8px;
  cursor: pointer;
}

.custom-checkbox input {
  opacity: 0;
  width: 0;
  height: 0;
}

.checkmark {
  position: absolute;
  top: 0;
  left: 0;
  width: 18px;
  height: 18px;
  background-color: white;
  border: 2px solid #5F4E9B;
  border-radius: 3px;
}

.custom-checkbox input:checked ~ .checkmark {
  background-color: #5F4E9B;
}

.checkmark:after {
  content: "";
  position: absolute;
  display: none;
}

.custom-checkbox input:checked ~ .checkmark:after {
  display: block;
  left: 5px;
  top: 1px;
  width: 5px;
  height: 10px;
  border: solid white;
  border-width: 0 2px 2px 0;
  transform: rotate(45deg);
}

/* Style de tableau Excel */
.data-table {
  width: 100%;
  border-collapse: collapse;
  background-color: white;
  border: 1px solid #ddd;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.data-table thead {
  background-color: #5F4E9B;
}

.data-table th {
  padding: 12px;
  text-align: center;
  color: white;
  font-weight: bold;
  font-size: 14px;
  border: 1px solid #4a3d7c; /* Bordure entre les en-têtes */
  position: relative;
  cursor: pointer;
}

/* Garder la couleur des en-têtes au survol */
.data-table th:hover {
  background-color: #4a3d7c; /* Un violet légèrement plus foncé au survol */
  color: white; /* Garder le texte blanc */
}

.data-table td {
  padding: 10px;
  text-align: left;
  font-size: 14px;
  border: 1px solid #ddd; /* Bordure entre les cellules */
  border-bottom: 1px solid #ddd; /* Bordure en bas de chaque ligne */
}

.data-table tr:nth-child(even) {
  background-color: #f2f2f2; /* Lignes alternées comme dans Excel */
}

.data-table tbody tr:hover td {
  background-color: #e6e6ff; /* Effet de survol comme dans Excel */
}

.sorted-asc::after, .sorted-desc::after {
  margin-left: 5px;
}

.csv-download {
  margin: 15px 0;
}

/* Pour l'effet d'alternance des lignes comme dans Excel */
.data-table tbody tr:nth-child(even) td {
  background-color: #f2f2f2;
}

.data-table tbody tr:nth-child(odd) td {
  background-color: white;
}

/* Style du footer de la table */
.data-table-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 15px 10px;
  background-color: #f5f5f5;
  border: 1px solid #ddd;
  border-top: none;
  font-size: 14px;
}

.rows-per-page {
  display: flex;
  align-items: center;
  gap: 10px;
}

.rows-per-page select {
  padding: 5px 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  background-color: white;
  cursor: pointer;
}

.pagination {
  display: flex;
  align-items: center;
  gap: 5px;
}

.page-info {
  margin: 0 15px;
}

.pagination button {
  width: 32px;
  height: 32px;
  border: 1px solid #ccc;
  border-radius: 4px;
  background-color: white;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 14px;
  transition: all 0.2s;
}

.pagination button:hover {
  background-color: #e6e6ff;
  border-color: #5F4E9B;
}

.pagination button:disabled {
  opacity: 0.5;
  cursor: not-allowed;
  background-color: #f5f5f5;
}

.pagination button.active {
  background-color: #5F4E9B;
  color: white;
  border-color: #5F4E9B;
}
</style>

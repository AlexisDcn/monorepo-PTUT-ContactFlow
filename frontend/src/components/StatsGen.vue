<template>
  <v-container class="container fill-height">
    <CsvDownloader :data="data.prospects" :headers="headers" />
    <v-data-table :items="data.prospects" :footer-props="{
    itemsPerPageOptions: [5, 10, 15, 20],
    itemsPerPageText: 'Prospects par page :'
  }">
    </v-data-table>
  </v-container>
</template>

<script setup>
import { onMounted, reactive } from "vue";
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
  formulaire: { ...prospectVide },
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


function getProspect() {
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


onMounted(getProspect);
</script>

<style scoped>
.container {
  margin: 2rem auto;
  max-width: 100%;
  background-color: #f9f9f9;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0px 4px 10px #5f4e9b;
  overflow-x: auto;
}

.fill-height {
  height: 100%;
}

::v-deep(.v-data-table) {
  background-color: #5F4E9B; /* Fond violet */
  border-radius: 15px; /* Coins arrondis */
  padding: 20px; /* Espacement intérieur */
}

::v-deep(.v-data-table-header) {
  background-color: transparent;
  color: white;
  font-weight: bold;
  font-size: 16px;
}
::v-deep(.v-data-table td) {
  background-color: white; /* Fond blanc */
  padding: 12px 15px;
  text-align: left;
  font-size: 14px;
  border: none;
}
::v-deep(.v-data-table tbody tr) {
  border-bottom: none;
}

::v-deep(.v-data-table-footer) {
  background-color: #5F4E9B;
  color: white;
}
::v-deep(.v-data-table thead th) {
  color: white !important;
  font-weight: bold;
  text-align: left;
}




</style>

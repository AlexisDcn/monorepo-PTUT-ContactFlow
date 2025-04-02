<template>
  <v-btn @click="downloadCSV" color="primary">Télécharger les données en CSV</v-btn>
</template>

<script setup>
import { ref, defineProps } from 'vue';

const props = defineProps({
  data: {
    type: Array,
    required: true
  },
  headers: {
    type: Array,
    required: true
  }
});

// Fonction pour télécharger les statistiques en CSV
function downloadCSV() {
  const csvRows = [];

  // Ajouter les en-têtes de colonne
  csvRows.push(props.headers.map(header => header.text).join(','));

  // Ajouter les données
  props.data.forEach(item => {
    const row = props.headers.map(header => item[header.value]).join(',');
    csvRows.push(row);
  });

  // Créer un blob à partir des données CSV
  const csvContent = csvRows.join("\n");
  const blob = new Blob([csvContent], {type: 'text/csv'});
  const url = window.URL.createObjectURL(blob);

  // Créer un lien de téléchargement
  const a = document.createElement('a');
  a.setAttribute('hidden', '');
  a.setAttribute('href', url);
  a.setAttribute('download', 'listeDeProspects.csv');
  document.body.appendChild(a);
  a.click();
  document.body.removeChild(a);
}
</script>

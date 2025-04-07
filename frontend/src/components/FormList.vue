<script setup>
import doAjaxRequest from "@/util/util.js";
import { onMounted, reactive } from "vue";

let data = reactive({
  formulaires: [],
});

let salonsMap = reactive({});

function getFormData() {
  doAjaxRequest('/api/formulaires')
    .then((result) => {
      // Réinitialiser le tableau avant d'ajouter de nouveaux formulaires
      data.formulaires = [];

      // Ajout des formulaires
      for (let formulaire of result._embedded.formulaires) {
        // Extraire l'ID du salon à partir de l'URL
        if (formulaire._links && formulaire._links.salon && formulaire._links.salon.href) {
          const salonUrl = formulaire._links.salon.href;
          const idSalon = extractIdFromUrl(salonUrl);

          // Attacher l'ID du salon au formulaire pour référence
          formulaire.idSalon = idSalon;

          // Récupérer les détails du salon
          getSalonDetails(idSalon, formulaire.idForm);
        }

        data.formulaires.push(formulaire);
      }
    })
    .catch((error) => alert(error.message));
}

function extractIdFromUrl(url) {
  const parts = url.split('/');
  for (let i = 0; i < parts.length; i++) {
    if (parts[i] === 'formulaires' && i + 1 < parts.length) {
      return parts[i + 1];
    }
  }
  return null;
}

function getSalonDetails(idSalon, idForm) {
  doAjaxRequest(`/api/salons/${idSalon}`)
    .then((salonData) => {
      salonsMap[idForm] = salonData;
    })
    .catch((error) => {
      console.error(`Erreur lors de la récupération du salon ${idSalon}:`, error);
      salonsMap[idForm] = { nom: "Erreur de chargement" };
    });
}

function getSalonName(formulaire) {
  if (salonsMap[formulaire.idForm]) {
    return salonsMap[formulaire.idForm].nom || "Salon sans nom";
  }
  return "Chargement...";
}

onMounted(() => {
  getFormData();
});
</script>

<template>
  <div class="container">
    <h2>Liste des formulaires</h2>

    <table class="data-table">
      <thead>
      <tr>
        <th>ID Formulaire</th>
        <th>Statut</th>
        <th>Salon</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="form in data.formulaires" :key="form.idForm">
        <td>{{ form.idForm }}</td>
        <td>
            <span class="status" :class="{ 'active': form.actif, 'archived': !form.actif }">
              {{ form.actif ? 'Actif' : 'Archivé' }}
            </span>
        </td>
        <td>{{ getSalonName(form) }}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<style scoped>
.container {
  padding: 20px;
}

.data-table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}

.data-table th, .data-table td {
  padding: 12px;
  text-align: left;
  border-bottom: 1px solid #ddd;
}

.data-table th {
  background-color: #f2f2f2;
  font-weight: bold;
}

.status {
  padding: 6px 12px;
  border-radius: 4px;
  font-weight: 500;
}

.active {
  background-color: #e6f7e6;
  color: #28a745;
}

.archived {
  background-color: #f8f9fa;
  color: #6c757d;
}
</style>

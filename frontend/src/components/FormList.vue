<script setup>
import doAjaxRequest from "@/util/util.js";
import { onMounted, reactive } from "vue";

let data = reactive({
  formulaires: [],
});

let salonsMap = reactive({});

async function getFormData() {
  try {
    const result = await doAjaxRequest('/api/formulaires');

    // Réinitialiser le tableau avant d'ajouter de nouveaux formulaires
    data.formulaires = [];

    // Ajout des formulaires
    for (let formulaire of result._embedded.formulaires) {
      // Extraire l'ID du salon à partir de l'URL
      if (formulaire._links && formulaire._links.salon && formulaire._links.salon.href) {
        const salonUrl = formulaire._links.salon.href;
        // Attendre que l'ID soit extrait
        const idSalon = await extractIdFromUrl(salonUrl);

        // Attacher l'ID du salon au formulaire pour référence
        formulaire.idSalon = idSalon;

        // Récupérer les détails du salon
        getSalonDetails(idSalon, formulaire.idForm);
      }

      data.formulaires.push(formulaire);
    }
  } catch (error) {
    alert(error.message);
  }
}

async function extractIdFromUrl(url) {
  const parts = url.split('/api');
  console.log(parts[1]);
  const nvUrl = "/api" + parts[1];

  try {
    const result = await doAjaxRequest(nvUrl);
    console.log(result);
    console.log("blabla dans la requete", parseInt(result.idSalon));
    return parseInt(result.idSalon);
  } catch (error) {
    console.error("Erreur lors de l'extraction de l'ID du salon:", error);
    return null;
  }
}

function getSalonDetails(idSalon, idForm) {
  console.log(idSalon);
  console.log(idForm);
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
  extractIdFromUrl("/api/formulaires/4/salon");
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
.data-table thead {
  background-color: #5F4E9B !important;
}

.data-table th {
  padding: 12px;
  color: white;
  font-weight: bold;
  font-size: 16px;
  border: 1px solid #4a3d7c;
  position: relative;
}
</style>

<template>
  <div class="container">
        <div>

      <table>
        <thead>
        <tr>
          <th>id</th>
          <th>nom</th>
          <th>prenom</th>
          <th>numTel</th>
          <th>mail</th>
          <th>ville</th>
          <th>departement</th>
          <th>codePostal</th>
        </tr>
        </thead>
        <tbody>
        <!-- Une ligne pour chaque pays -->
        <tr v-for="prospect in data.prospects" :key="prospect.idProspect">
          <td>{{ prospect.idProspect }}</td>
          <td>{{ prospect.nom }}</td>
          <td>{{ prospect.prenom }}</td>
          <td>{{ prospect.numTel }}</td>
          <td>{{ prospect.mail }}</td>
          <td>{{ prospect.ville }}</td>
          <td>{{ prospect.departement }}</td>
          <td>{{ prospect.codePostal }}</td>

        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script setup>
import {onMounted, reactive} from "vue";

import doAjaxRequest from "@/util/util.js"

const prospectVide = {
  nom:"",
  prenom:"",
  numTel:"",
  mail:"",
  ville:"",
  departement:"",
  codePostal:""
};

let data = reactive({
  // Les données saisies dans le formulaire
  formulaire: { ...prospectVide },
  // Les pays récupérés depuis l'API
  prospects : [],
});


function getProspect(){
  doAjaxRequest("/api/prospects")
  .then((result) => {
      data.prospects = result._embedded.prospects;
    })
      .catch(error => alert(error.message));
}


onMounted(getProspect);
</script>

<style scoped>
.container {
  margin: 2rem auto;
  max-width: 100%; /* Assure que le conteneur utilise toute la largeur disponible */
  background-color: #f9f9f9;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
  overflow-x: auto; /* Ajoute un défilement horizontal si nécessaire */
}

.form-group {
  margin-bottom: 15px;
}

.label {
  display: block;
  font-weight: bold;
  margin-bottom: 5px;
}

.input-field {
  width: 100%;
  padding: 10px;
  font-size: 1rem;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.input-field:focus {
  outline: none;
  border-color: #007BFF;
  box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
}

.button-container {
  text-align: center;
}

.submit-button {
  padding: 10px 20px;
  font-size: 1rem;
  color: #fff;
  background-color: #007BFF;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.submit-button:hover {
  background-color: #0056b3;
}

.table-container {
  margin-top: 20px;
}

table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 10px;
  table-layout: auto; /* Permet aux colonnes de s'ajuster automatiquement */
}

thead th {
  background-color: #007BFF;
  color: #fff;
  padding: 10px;
  text-align: left;
  border: 1px solid #ddd;
}

tbody td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
  white-space: nowrap; /* Empêche le retour à la ligne */
}

tbody tr:nth-child(odd) {
  background-color: #f2f2f2;
}

tbody tr:hover {
  background-color: #eaeaea;
}

th, td {
  font-size: 0.9rem;
  text-align: left;
}

code {
  color: #d63384;
  font-weight: bold;
  background-color: #f8f9fa;
  padding: 2px 4px;
  border-radius: 3px;
}
</style>

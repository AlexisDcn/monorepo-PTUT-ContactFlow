<template>
  <div class="container">
    <!-- Un formulaire pour saisir les valeurs du pays à ajouter -->
    <form @submit.prevent="ajoutePays">
      <div>
        <div>
          <label for="code">Code ISO : </label>
          <input id="code" v-model="data.formulaire.code" size="2" maxlength="2" />
        </div>
        <div>
          <label for="name">Nom du pays : </label>
          <input id="name" v-model="data.formulaire.name" />
        </div>
        <div>
          <button type="submit">Ajouter</button>
        </div>
      </div>
    </form>
    <!-- Création dynamique du Formulaire -->
    <div>
      <form @submit.prevent="ajouteProspect">
      <input v-for="champ in data.champs"
             v-model="data.formulaire[champ.nom]"
             :type="champ.type" :placeholder="champ.placeholders"
      />
        <button type="submit">Ajouter</button>
      </form>
      <!-- Un tableau pour afficher la liste des pays -->
      <!-- <table>
        <thead>
        <tr>
          <th>Code</th>
          <th>Nom</th>
        </tr>
        </thead>
        <tbody>
         Une ligne pour chaque pays
        <tr v-for="country in data.prospects" :key="country.idProspect">
          <td>{{ country.idProspect }}</td>
          <td>{{ country.nom }}</td>
        </tr>
        </tbody>
      </table>
      -->
    </div>
  </div>
</template>

<script setup>
import { onMounted, reactive, defineProps } from 'vue'
// Importer la fonction doAjaxRequest qui gère les erreurs d'API
import doAjaxRequest from '@/util/util.js'


// Récupère le props du salon
const props = defineProps(["idSalon"]);
// Pour réinitialiser le formulaire
// const prospectVide = {
//   nom: '',
//   prenom: '',
//   num_tel: '',
//   mail: '',
//   ville: '',
//   departement: '',
//   code_postal: '',
// }

// Les données du composant
let data = reactive({
  // Les données saisies dans le formulaire
  formulaire: { salon: `/api/salons/${parseInt(props.idSalon)}` },
  prospects: [],
  champs: [],
})

function ajouteProspect() {
  // Ajouter un pays avec les données du formulaire
  const options = {
    // Options de la requête fetch
    method: 'POST', // Verbe HTTP POST pour ajouter un enregistrement
    // On transmet les données du formulaire dans le corps de la requête
    body: JSON.stringify(data.formulaire),
    headers: {
      'Content-Type': 'application/json',
    },
  }
  console.log(JSON.stringify(data.formulaire))

  // On appelle l'API REST générée par les repositories Spring Data REST
  doAjaxRequest('/api/prospects', options)
    .then((result) => {
      console.log('Prospect ajouté :', result)
      getProspect()
      // Réinitialiser le formulaire
      // data.formulaire = { }
      // refresh() // Rafraîchir la liste des pays
    })
    .catch((error) => alert(error.message))
}

function getProspect() {
  doAjaxRequest('/api/prospects')
    .then((result) => {
      data.prospects = result._embedded.prospects
      console.log(data.prospects)
    })
    .catch((error) => alert(error.message))
}

function getChamp() {
  doAjaxRequest('/api/champs')
    .then((result) => {
      data.champs = result._embedded.champs

      // Création du dico qui permet l'envoi des données
      for (let elmnt of data.champs){
        // Vérifie que l'entrée du dictionnaire n'existe pas avant de la créer
        // Sinon boucle infinie
        if ( !data.formulaire[elmnt.nom] ){
          data.formulaire[elmnt.nom] =""
        }
      }
    })
    .catch((error) => alert(error.message))
}

// Appeler la fonction refresh() pour récupérer la liste des pays au chargement du composant
onMounted(() => {
  getProspect()
  getChamp()
})
</script>

<!-- Un CSS pour ce composant -->
<style scoped>
.container {
  margin: 2rem auto;
  max-width: 400px;
  background-color: #f9f9f9;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
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
  border-color: #007bff;
  box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
}

.button-container {
  text-align: center;
}

.submit-button {
  padding: 10px 20px;
  font-size: 1rem;
  color: #fff;
  background-color: #007bff;
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
  border-collapse: collapse; /* Supprime les bordures en double */
  margin-top: 10px;
}

thead th {
  background-color: #007bff;
  color: #fff;
  padding: 10px;
  text-align: left; /* Alignement à gauche */
  border: 1px solid #ddd;
}

tbody td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left; /* Alignement à gauche */
}

tbody tr:nth-child(odd) {
  background-color: #f2f2f2; /* Lignes alternées pour une meilleure lisibilité */
}

tbody tr:hover {
  background-color: #eaeaea;
}

th,
td {
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

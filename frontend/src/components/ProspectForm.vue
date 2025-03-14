<template>
  <div class="container">
    <!-- Création dynamique du Formulaire d'ajout de prospect -->
    <div>

      <form @submit.prevent="ajouteProspect">
      <input v-for="champ in data.champs"
             v-model="data.formulaire[champ.nom]"
             :type="champ.type" :placeholder="champ.placeholders"
      />
        <button type="submit">Ajouter</button>
      </form>
    </div>
  </div>
</template>

<script setup>
import { onMounted, reactive, defineProps } from 'vue'
// Importer la fonction doAjaxRequest qui gère les erreurs d'API
import doAjaxRequest from '@/util/util.js'


// Récupère le props du salon
const props = defineProps(["idSalon", "idForm"]);


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

function getChampFormu(idForm) {
  doAjaxRequest(`/rest/getChampFormu/${idForm}`)
    .then((result) => {
      data.champs = result.champs

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
  getChampFormu(parseInt(props.idSalon))
})
</script>

<!-- Un CSS pour ce composant -->
<style scoped>

</style>

<template>
  <div class="container">
    <!-- Formulaire sans sections -->
    <div class="form-wrapper">
      <h2>Formulaire</h2>
      <div class="form-fields">
        <template v-for="champ in data.champs" :key="champ.idChamp || champ.nom">
          <!-- Champ de type texte standard -->
          <input
            v-if="champ.type !== 'select'"
            class="champ"
            v-model="data.formulaire[champ.nom]"
            :type="champ.type || 'text'"
            :placeholder="champ.placeholder || champ.placeholders"
          />

          <!-- Champ de type select (dropdown) -->
          <select
            v-else
            class="champ dropdown"
            v-model="data.formulaire[champ.nom]"
          >
            <option value="" disabled selected>{{ champ.placeholder || champ.placeholders }}</option>
            <option v-for="option in champ.options" :value="option.value" :key="option.value">
              {{ option.label }}
            </option>
          </select>
        </template>
      </div>

      <button class="bouton" @click="ajouteProspect">Envoyer</button>
    </div>
  </div>
</template>

<script setup>
import { onMounted, reactive, defineProps } from 'vue'
// Importer la fonction doAjaxRequest qui gère les erreurs d'API
import doAjaxRequest from '@/util/util.js'
import router from "@/router/index.js";

// Récupère le props du salon
const props = defineProps(["idSalon", "idForm"]);


// Les données du composant
let data = reactive({
  // Les données saisies dans le formulaire
  formulaire: { salon: `/api/salons/${parseInt(props.idSalon)}` },
  prospects: [],
  champs: []
})

function ajouteProspect() {
  const options = {
    method: 'POST',
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
      router.push('/formulaire-valide');
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
      for (let elmnt of data.champs) {
        // Vérifie que l'entrée du dictionnaire n'existe pas avant de la créer
        if (!data.formulaire[elmnt.nom]) {
          data.formulaire[elmnt.nom] = ""
        }
      }

      // Conversion des types spécifiques
      for (let champ of data.champs) {
        if (champ.nom.toLowerCase() === 'sexe') {
          champ.type = 'select';
          champ.options = [
            { value: 'H', label: 'Homme' },
            { value: 'F', label: 'Femme' },
            { value: 'A', label: 'Autre' }
          ];
        }
      }
    })
    .catch((error) => alert(error.message))
}

function getFormInfo(idForm) {
  doAjaxRequest(`/api/formulaires/${idForm}`)
    .then((result) => {
      console.log(result);
    })
    .catch((error) => alert(error.message))
}

// Appeler les fonctions au chargement du composant
onMounted(() => {
  getProspect()
  getChampFormu(parseInt(props.idSalon))
  console.log("test", props)
})
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  max-width: 700px;
  margin: 40px auto;
  font-family: Arial, sans-serif;
}

.form-wrapper {
  background-color: #6b5b95;
  border-radius: 20px;
  padding: 20px;
  margin-bottom: 25px;
  width: 100%;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

h2 {
  color: white;
  font-size: 20px;
  margin-bottom: 15px;
  font-weight: normal;
}

.form-fields {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.champ {
  padding: 12px 15px;
  border: none;
  border-radius: 25px;
  background-color: white;
  font-size: 16px;
  width: 100%;
  box-sizing: border-box;
}

.dropdown {
  appearance: none;
  background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='12' height='12' fill='%23333' viewBox='0 0 16 16'%3E%3Cpath d='M7.247 11.14L2.451 5.658C1.885 5.013 2.345 4 3.204 4h9.592a1 1 0 0 1 .753 1.659l-4.796 5.48a1 1 0 0 1-1.506 0z'/%3E%3C/svg%3E");
  background-repeat: no-repeat;
  background-position: calc(100% - 15px) center;
  padding-right: 35px;
}

.bouton {
  padding: 12px 15px;
  background-color: #ff6b6b;
  color: white;
  border: none;
  border-radius: 25px;
  cursor: pointer;
  font-size: 16px;
  margin-top: 20px;
  align-self: center;
  min-width: 120px;
  transition: all 0.2s ease;
}

.bouton:hover {
  background-color: #e06363;
  transform: scale(1.02);
}

@media (max-width: 768px) {
  .container {
    padding: 0 15px;
  }

  .form-wrapper {
    border-radius: 15px;
  }
}
</style>

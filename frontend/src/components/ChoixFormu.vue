<script setup>


import {onMounted, reactive} from "vue";
import doAjaxRequest from "@/util/util.js";
import { useRouter } from 'vue-router';

// Définition de la liste des formulaires réactive
const listeForm = reactive([]);

// Définition du router
const router = useRouter();

function getFormulaires(){
  doAjaxRequest('/rest/formulairesActifs')
    .then((result) => {
      console.log(result)
      for (let elmnt of result.formulaire){
          listeForm.push(elmnt)
      }
    })
    .catch((error) => alert(error.message))
}

function navFormu(id){
  router.push("/formulaire/" + id)
}

onMounted(()=>
getFormulaires()
)

</script>

<template>
  <div class="container">
    <div>
      <v-card class="card " title="Accueil Temporaire" elevation="16">
        Dans cet accueil temporaire il est possible de choisir le formulaire à remplir (parmis les formulaires actifs)
      </v-card>
    </div>
    <div class="listeB">
      <button v-for="formu in listeForm" class="form-button" @click="navFormu(formu.idForm)" :idSalon="formu.salon.idSalon"> Formulaire {{ formu.idForm }}</button>
    </div>
  </div>


</template>

<style scoped>

.container {
  align-items: center;
  justify-content: center;
  margin: 10% auto auto;
}

.card{
  background-color: #5f4e9b !important; /* Bleu clair */
  padding: 20px;
  border-radius: 12px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  text-align: center;
  max-width: 600px;
  color: white;
}

.listeB {
  display: flex;
  gap: 15px;
  margin-top: 20px;
}

.form-button {
  width: 80%;
  max-width: 300px;
  padding: 10px;
  margin: 10px;
  background-color: #2f2769;
  color: white;
  border: none;
  border-radius: 8px;
  font-size: 16px;
  font-weight: bold;
  cursor: pointer;
}

.form-button:hover {
  transform: scale(1.02) !important;
  filter: brightness(0.9) !important;
}
</style>

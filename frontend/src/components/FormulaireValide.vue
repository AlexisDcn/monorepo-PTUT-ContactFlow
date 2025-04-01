<template>
  <v-container class="confirmation-container">
    <v-card class="confirmation-card">
      <v-card-title class="headline">
        Vos informations ont bien été prises en compte,<br />
        Nous vous remercions de votre retour !
      </v-card-title>
      <v-card-actions>
        <button class="envoie-btn" @click="retourFormulaire">Renvoyer un formulaire</button>
      </v-card-actions>
    </v-card>
  </v-container>
</template>

<script setup>
import { useRouter } from 'vue-router';

const router = useRouter();

function retourFormulaire() {
  // Redirige vers la page du formulaire précédent
  router.back();
}
</script>

<style scoped>
.confirmation-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.confirmation-card {
  max-width: 600px;
  padding: 20px;
  text-align: center;
  background-color: #5f4e9b; /* Bleu clair */
  border-radius: 12px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.envoie-btn{
  width: 80%;
  max-width: 300px;
  padding: 10px;
  margin: 10px;
  background-color: #ed6962;
  color: white;
  border: none;
  border-radius: 8px;
  font-size: 16px;
  font-weight: bold;
  cursor: pointer;
}

.envoie-btn:hover{
  transform: scale(1.02) !important;
  filter: brightness(0.9) !important;
}

.headline {
  font-size: 1.5rem;
  color: white;
}
</style>

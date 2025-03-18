<template>
  <v-container class="confirmation-container">
    <v-card class="confirmation-card">
      <v-card-title class="headline">
        Vos informations ont bien été prises en compte,<br />
        Nous vous remercions de votre retour !
      </v-card-title>
      <v-card-actions>
        <v-btn color="primary" @click="retourFormulaire">Renvoyer un formulaire</v-btn>
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
  background-color: #d0ebff; /* Bleu clair */
  border-radius: 12px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.headline {
  font-size: 1.5rem;
  color: #333;
}
</style>

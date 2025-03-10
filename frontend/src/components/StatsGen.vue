<template>
  <v-container class="container fill-height">
    <v-btn @click="retourAccueil" color="secondary" class="mb-4">Retour</v-btn>
    <v-data-table :headers="headers" :items="data.prospects" item-value="idProspect">
      <template v-slot:item="{ item }">
        <tr>
          <td>{{ item.idProspect }}</td>
          <td>{{ item.nom }}</td>
          <td>{{ item.prenom }}</td>
          <td>{{ item.numTel }}</td>
          <td>{{ item.mail }}</td>
          <td>{{ item.ville }}</td>
          <td>{{ item.departement }}</td>
          <td>{{ item.codePostal }}</td>
        </tr>
      </template>
    </v-data-table>
  </v-container>
</template>

<script setup>
import { onMounted, reactive } from "vue";
import doAjaxRequest from "@/util/util.js";
import { useRouter } from 'vue-router';

const prospectVide = {
  nom: "",
  prenom: "",
  numTel: "",
  mail: "",
  ville: "",
  departement: "",
  codePostal: "",
};

let data = reactive({
  formulaire: { ...prospectVide },
  prospects: [],
});

const headers = [
  { text: 'ID', value: 'idProspect' },
  { text: 'Nom', value: 'nom' },
  { text: 'Prénom', value: 'prenom' },
  { text: 'Numéro de Téléphone', value: 'numTel' },
  { text: 'Email', value: 'mail' },
  { text: 'Ville', value: 'ville' },
  { text: 'Département', value: 'departement' },
  { text: 'Code Postal', value: 'codePostal' },
];

const router = useRouter();

function retourAccueil() {
  router.push('/');
}

function getProspect() {
  doAjaxRequest("/api/prospects")
    .then((result) => {
      data.prospects = result._embedded.prospects;
    })
    .catch((error) => alert(error.message));
}

onMounted(getProspect);
</script>

<style scoped>
.container {
  margin: 2rem auto;
  max-width: 100%;
  background-color: #f9f9f9;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
  overflow-x: auto;
}

.fill-height {
  height: 100%;
}
</style>

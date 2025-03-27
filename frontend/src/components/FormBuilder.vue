<template>
  <v-container class="form-builder">
    <v-row>
      <v-col>
        <h2>Créer un nouveau formulaire</h2>
        <div v-for="(section, index) in formSections" :key="index" class="form-section">
          <v-subheader>Section {{ index + 1 }}</v-subheader>
          <v-list>
            <v-list-item v-for="(champ, champIndex) in section.champs" :key="champIndex">
              <v-list-item-content>
                <v-list-item-title>{{ champ.nom }}</v-list-item-title>
              </v-list-item-content>
              <v-list-item-action>
                <v-btn icon @click="removeChamp(index, champIndex)">
                  <v-icon>mdi-delete</v-icon>
                </v-btn>
              </v-list-item-action>
            </v-list-item>
          </v-list>
          <v-text-field
            v-model="newChampName"
            label="Nom du champ"
          ></v-text-field>
          <v-btn @click="addChamp(index)">Ajouter un champ</v-btn>
          <v-btn @click="removeSection(index)">Supprimer la section</v-btn>
        </div>
        <v-btn @click="addSection">Ajouter une section</v-btn>
        <v-btn @click="saveForm">Enregistrer le formulaire</v-btn>
      </v-col>
    </v-row>
  </v-container>
</template>

<script setup>
import {ref} from 'vue';
import doAjaxRequest from '@/util/util.js';
import {useRouter} from 'vue-router';

const formSections = ref([
  {
    champs: [
      {nom: 'Nom', type: 'text'},
      {nom: 'Email', type: 'email'}
    ]
  }
]);

const newChampName = ref('');
const router = useRouter();

function addSection() {
  formSections.value.push({
    champs: []
  });
}

function addChamp(sectionIndex) {
  if (newChampName.value.trim() !== '') {
    formSections.value[sectionIndex].champs.push({
      nom: newChampName.value,
      type: 'text' // Vous pouvez ajuster le type selon vos besoins
    });
    newChampName.value = ''; // Réinitialiser le nom du champ après ajout
  }
}

function removeSection(sectionIndex) {
  formSections.value.splice(sectionIndex, 1);
}

function removeChamp(sectionIndex, champIndex) {
  formSections.value[sectionIndex].champs.splice(champIndex, 1);
}

function saveForm() {
  const formulaireData = {
    actif: true, // Par défaut, le formulaire est actif
    salon: "1", // Remplacez par l'identifiant du salon approprié
    champs: formSections.value.flatMap(section =>
      section.champs.map(champ => ({
        nom: champ.nom,
        type: champ.type
      }))
    )
  };

  const options = {
    method: 'POST',
    body: JSON.stringify(formulaireData),
    headers: {
      'Content-Type': 'application/json',
    },
  };

  // Afficher les données qui seront envoyées dans la requête POST
  console.log('Données envoyées dans la requête POST :', JSON.stringify(formulaireData, null, 2));

  doAjaxRequest('/api/formulaires', options)
    .then((result) => {
      console.log('Formulaire enregistré :', result);
      router.push('/formulaire-valide');
    })
    .catch((error) => alert(error.message));
}
</script>

<style scoped>
.form-builder {
  max-width: 600px;
  margin: auto;
}

.form-section {
  margin-bottom: 20px;
}
</style>

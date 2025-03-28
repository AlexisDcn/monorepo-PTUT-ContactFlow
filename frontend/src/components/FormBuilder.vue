<template>
  <div class="form-builder">
    <div class="form-container">
      <h2>Créer un nouveau formulaire</h2>

      <!-- Ajout du select pour choisir un salon -->
      <div>
        <label for="salon-select">Choisissez un salon :</label>
        <select v-model="selectedSalon" id="salon-select">
          <option v-for="salon in listSalon" :key="salon.idSalon" :value="salon.idSalon">
            {{ salon.nom }}
          </option>
        </select>
      </div>

      <div v-for="(section, index) in formSections" :key="index" class="form-section">
        <h3>Section {{ index + 1 }}</h3>
        <ul>
          <li v-for="(champ, champIndex) in section.champs" :key="champIndex" class="champ-item">
            <span>{{ champ.nom }}</span>
            <button @click="removeChamp(index, champIndex)">Supprimer</button>
          </li>
        </ul>
        <input
          v-model="newChampName"
          type="text"
          placeholder="Nom du champ"
        />
        <button @click="addChamp(index)">Ajouter un champ</button>
        <button @click="removeSection(index)">Supprimer la section</button>
      </div>
      <button @click="addSection">Ajouter une section</button>
      <button @click="saveForm">Enregistrer le formulaire</button>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue';
import doAjaxRequest from '@/util/util.js';
import { useRouter } from 'vue-router';

const formSections = ref([
  {
    champs: [
      { nom: 'Nom', type: 'text' },
      { nom: 'Email', type: 'email' }
    ]
  }
]);

const newChampName = ref('');
const router = useRouter();

// Ajout des variables pour la liste des salons et le salon sélectionné
const listSalon = reactive([]);
const selectedSalon = ref(null);

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
    salon: `/api/salons/${selectedSalon.value}`,
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

// Ajout de la fonction pour récupérer les salons
function getSalon() {
  let premierTour = false;
  doAjaxRequest('/api/salons')
    .then((result) => {
      console.log(result._embedded);
      for (let elmnt of result._embedded.salons) {
        if (!premierTour) {
          selectedSalon.value = elmnt.idSalon;
          premierTour = true;
        }
        listSalon.push(elmnt);
      }
      console.log(listSalon);
    })
    .catch((error) => alert(error.message));
}

// Appeler getSalon lors du montage du composant
onMounted(() => {
  getSalon();
});
</script>

<style scoped>
.form-builder {
  max-width: 600px;
  margin: auto;
}

.form-section {
  margin-bottom: 20px;
}

.champ-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

button {
  margin-top: 10px;
}
</style>

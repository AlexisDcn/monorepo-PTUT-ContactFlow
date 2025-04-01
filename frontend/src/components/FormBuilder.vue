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
            <button @click="removeChamp(index, champIndex)" class="delete-btn">
              Supprimer<span class="icon">🗑️</span>
            </button>

          </li>
        </ul>
        <input
          v-model="newChampName"
          type="text"
          placeholder="Nom du champ"
        />
        <button @click="addChamp(index)" class="add-field-btn">
          Ajouter un champ <span class="icon">＋</span>
        </button>
        <button @click="removeSection(index)" class="delete-section-btn">
          Supprimer la section <span class="icon">🗑️</span>
        </button>
      </div>
      <button @click="addSection" class="add-section-btn">
        Ajouter une section <span class="icon">＋</span>
      </button>
      <button @click="saveForm" class="error-btn">
        Publier le formulaire <span class="icon">✉️</span>
      </button>
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
  max-width: 700px;
  margin: auto;
  font-family: Arial, sans-serif;
}

.form-container {
  background-color: #5f4e9b;
  padding: 20px;
  border-radius: 12px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
  color: white;
}

h2, h3 {
  margin-bottom: 10px;
}

.form-section {
  background-color: #2f2769;
  padding: 15px;
  border-radius: 10px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  margin-bottom: 15px;
}

ul {
  list-style-type: none;
  padding: 0;
}

.champ-item {
  display: flex;
  align-items: center;
  justify-content: space-between;
  background: white;
  padding: 8px;
  border-radius: 20px;
  margin-bottom: 10px;
}

.champ-item span {
  flex-grow: 1;
  margin-left: 10px;
  color: black;
}

.champ-item button {
  background: none;
  border: none;
  color: #ed6962;
  cursor: pointer;
  font-size: 18px;
}

input {
  width: calc(100% - 20px);
  padding: 8px;
  border-radius: 5px;
  border: none;
  margin-top: 5px;
}

button {
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #5f4e9b;
  color: white;
  border: none;
  padding: 10px 15px;
  border-radius: 20px;
  cursor: pointer;
  font-weight: bold;
  transition: transform 0.2s, filter 0.2s;
  margin-top: 10px;
}

button:hover {
  transform: scale(1.02);
  filter: brightness(0.9);
}

.error-btn {
  background-color: #ed6962;
}

.error-btn:hover {
  filter: brightness(0.9);
}

/* Icônes */
.icon {
  font-size: 18px;
  margin-left: 5px;
}

/* Espacement entre les sections */
.add-section-btn {
  background-color: #2f2769;
  margin-top: 15px;
}



</style>

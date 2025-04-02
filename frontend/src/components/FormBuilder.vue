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

      <div class="form-fields">
        <ul>
          <li v-for="(champ, champIndex) in formChamps" :key="champIndex" class="champ-item">
            <span>{{ champ.nom }}</span>
            <button @click="removeChamp(champIndex)" class="delete-btn">
              Supprimer<span class="icon">🗑️</span>
            </button>
          </li>
        </ul>
        <input
          v-model="newChampName"
          type="text"
          placeholder="Nom du champ"
        />
        <input
          v-model="newChampPlaceholder"
          type="text"
          placeholder="Placeholder du champ"
        />
        <button @click="addChamp" class="add-field-btn">
          Ajouter un champ <span class="icon">＋</span>
        </button>
      </div>
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

const formChamps = ref([]);
const newChampName = ref('');
const newChampPlaceholder = ref('');
const router = useRouter();

// Ajout des variables pour la liste des salons et le salon sélectionné
const listSalon = reactive([]);
const selectedSalon = ref(null);

function addChamp() {
  if (newChampName.value.trim() !== '') {
    formChamps.value.push({
      nom: newChampName.value,
      type: 'text',
      placeholders: newChampPlaceholder.value
    });
    newChampName.value = '';
    newChampPlaceholder.value = '';
  }
}

function removeChamp(champIndex) {
  formChamps.value.splice(champIndex, 1);
}

async function saveForm() {
  try {
    // Créer les champs
    const allChamps = [];

    for (const champ of formChamps.value) {
      const champsData = {
        ...champ
      };

      const response = await doAjaxRequest('/rest/createChamp', {
        method: 'POST',
        body: JSON.stringify(champsData),
        headers: {
          'Content-Type': 'application/json',
        },
      });

      allChamps.push({
        idChamp: response.idChamp
      });
    }

    // Créer le formulaire
    const formulaireData = {
      actif: true,
      salon: {idSalon: selectedSalon.value}
    };

    const formulaireResponse = await doAjaxRequest('/rest/createFormulaire', {
      method: 'POST',
      body: JSON.stringify(formulaireData),
      headers: {
        'Content-Type': 'application/json',
      },
    });

    const formulaireId = formulaireResponse.idForm;

    // Créer les relations Contient pour chaque champ
    for (const champ of allChamps) {
      await doAjaxRequest('/rest/createContient', {
        method: 'POST',
        body: JSON.stringify({
          champId: champ.idChamp,
          formulaireId: formulaireId
        }),
        headers: {
          'Content-Type': 'application/json',
        },
      });
    }

    console.log('Formulaire et champs enregistrés avec succès');
    router.push('/formulaire-valide');
  } catch (error) {
    alert('Erreur lors de l\'enregistrement du formulaire : ' + error.message);
  }
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

h2 {
  margin-bottom: 10px;
}

.form-fields {
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
  background-color: #5f4e9b !important;
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
  background-color: #ed6962 !important;
}

.error-btn:hover {
  filter: brightness(0.9);
}

/* Icônes */
.icon {
  font-size: 18px;
  margin-left: 5px;
}

.add-field-btn {
  background-color: #2f2769;
  margin-top: 15px;
}
</style>

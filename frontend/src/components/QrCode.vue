<template>
  <div class="qr-generator">
    <h2>Générateur de QR Code</h2>

    <div class="form-group">
      <label for="url">URL à encoder:</label>
      <input
        type="text"
        id="url"
        v-model="url"
        class="form-control"
        placeholder="https://votre-app.com/page"
      >
    </div>

    <div class="form-group">
      <label for="fileName">Nom du fichier:</label>
      <input
        type="text"
        id="fileName"
        v-model="fileName"
        class="form-control"
        placeholder="mon-qr-code"
      >
    </div>

    <div class="form-group">
      <label for="format">Format d'image:</label>
      <select id="format" v-model="format" class="form-control">
        <option value="png">PNG</option>
        <option value="jpeg">JPEG</option>
      </select>
    </div>

    <div class="form-group">
      <label for="size">Taille (pixels):</label>
      <input
        type="number"
        id="size"
        v-model.number="size"
        class="form-control"
        min="100"
        max="1000"
      >
    </div>

    <button @click="generateQRCode" class="btn primary-btn" :disabled="!isValidUrl">
      Générer
    </button>

    <div v-if="previewUrl" class="qr-preview">
      <h3>Aperçu:</h3>
      <img :src="previewUrl" alt="QR Code Preview">
      <button @click="downloadQRCode" class="btn success-btn">
        Télécharger
      </button>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue';
import axios from 'axios';

const url = ref('');
const fileName = ref('mon-qr-code');
const format = ref('png');
const size = ref(250);
const previewUrl = ref(null);
const qrCodeBlob = ref(null);

const isValidUrl = computed(() => url.value.trim() !== '');
const finalFileName = computed(() => fileName.value.trim() || 'qr-code');

async function generateQRCode() {
  if (!isValidUrl.value) return;

  try {
    const response = await axios.post('/api/qrcode/generate', {
      url: url.value,
      fileName: finalFileName.value,
      format: format.value,
      size: size.value
    }, {
      responseType: 'blob'
    });

    const blob = new Blob([response.data], {
      type: format.value === 'png' ? 'image/png' : 'image/jpeg'
    });
    qrCodeBlob.value = blob;
    previewUrl.value = URL.createObjectURL(blob);
  } catch (error) {
    console.error('Erreur lors de la génération du QR code:', error);
    alert('Une erreur est survenue lors de la génération du QR code.');
  }
}

function downloadQRCode() {
  if (!qrCodeBlob.value) return;

  const link = document.createElement('a');
  link.href = URL.createObjectURL(qrCodeBlob.value);
  link.download = `${finalFileName.value}.${format.value}`;
  link.click();
}
</script>

<style scoped>
.qr-generator {
  max-width: 500px;
  margin: 0 auto;
  padding: 20px;
  border-radius: 10px;
  background: #f5f5f5;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  text-align: center;
}

.form-group {
  margin-bottom: 15px;
  text-align: left;
}

.form-control {
  width: 100%;
  padding: 10px;
  margin-top: 5px;
  border: 1px solid #2f2769;
  border-radius: 5px;
}

.btn {
  padding: 10px 20px;
  cursor: pointer;
  border: none;
  border-radius: 5px;
  transition: transform 0.2s ease, filter 0.2s ease;
}

.primary-btn {
  background-color: #2f2769;
  color: white;
}

.success-btn {
  background-color: #5f4e9b;
  color: white;
}

.btn:hover {
  transform: scale(1.05);
  filter: brightness(0.9);
}

.qr-preview {
  margin-top: 20px;
  text-align: center;
  padding: 15px;
  border-radius: 10px;
  background: #fff;
}

.qr-preview img {
  max-width: 100%;
  height: auto;
  margin: 10px 0;
  border: 2px solid #2f2769;
  border-radius: 5px;
}
</style>

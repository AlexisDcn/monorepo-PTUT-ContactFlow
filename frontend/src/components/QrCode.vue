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

    <button @click="generateQRCode" class="btn btn-primary" :disabled="!isValidUrl">
      Générer et télécharger
    </button>

    <div v-if="previewUrl" class="qr-preview">
      <h3>Aperçu:</h3>
      <img :src="previewUrl" alt="QR Code Preview">
      <button @click="downloadQRCode" class="btn btn-success mt-2">
        Télécharger
      </button>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'QRCodeGenerator',
  data() {
    return {
      url: '',
      format: 'png',
      size: 250,
      previewUrl: null,
      qrCodeBlob: null
    };
  },
  computed: {
    isValidUrl() {
      // Validation URL basique
      return this.url.trim() !== '';
    }
  },
  methods: {
    async generateQRCode() {
      if (!this.isValidUrl) return;

      try {
        const response = await axios.post('/api/qrcode/generate', {
          url: this.url,
          format: this.format,
          size: this.size
        }, {
          responseType: 'blob' // Important pour recevoir des données binaires
        });

        // Créer un URL pour l'aperçu
        const blob = new Blob([response.data], {
          type: this.format === 'png' ? 'image/png' : 'image/jpeg'
        });
        this.qrCodeBlob = blob;
        this.previewUrl = URL.createObjectURL(blob);
      } catch (error) {
        console.error('Erreur lors de la génération du QR code:', error);
        alert('Une erreur est survenue lors de la génération du QR code.');
      }
    },
    downloadQRCode() {
      if (!this.qrCodeBlob) return;

      // Créer un lien pour télécharger
      const link = document.createElement('a');
      link.href = URL.createObjectURL(this.qrCodeBlob);
      link.download = `qrcode.${this.format}`;
      link.click();
    }
  }
};
</script>

<style scoped>
.qr-generator {
  max-width: 500px;
  margin: 0 auto;
  padding: 20px;
}

.form-group {
  margin-bottom: 15px;
}

.form-control {
  width: 100%;
  padding: 8px;
  margin-top: 5px;
}

.btn {
  padding: 8px 16px;
  cursor: pointer;
}

.btn-primary {
  background-color: #4CAF50;
  color: white;
  border: none;
}

.btn-success {
  background-color: #2196F3;
  color: white;
  border: none;
}

.qr-preview {
  margin-top: 20px;
  text-align: center;
}

.qr-preview img {
  max-width: 100%;
  height: auto;
  margin: 10px 0;
  border: 1px solid #ddd;
}
</style>

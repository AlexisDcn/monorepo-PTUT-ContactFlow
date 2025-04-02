<template>
  <footer class="footer-general">
    <div class="footer-content">
      <div class="footer-logo">
        <h2>Contact'Flow</h2>
      </div>

      <div class="footer-section">
        <h3>Contact</h3>
        <p>E-mail Communication ISIS : <a href="mailto:communication-isis@univ-jfc.fr">communication-isis@univ-jfc.fr</a></p>
        <p>Num. de tél. Communication ISIS : <a href="tel:0567530031">05 67 53 00 31</a></p>
      </div>

      <div class="footer-section">
        <h3>Liens rapides</h3>
        <p><a href="/">Accueil</a></p>
        <p><a href="/login">Se Connecter</a></p>
      </div>

      <div class="footer-section">
        <h3>Légal</h3>
        <p><a href="#">Conditions d'utilisation</a></p>
        <p><a href="#">Politique de confidentialité</a></p>
        <p><a href="#">Mentions légales</a></p>
      </div>
    </div>

    <div class="footer-bottom">
      <p>© {{ currentYear }} Contact'Flow. Tous droits réservés.</p>
    </div>
  </footer>
</template>

<script setup>
import { computed } from 'vue';

// Année actuelle pour le copyright
const currentYear = computed(() => new Date().getFullYear());
</script>

<style scoped>
.footer-general {
  background-color: #2f2769;
  color: white;
  padding: 2rem 0;
  width: 100%;
}

.footer-content {
  max-width: 1200px;
  margin: 0 auto;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  padding: 0 2rem;
}

.footer-logo {
  flex: 1;
  min-width: 250px;
  margin-bottom: 1.5rem;
}

.footer-logo h2 {
  margin: 0;
  font-size: 1.5rem;
  font-weight: 600;
}

.footer-section {
  flex: 1;
  margin-bottom: 1.5rem;
  min-width: 150px;
  padding: 0 15px;
}

.footer-section h3 {
  font-size: 1rem;
  margin-bottom: 1rem;
  font-weight: 600;
  color: #ffffff;
}

.footer-section p {
  margin-bottom: 0.5rem;
}

.footer-section a {
  color: #d1d1e0;
  text-decoration: none;
  transition: color 0.3s ease;
}

.footer-section a:hover {
  color: #ffffff;
  text-decoration: underline;
}

.footer-bottom {
  text-align: center;
  padding-top: 1.5rem;
  margin-top: 1.5rem;
  border-top: 1px solid rgba(255, 255, 255, 0.1);
  font-size: 0.9rem;
}

@media (max-width: 768px) {
  .footer-content {
    flex-direction: column;
  }

  .footer-section {
    margin-right: 0;
    margin-bottom: 2rem;
    width: 100%;
  }
}
</style>

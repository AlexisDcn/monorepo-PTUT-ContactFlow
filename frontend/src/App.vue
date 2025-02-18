<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <header>
    <img alt="ISIS logo" class="logo" src="@/assets/logo-ISIS-vertical-RVB.svg" width="125" height="125" />
    <h1 class="white">Contact’Flow</h1>
    <div class="wrapper">
      <nav>
        <RouterLink to="/">Home</RouterLink>
        <RouterLink to="/about">Les pays</RouterLink>
      </nav>
    </div>
  </header>

  <RouterView />

  <footer>

  </footer>
</template>

<style scoped>
header {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  z-index: 1000; /* Pour éviter qu'il soit caché par d'autres éléments */
  padding: 1rem;
  line-height: 1.5;
  max-height: 100vh;
  /*background-color: #2F2769;*/
}

.logo {
  display: block;
  margin: 0 auto 2rem;
}

nav {
  justify-content: space-around;
}

nav a {
  color: black; /* Définit la couleur du texte en noir */
  text-decoration: none; /* Enlève le soulignement */
}

nav a:hover {
  color: gray; /* Optionnel : Change la couleur au survol */
}

nav a.router-link-exact-active {
  color: black; /* Assure que le lien actif reste noir */
  background-color: gray;
}




@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  }

  .logo {
    margin: 0 2rem 0 0;
  }




}
</style>

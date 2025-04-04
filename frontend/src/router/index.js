import { createRouter, createWebHistory } from 'vue-router';
import { getAuth, onAuthStateChanged } from 'firebase/auth';
import AdminStatistiquesGeneView from "@/views/AdminStatistiquesGeneView.vue";
import ChoixFormuView from "@/views/ChoixFormuView.vue";
import AdminAccueilView from "@/views/AdminAccueilView.vue";
import AdminGraphiqueEvolutifView from "@/views/AdminGraphiqueEvolutifView.vue";
import AdminOptionsFormuView from "@/views/AdminOptionsFormuView.vue";
import FormuValidView from "@/views/FormuValidView.vue";
import AdminCreationFormuView from "@/views/AdminCreationFormuView.vue";
import AdminVisualisationFormuView from "@/views/AdminVisualisationFormuView.vue";
import LoginView from "@/views/LoginView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/', name: 'choixform', component: ChoixFormuView },
    { path: '/formulaire/:idSalon', name: 'form', component: () => import('@/views/FormulaireView/[id].vue') },
    { path: '/admin', name: 'admin', component: AdminAccueilView, meta: { requiresAuth: true } },
    { path: '/statistiques-generales', name: 'statistiques-generales', component: AdminStatistiquesGeneView, meta: { requiresAuth: true } },
    { path: '/graphique-evolutif', name: 'graphique-evolutif', component: AdminGraphiqueEvolutifView, meta: { requiresAuth: true } },
    { path: '/ajouter-des-options', name: 'ajouter-des-options', component: AdminOptionsFormuView, meta: { requiresAuth: true } },
    { path: '/formulaire-valide', name: 'formulaire-valide', component: FormuValidView },
    { path: '/creation-formu', name: 'creation-formu', component: AdminCreationFormuView, meta: { requiresAuth: true } },
    { path: '/visualisation-formu', name: 'visualisation-formu', component: AdminVisualisationFormuView, meta: { requiresAuth: true } },
    { path: '/login', name: 'login', component: LoginView },
  ],
});

// Protéger les routes admin avec Firebase Auth
router.beforeEach((to, from, next) => {
  if (to.meta.requiresAuth) {
    const auth = getAuth();
    onAuthStateChanged(auth, (user) => {
      if (user) {
        next();
      } else {
        next({ name: 'login' }); // Redirige vers la page de connexion si pas connecté
      }
    });
  } else {
    next();
  }
});

export default router;

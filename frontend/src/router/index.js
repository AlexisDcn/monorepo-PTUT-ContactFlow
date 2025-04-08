import { createRouter, createWebHistory } from 'vue-router';
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
    {
      path: '/',
      name: 'choixform',
      component: ChoixFormuView,
    },
    {
      path: '/formulaire/:idSalon&:idForm',
      name: 'form',
      component: () => import('@/views/FormulaireView/[id].vue'),
    },
    {
      path: '/admin',
      name: 'admin',
      component: AdminAccueilView,
    },
    {
      path: '/statistiques-generales',
      name: 'statistiques-generales',
      component: AdminStatistiquesGeneView,
    },
    {
      path: '/graphique-evolutif',
      name: 'graphique-evolutif',
      component: AdminGraphiqueEvolutifView,
    },
    {
      path: '/ajouter-des-options',
      name: 'ajouter-des-options',
      component: AdminOptionsFormuView,
    },
    {
      path: '/formulaire-valide',
      name: 'formulaire-valide',
      component: FormuValidView,
    },
    {
      path: '/creation-formu',
      name: 'creation-formu',
      component: AdminCreationFormuView,
    },
    {
      path: '/visualisation-formu',
      name: 'visualisation-formu',
      component: AdminVisualisationFormuView,
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView,
    },
  ],
});

export default router;

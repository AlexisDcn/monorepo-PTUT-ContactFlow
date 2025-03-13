import { createRouter, createWebHistory } from 'vue-router'
import StatGenView from "@/views/StatGenView.vue";
import HomeView from "@/views/HomeView.vue";
import AdminView from "@/views/AdminView.vue";
import GraphiqueEvolutifView from "@/views/GraphiqueEvolutifView.vue";
import AjouterDesOptionsView from "@/views/AjouterDesOptionsView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
    {
      path: '/formulaire/:idSalon',
      name:'form',
      component: () => import('../views/FormulaireView/[id].vue'),
    },
    {
      path: '/admin',
      name: 'admin',
      component: AdminView,
    },
    {
      path: '/statistiques-generales',
      name: 'statistiques-generales',
      component: StatGenView,
    },
    {
      path: '/graphique-evolutif',
      name: 'graphique-evolutif',
      component: GraphiqueEvolutifView,
    },
    {
      path: '/ajouter-des-options',
      name: 'ajouter-des-options',
      component: AjouterDesOptionsView,
    },
  ],
})

export default router

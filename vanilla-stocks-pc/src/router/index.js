import { createWebHistory, createRouter } from 'vue-router'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: () => import('@/views/HelloWorld.vue'), // 동적 import
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/Login.vue'),
  },
  {
    path: '/market/status',
    name: 'MarketStatus',
    component: () => import('@/views/MarketStatus.vue'),
  },
]

export const router = createRouter({
  history: createWebHistory(),
  routes,
})
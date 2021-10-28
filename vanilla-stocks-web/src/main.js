import { createApp } from "vue";
import { createWebHistory, createRouter } from "vue-router";
import axios from  "@/assets/js/axios";
import moment from 'moment';
import VueHighcharts from "vue3-highcharts";

// styles

import "@fortawesome/fontawesome-free/css/all.min.css";
import "@/assets/styles/tailwind.css";

// mouting point for the whole app

import App from "@/App.vue";

// layouts

import Admin from "@/layouts/Admin.vue";
import Auth from "@/layouts/Auth.vue";

// views for Admin layout

import Dashboard from "@/views/admin/Dashboard.vue";
import Settings from "@/views/admin/Settings.vue";
import Tables from "@/views/admin/Tables.vue";
import Maps from "@/views/admin/Maps.vue";
import MarketStatus from "@/views/admin/MarketStatus.vue";
import UpjongStatus from "@/views/admin/UpjongStatus.vue";
import ThemeStatus from "@/views/admin/ThemeStatus.vue";

// views for Auth layout

import Login from "@/views/auth/Login.vue";
import Register from "@/views/auth/Register.vue";

// views without layouts

import Landing from "@/views/Landing.vue";
import Profile from "@/views/Profile.vue";
import Index from "@/views/Index.vue";

// routes

const routes = [
  {
    path: "/admin",
    redirect: "/admin/dashboard",
    component: Admin,
    children: [
      {
        path: "/admin/dashboard",
        component: Dashboard,
        meta: {
          title: "DASHBOARD"
        }
      },
      {
        path: "/admin/settings",
        component: Settings,
      },
      {
        path: "/admin/tables",
        component: Tables,
      },
      {
        path: "/admin/maps",
        component: Maps,
      },
      {
        path: "/admin/market",
        name: "MarketStatus",
        component: MarketStatus,
        meta: {
          title: "증시현황"
        }
      },
      {
        path: "/admin/upjong",
        name: "UpjongStatus",
        component: UpjongStatus,
        meta: {
          title: "업종현황"
        }
      },
      {
        path: "/admin/theme",
        name: "ThemeStatus",
        component: ThemeStatus,
        meta: {
          title: "테마현황"
        }
      },
    ],
  },
  {
    path: "/auth",
    redirect: "/auth/login",
    component: Auth,
    children: [
      {
        path: "/auth/login",
        component: Login,
      },
      {
        path: "/auth/register",
        component: Register,
      },
    ],
  },
  {
    path: "/landing",
    component: Landing,
  },
  {
    path: "/profile",
    component: Profile,
  },
  {
    path: "/",
    component: Index,
  },
  { path: "/:pathMatch(.*)*", redirect: "/" },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

const app = createApp(App)
app.config.globalProperties.axios = axios;
app.config.globalProperties.$moment = moment;
app.use(VueHighcharts);
app.use(router).mount("#app");

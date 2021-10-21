<template>
  <div class="wrapper">
    <side-bar>
      <template slot="links">
        <!--
        <sidebar-item
          :link="{
            name: 'Dashboard',
            path: '/dashboard',
            icon: 'ni ni-tv-2 text-primary',
          }"
        >
        </sidebar-item>

        <sidebar-item
            :link="{
              name: 'Icons',
              path: '/icons',
              icon: 'ni ni-planet text-blue'
              }"
            >
        </sidebar-item>

        <sidebar-item
              :link="{
                name: 'Maps',
                path: '/maps',
                icon: 'ni ni-pin-3 text-orange'
              }">
        </sidebar-item>

        <sidebar-item
              :link="{
                name: 'User Profile',
                path: '/profile',
                icon: 'ni ni-single-02 text-yellow'
                }">
        </sidebar-item>

        <sidebar-item
                :link="{
                  name: 'Tables',
                  path: '/tables',
                  icon: 'ni ni-bullet-list-67 text-red'
                }">
        </sidebar-item>

        <sidebar-item
                  :link="{
                    name: 'Login',
                    path: '/login',
                    icon: 'ni ni-key-25 text-info'
                  }">
        </sidebar-item>
        <sidebar-item
                  :link="{
                    name: 'Register',
                    path: '/register',
                    icon: 'ni ni-circle-08 text-pink'
                  }">
        </sidebar-item>
        -->

        <sidebar-item
          :link="{
            name: 'Settings',
            path: '/settings',
            icon: 'ni ni-settings-gear-65 text-blue'
          }"
        >
        </sidebar-item>
        
        <sidebar-item
          :link="{
            name: '감시',
            path: '/monitor',
            icon: 'ni ni-tv-2 text-primary',
          }"
        >
          <sidebar-item
            v-for="menu in monitorMenus"
            :key="menu.name"
            :link="menu"
          >
          </sidebar-item>
        </sidebar-item>

        <sidebar-item
          :link="{
            name: '관리',
            path: '/management',
            icon: 'ni ni-tv-2 text-primary',
          }"
        >
          <sidebar-item
            v-for="menu in managementMenus"
            :key="menu.name"
            :link="menu"
          >
          </sidebar-item>
        </sidebar-item>

        <sidebar-item
          :link="{
            name: '성능',
            path: '/performance',
            icon: 'ni ni-tv-2 text-primary',
          }"
        >
          <sidebar-item
            v-for="menu in performanceMenus"
            :key="menu.name"
            :link="menu"
          >
          </sidebar-item>
        </sidebar-item>

        <sidebar-item
          :link="{
            name: '품질',
            path: '/quality',
            icon: 'ni ni-tv-2 text-primary',
          }"
        >
          <sidebar-item
            v-for="menu in qualityMenus"
            :key="menu.name"
            :link="menu"
          >
          </sidebar-item>
        </sidebar-item>

        <sidebar-item
          :link="{
            name: '기타',
            path: '/etc',
            icon: 'ni ni-tv-2 text-primary',
          }"
        >
          <sidebar-item
            v-for="menu in etcMenus"
            :key="menu.name"
            :link="menu"
          >
          </sidebar-item>
        </sidebar-item>
      </template>
    </side-bar>
    <div class="main-content">
      <dashboard-navbar :type="$route.meta.navbarType"></dashboard-navbar>

      <div @click="$sidebar.displaySidebar(false)">
        <fade-transition :duration="200" origin="center top" mode="out-in">
          <!-- your content here -->
          <router-view></router-view>
        </fade-transition>
      </div>
    </div>
  </div>
</template>
<script>
  /* eslint-disable no-new */
  import PerfectScrollbar from 'perfect-scrollbar';
  import 'perfect-scrollbar/css/perfect-scrollbar.css';

  function hasElement(className) {
    return document.getElementsByClassName(className).length > 0;
  }

  function initScrollbar(className) {
    if (hasElement(className)) {
      new PerfectScrollbar(`.${className}`);
    } else {
      // try to init it later in case this component is loaded async
      setTimeout(() => {
        initScrollbar(className);
      }, 100);
    }
  }

  import DashboardNavbar from './DashboardNavbar.vue';
  import DashboardContent from './Content.vue';
  import { FadeTransition } from 'vue2-transitions';
  import EventBus from '@/plugins/eventBus';

  export default {
    components: {
      DashboardNavbar,
      DashboardContent,
      FadeTransition
    },
    data() {
      return {
        storageMenus: JSON.parse(window.localStorage.getItem('checkedMenus')),
        monitorMenus: [],
        managementMenus: [],
        performanceMenus: [],
        qualityMenus: [],
        etcMenus: []
      }
    },
    methods: {
      initScrollbar() {
        let isWindows = navigator.platform.startsWith('Win');
        if (isWindows) {
          initScrollbar('sidenav');
        }
      }
    },
    created() {
      EventBus.$on('event-menuChanged', (payload) => {
          this.storageMenus = JSON.parse(window.localStorage.getItem('checkedMenus'));

          this.monitorMenus = [];
          this.storageMenus.monitorMenus.forEach((item) => {
            if (item.checked && item.checked === 'true') {
              this.monitorMenus.push(item);
            }
          });

          this.managementMenus = [];
          this.storageMenus.managementMenus.forEach((item) => {
            if (item.checked && item.checked === 'true') {
              this.managementMenus.push(item);
            }
          });

          this.performanceMenus = [];
          this.storageMenus.performanceMenus.forEach((item) => {
            if (item.checked && item.checked === 'true') {
              this.performanceMenus.push(item);
            }
          });

          this.qualityMenus = [];
          this.storageMenus.qualityMenus.forEach((item) => {
            if (item.checked && item.checked === 'true') {
              this.qualityMenus.push(item);
            }
          });

          this.etcMenus = [];
          this.storageMenus.etcMenus.forEach((item) => {
            if (item.checked && item.checked === 'true') {
              this.etcMenus.push(item);
            }
          });
      });
    },
    beforeMount() {
      this.storageMenus.monitorMenus.forEach((item) => {
        if (item.checked) {
          this.monitorMenus.push(item);
        }
      });

      this.storageMenus.managementMenus.forEach((item) => {
        if (item.checked) {
          this.managementMenus.push(item);
        }
      });

      this.storageMenus.performanceMenus.forEach((item) => {
        if (item.checked) {
          this.performanceMenus.push(item);
        }
      });

      this.storageMenus.qualityMenus.forEach((item) => {
        if (item.checked) {
          this.qualityMenus.push(item);
        }
      });

      this.storageMenus.etcMenus.forEach((item) => {
        if (item.checked) {
          this.etcMenus.push(item);
        }
      });
    },
    mounted() {
      this.initScrollbar();
    }
  };
</script>
<style lang="scss">
</style>

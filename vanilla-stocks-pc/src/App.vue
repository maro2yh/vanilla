<template>
  <q-layout view="lHh Lpr lFf">
    <q-header elevated class="glossy">
      <q-toolbar>
        <q-btn
          flat
          dense
          round
          @click="leftDrawerOpen = !leftDrawerOpen"
          aria-label="Menu"
          icon="menu"
        />

        <q-toolbar-title>
          Quasar App
        </q-toolbar-title>

        <div>Quasar v{{ $q.version }}</div>
      </q-toolbar>
    </q-header>

    <q-drawer
      v-model="leftDrawerOpen"
      show-if-above
      bordered
      class="bg-grey-2"
    >
      <SideMenuList />
    </q-drawer>

    <q-page-container>
      <!-- <HelloWorld /> -->
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script>
import { ref } from 'vue'
// import HelloWorld from '@/views/HelloWorld.vue'
import SideMenuList from '@/components/SideMenuList.vue'

export default {
  name: 'LayoutDefault',
  components: {
    SideMenuList
  },
  setup () {
    return {
      leftDrawerOpen: ref(false)
    }
  }
}
</script>

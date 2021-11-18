<template>
  <div>
    <q-list>
      <q-item-label header>Essential Links</q-item-label>
      <q-item clickable tag="a" target="_blank" href="https://quasar.dev">
        <q-item-section avatar>
          <q-icon name="school" />
        </q-item-section>
        <q-item-section>
          <q-item-label>Docs</q-item-label>
          <q-item-label caption>quasar.dev</q-item-label>
        </q-item-section>
      </q-item>
      <q-item clickable tag="a" target="_blank" href="https://github.com/quasarframework/">
        <q-item-section avatar>
          <q-icon name="code" />
        </q-item-section>
        <q-item-section>
          <q-item-label>Github</q-item-label>
          <q-item-label caption>github.com/quasarframework</q-item-label>
        </q-item-section>
      </q-item>
      <q-item clickable tag="a" target="_blank" href="https://chat.quasar.dev">
        <q-item-section avatar>
          <q-icon name="chat" />
        </q-item-section>
        <q-item-section>
          <q-item-label>Discord Chat Channel</q-item-label>
          <q-item-label caption>chat.quasar.dev</q-item-label>
        </q-item-section>
      </q-item>
      <q-item clickable tag="a" target="_blank" href="https://forum.quasar.dev">
        <q-item-section avatar>
          <q-icon name="forum" />
        </q-item-section>
        <q-item-section>
          <q-item-label>Forum</q-item-label>
          <q-item-label caption>forum.quasar.dev</q-item-label>
        </q-item-section>
      </q-item>
      <q-item clickable tag="a" target="_blank" href="https://twitter.com/quasarframework">
        <q-item-section avatar>
          <q-icon name="rss_feed" />
        </q-item-section>
        <q-item-section>
          <q-item-label>Twitter</q-item-label>
          <q-item-label caption>@quasarframework</q-item-label>
        </q-item-section>
      </q-item>
      <q-item clickable to="/market/status" exact>
        <q-item-section avatar>
          <q-icon name="analytics" />
        </q-item-section>
        <q-item-section>
          <q-item-label>증시현황</q-item-label>
          <q-item-label caption>코스피/코스닥 현황</q-item-label>
        </q-item-section>
      </q-item>
      <q-separator />
    </q-list>
  </div>
</template>
<script>
export default {
  name: 'SideMenuList'
}
</script>

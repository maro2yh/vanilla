<template>
  <div id="page-title">
    <h1 class="page-header">{{ pageTitle }}</h1>
  </div>
</template>

<script>
export default {
  data() {
    return {
      pageTitle: this.$route.meta.title
    }
  }
}
</script>
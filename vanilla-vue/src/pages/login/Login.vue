<template>
  <div class="login login-v1">
    <div class="login-container">
      <div class="login-header">
        <div class="brand">
          <div class="d-flex align-items-center">
            <span class="logo"></span> <b>Color</b> Admin
          </div>
          <small>Bootstrap 5 Responsive Admin Template</small>
        </div>
        <div class="icon">
          <i class="fa fa-lock"></i>
        </div>
      </div>
      <!-- END login-header -->
      
      <div class="login-body">
        <div class="login-content fs-13px">
          <form v-on:submit="checkForm" method="GET">
            <div class="form-floating mb-20px">
              <input type="email" class="form-control fs-13px h-45px" id="emailAddress" placeholder="Email Address" />
              <label for="emailAddress" class="d-flex align-items-center py-0">Email Address</label>
            </div>
            <div class="form-floating mb-20px">
              <input type="password" class="form-control fs-13px h-45px" id="password" placeholder="Password" />
              <label for="password" class="d-flex align-items-center py-0">Password</label>
            </div>
            <div class="form-check mb-20px">
              <input class="form-check-input" type="checkbox" value="" id="rememberMe" />
              <label class="form-check-label" for="rememberMe">
                Remember Me
              </label>
            </div>
            <div class="login-buttons">
              <button type="submit" class="btn h-45px btn-success d-block w-100 btn-lg">Sign me in</button>
            </div>
          </form>
        </div>
        <!-- END login-content -->
      </div>
      <!-- END login-body -->
    </div>
    <!-- END login-container -->
  </div>
  <!-- END login -->
</template>

<script>
import AppOptions from '@/config/AppOptions.vue'

export default {
  created() {
    AppOptions.appEmpty = true
  },
  beforeRouteLeave (to, from, next) {
    AppOptions.appEmpty = false
    next()
  },
  methods: {
    checkForm: function(e) {
      e.preventDefault()
      this.$router.push({ path: '/dashboard/v3'})
    }
  }
}
</script>
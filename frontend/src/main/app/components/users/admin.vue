<template>
  <div>
    <router-link class="a_left" to="/">HOME</router-link>
    <router-link class="a_left" to="/shop">SHOP</router-link>
    <router-link class="a_right" to="/cart">CART</router-link>
    <router-link class="a_right" to="/cabinet">CABINET</router-link>
    <router-link class="a_right" to="/warehouse">WAREHOUSE</router-link>
    <a class="a_right" v-on:click="removeAccessAndRefreshToken()" href="http://localhost:8080/auth/realms/my_realm/protocol/openid-connect/logout?redirect_uri=http://localhost:8082/">LOGOUT</a>
  </div>
</template>

<script>
import authService from "../services/authService.js";

export default {
  name: "admin",

  methods: {
    removeAccessAndRefreshToken: () => {
      authService.removeAccessAndRefreshToken();
    }
  }
}
</script>

<style scoped>

</style>
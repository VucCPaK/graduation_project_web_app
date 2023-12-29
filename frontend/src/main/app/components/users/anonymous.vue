<template>
  <div>
    <router-link class="a_left" to="/">HOME</router-link>
    <router-link class="a_left" to="/shop">SHOP</router-link>
    <router-link class="a_right" to="/cart">CART</router-link>
    <a class="a_right" href="http://localhost:8080/auth/realms/my_realm/protocol/openid-connect/auth?client_id=my_client&response_type=code&redirect_uri=http://localhost:8082">SIGN IN</a>
  </div>
</template>

<script>
export default {
  name: "anonymous"
}
</script>

<style scoped>

</style>
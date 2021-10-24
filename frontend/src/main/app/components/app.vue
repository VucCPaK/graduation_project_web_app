<template>
  <div>
    <div class="navig">
      <component v-bind:is="component"/>
      <!--      <router-link class="a_left" to="/">HOME</router-link>-->
      <!--      <router-link class="a_left" to="/shop">SHOP</router-link>-->
      <!--      <router-link class="a_right" to="/cart">CART</router-link>-->
      <!--      <router-link class="a_right" to="/cabinet">CABINET</router-link>-->
      <!--      <a class="a_right" href="http://localhost:8085/auth/realms/my_realm/protocol/openid-connect/auth?client_id=my_client&response_type=code&redirect_uri=http://localhost:8082">SIGN IN</a>-->
    </div>
    <router-view></router-view>
  </div>
</template>

<script>
import authService from './services/authService.js'

export default {
  name: "app",

  data() {
    return {
      component: null
    }
  },

  computed: {
    loader() {
      let role = authService.getRole();
      return () => import(`./users/${role}.vue`);
    },
  },

  mounted() {
    authService.updateAccessToken().then(() =>
        authService.handleCallback().then(() => {
          this.component = () => this.loader()
        })
    );
  }
}
</script>

<style>
.navig {
  overflow: hidden;
  background-color: #000000;
}

.navig a:hover {
  background-color: #ddd;
  color: black;
}

.a_left {
  float: left;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
  margin-left: 20px;
}

.a_right {
  float: right;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
  margin-right: 20px;
}

body {
  margin: 0;
  font-family: sans-serif;
}
</style>
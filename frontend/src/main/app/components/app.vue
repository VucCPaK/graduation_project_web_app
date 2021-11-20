<template>
  <div>
    <div class="navig">
      <component v-bind:is="component"/>
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
    authService.refreshToken()
        .then(() => this.component = () => this.loader());
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
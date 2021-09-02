<template>

  <!--Registration-->
  <div class="w3-container w3-padding-64 w3-grayscale-min w3-xlarge" id="registration">
    <div class="w3-content">
      <h1 class="w3-center w3-jumbo" style="margin-bottom:64px">Sign up</h1>

      <p><input class="w3-input w3-padding-16 w3-border" v-model="login"
                type="text" placeholder="Login" required></p>
      <p><input class="w3-input w3-padding-16 w3-border" v-model="password"
                type="password" placeholder="Password" required></p>
      <p><input class="w3-input w3-padding-16 w3-border" v-model="mail"
                type="email" placeholder="Mail" required></p>
      <p><input class="w3-input w3-padding-16 w3-border" v-model="firstName"
                type="text" placeholder="First name"></p>
      <p><input class="w3-input w3-padding-16 w3-border" v-model="lastName"
                type="text" placeholder="Last name"></p>
      <p><input class="w3-input w3-padding-16 w3-border" v-model="phone"
                type="tel" placeholder="Phone number"></p>
      <p>
        <button class="w3-button w3-light-grey w3-block" v-on:click="submitForm()">Sign up</button></p>

      <p>Do you already have an account?
        <router-link to="/sign_in">Sign in.</router-link>
      </p>
    </div>
  </div>

</template>

<script>
import authService from '../services/authService.js'

export default {
  name: 'registration',

  data() {
    return {
      login: '',
      password: '',
      mail: '',
      firstName: '',
      lastName: '',
      phone: ''
    }
  },

  methods: {
    checkForm() {

    },

    checkLogin() {
      return authService.isUserPresentInDirectory(this.login)
    },

    submitForm() {
      if (this.checkLogin()) {
        console.log("Please, change username")
        return;
      }

      authService.registration(
          this.login,
          this.password,
          this.mail,
          this.firstName,
          this.lastName,
          this.phone
      );
    }
  }
}
</script>

<style scoped>
body {
  font-family: sans-serif;
}
</style>
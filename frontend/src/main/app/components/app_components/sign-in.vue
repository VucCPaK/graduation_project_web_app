<template>
<!--  &lt;!&ndash;Registration&ndash;&gt;-->
<!--    <div class="w3-container w3-padding-64 w3-grayscale-min w3-xlarge" id="registration">-->
<!--      <div class="w3-content">-->
<!--        <h1 class="w3-center w3-jumbo" style="margin-bottom:64px">Sign in</h1>-->
<!--        <form action="http://localhost:8081/perform-login" method="post">-->
<!--          <p><input class="w3-input w3-padding-16 w3-border" type="text" placeholder="Username" required name="username"></p>-->
<!--          <p><input class="w3-input w3-padding-16 w3-border" type="password" placeholder="Password" required name="password"></p>-->
<!--          <p>-->
<!--            <button class="w3-button w3-light-grey w3-block" type="submit">Sign in</button>-->
<!--          </p>-->
<!--        </form>-->
<!--        <p>Don't have an account?-->
<!--          <router-link to="/sign_up">Create it!</router-link>-->
<!--        </p>-->
<!--        <p>-->
<!--          With GitHub: <a href="http://localhost:8081/oauth2/authorization/github">Click here</a>-->
<!--        </p>-->
<!--      </div>-->
<!--    </div>-->
  <div>
    {{user}}
  </div>
</template>

<script>
import authService from '../services/authService.js'

export default {

  data() {
    return {
      user: ''
    }
  },

  mounted() {
      authService.auth()
        .then(response => this.user = response.data);
  }

}



</script>

<style scoped>
</style>
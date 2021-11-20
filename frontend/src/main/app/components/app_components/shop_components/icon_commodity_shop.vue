<template>
  <div class="col mb-5">
    <div class="card h-100">
      <!-- Product image-->
      <img class="card-img-top spec-img" :src="picture" alt="..."/>
      <!-- Product details-->
      <div class="card-body p-4">
        <div class="text-center spec-container">
          <!-- Product name-->
          <h6 class="fw-bolder">{{name}}</h6>
          <!-- Product price-->
          <h6>{{price}}$</h6>
          <!-- Product actions-->
        </div>
      </div>
      <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
        <div class="text-center">
          <router-link class="btn btn-outline-dark mt-auto" :to="toItem()">View</router-link>
          <router-link v-if="checkCredentials()" class="btn btn-outline-dark mt-auto" :to="toSupply()">Edit</router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import authService from '../../services/authService.js'

export default {
  name: "icon_commodity_shop",
  props: ['id', 'name', 'price', 'picture', 'supplierId'],

  methods: {
    toItem() {
      return `/shop/${this.id}`
    },

    toSupply() {
      return `/shop/supply/${this.id}`
    },

    checkCredentials() {
      return (authService.getRole() === 'supplier' && authService.getUserId() === this.supplierId)
          || authService.getRole() === 'admin'
    },
  }
}
</script>

<style scoped>
@import "../../css/shop/styles.css";

  .spec-img {
    object-fit: contain;
    width: auto;
    height: 150px;
  }

  .spec-container{
    height: 50px;
  }
</style>
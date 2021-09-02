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
          {{price}}$
        </div>
      </div>
      <!-- Product actions-->
      <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
        <div class="text-center">
          <router-link class="btn btn-outline-dark mt-auto" :to="idToString()">View</router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "icon_commodity_shop",
  props: ['id', 'name', 'price', 'picture'],

  methods: {
    idToString() {
      return `/shop/${this.id}`
    }
  }
}
</script>

<style scoped>
  .spec-img {
    object-fit: contain;
    width: auto;
    height: 150px;
  }

  .spec-container{
    height: 30px;
  }
</style>
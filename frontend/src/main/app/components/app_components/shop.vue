<template>
  <div>
    <router-view/>

    <!-- Header-->
    <header class="bg-dark py-5">
      <div class="container px-4 px-lg-5 my-5">
        <div class="text-center text-white">
          <h1 class="display-4 fw-bolder">Components</h1>
          <p class="lead fw-normal text-white-50 mb-0">With this shop homepage template</p>
        </div>
      </div>
      <div class="d-flex justify-content-center">
        <router-link v-if="checkCredentials()" class="btn btn-primary" :to="toCreateItem()">Add new item</router-link>
      </div>
    </header>

    <!-- Section-->
    <section class="py-5">
      <div class="container px-4 px-lg-5 mt-5">
        <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
          <div v-for="item in items" :key="item.id">
            <icon_commodity_shop
                v-bind:id='item.id'
                v-bind:name='item.name'
                v-bind:price='item.price'
                v-bind:picture='getPictureUrl(item.pictures[0])'
                v-bind:supplierId="item.supplierId"
            />
          </div>
        </div>
      </div>
    </section>
    <!-- Footer-->
    <footer class="py-5 bg-dark">
      <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Your Website 2021</p></div>
    </footer>
  </div>
</template>

<script>
import icon_commodity_shop from "./shop_components/icon_commodity_shop.vue";
import shopService from "../services/shopService.js"
import authService from "../services/authService";

export default {
  components: {icon_commodity_shop},

  data() {
    return {
      items: [],
    }
  },

  beforeCreate() {
    shopService
        .getListOfItems()
        .then((items) => {
          this.items = items;
        })
  },

  methods: {
    getPictureUrl(itemEPicture) {
      return shopService.getPictureUrl(itemEPicture)
    },

    toCreateItem() {
      return `/shop/supply/item/new`;
    },

    checkCredentials() {
      return authService.getRole() === 'supplier' || authService.getRole() === 'admin'
    },
  }
}
</script>

<style scoped>
@import '../css/shop/styles.css';
@import "~bootstrap/dist/css/bootstrap.min.css";
</style>
<template>
  <div class="container-sm">
    <table class="table">
      <thead>
      <tr>
        <td></td>
        <td></td>
      </tr>
      </thead>
      <tbody>
      <tr v-for="item in items">
        <td><img class="card-img-top spec-img" :src="getPictureUrl(item.pictures[0])" alt="..."/></td>
        <td>
          <p><strong>Type: </strong>{{ item.type }}</p>
          <p><strong>Name: </strong>{{ item.name }}</p>
          <p><strong>Price: </strong>{{ item.price }}</p>
          <p><strong>Quantity: </strong>{{ item.quantity }}</p>
        </td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import warehouseService from "../../services/warehouseService.js";
import shopService from "../../services/shopService.js"

export default {
  name: "items",

  data() {
    return {
      items: []
    }
  },

  mounted() {
    warehouseService
        .getItemsInOrder(this.$route.params.id)
        .then((items) => {
          this.items = items;
        });
  },

  methods: {
    getPictureUrl(itemEPicture) {
      return shopService.getPictureUrl(itemEPicture)
    }
  }
}
</script>

<style scoped>
@import "~bootstrap/dist/css/bootstrap.min.css";

.spec-img {
  object-fit: contain;
  width: auto;
  height: 150px;
}
</style>
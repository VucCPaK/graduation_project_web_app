<template>
  <div>
    <div>
      <table class="table table-striped">
        <thead>
        <tr>
          <th scope="col">ID</th>
          <th scope="col">Date</th>
          <th scope="col">Total price</th>
          <th scope="col">Items</th>
          <th scope="col">Send</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="item in items">
          <td>{{ item.id }}</td>
          <td>{{ item.date }}</td>
          <td>{{ item.totalPrice }}$</td>
          <td><router-link class="btn btn-primary btn-sm"
                           role="button"
                           aria-disabled="true"
                           :to="idToString(item.id)">View items</router-link></td>
          <td><button type="button"
                      class="btn btn-primary btn-sm"
                      v-on:click="submit(item.id)">Confirm</button></td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import warehouseService from "../services/warehouseService.js";

export default {
  name: "warehouse",

  data() {
    return {
      items: []
    }
  },

  beforeCreate() {
    warehouseService
        .getList()
        .then((items) => {
          this.items = items;
        });
  },

  methods: {
    submit(id) {
      warehouseService
          .submit(id)
          .then(() => {
            warehouseService
                .getList()
                .then((items) => {
                  this.items = items;
                })
          });
    },

    idToString(id) {
      return `/warehouse/${id}`;
    }
  }

}
</script>

<style scoped>
@import '../css/shop/styles.css';
@import '~bootstrap/dist/css/bootstrap.min.css';
</style>
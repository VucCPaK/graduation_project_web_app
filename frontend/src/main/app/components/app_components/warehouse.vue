<template>
  <div>
    <div class="container px-4 px-lg-5 my-5">
      <label for="order-search">Search the order:</label>
      <input type="search" id="order-search" name="q"
             aria-label="Search through site content">
      <button>Search</button>
    </div>

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
          <td>{{ item.totalPrice }}</td>
          <td>{{ item.items }}</td>
          <td><input type="checkbox" v-model="item.sent"></td>
        </tr>
        </tbody>
      </table>
      <div class="btn">
        <button v-on:click="submit(items)">Submit</button>
      </div>
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
    submit(updatedItems) {
      warehouseService
          .submit(updatedItems)
          .then((items) => {
            this.items = items;
          });
    }
  }

}
</script>

<style scoped>
@import '../css/shop/styles.css';
@import '~bootstrap/dist/css/bootstrap.min.css';

.btn {
  position: absolute;
  right: 2%;
  margin-top: 2%;
  transform: translate(0%, -50%);
  -ms-transform: translate(0%, -50%)
}
</style>
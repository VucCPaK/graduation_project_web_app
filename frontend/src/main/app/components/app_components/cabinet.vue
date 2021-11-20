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
        </tr>
        </thead>
        <tbody>
        <tr v-for="item in items">
          <td>{{ item.id }}</td>
          <td>{{ item.date }}</td>
          <td>{{ item.totalPrice }}$</td>
          <td>
            <router-link class="btn btn-primary btn-sm"
                         role="button"
                         aria-disabled="true"
                         :to="idToString(item.id)">View items
            </router-link>
          </td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import cabinetService from "../services/cabinetService.js"
import authService from "../services/authService.js";

export default {
  name: "cabinet",

  data() {
    return {
      items: []
    }
  },

  beforeCreate() {
    cabinetService
        .getList(authService.getUserId())
        .then((items) => {
          this.items = items;
        });
  },

  methods: {
    idToString(id) {
      return `/cabinet/order/${id}`;
    }
  },
}
</script>

<style scoped>

</style>
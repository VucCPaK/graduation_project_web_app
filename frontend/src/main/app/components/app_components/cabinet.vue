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

      <div>
        <div class="d-flex justify-content-center btn btn-primary btn-sm" @click="showForm = true"
             role="button"
             aria-disabled="true"
             v-if="checkCredentials"
        >Get statistical data by products that you supply
        </div>
        <br>
        <form v-if="showForm" @submit.prevent="getStatistic">
          <div class="form-group">
            <label for="startDate">Start Date:</label>
            <input type="date" id="startDate" v-model="period.startDate" class="form-control">
          </div>
          <br>
          <div class="form-group">
            <label for="endDate">End Date:</label>
            <input type="date" id="endDate" v-model="period.endDate" class="form-control">
          </div>
          <br>
          <button type="submit" class="btn btn-primary">Submit</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import { saveAs } from 'file-saver';
import cabinetService from "../services/cabinetService.js"
import authService from "../services/authService.js";

export default {
  name: "cabinet",

  data() {
    return {
      items: [],
      showForm: false,
      period: {startDate: "", endDate: ""}
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
    },

    checkCredentials() {
      return authService.getRole() === 'supplier' || authService.getRole() === 'admin';
    },

    async getStatistic() {
      let role = authService.getRole();

      let blob;
      if (role === 'supplier') {
        let id = authService.getUserId();
        blob = cabinetService.getSupplierStatistic(id, this.period);
      } else if (role === 'admin') {
        blob = cabinetService.getAdminStatistic(this.period);
      }

      console.log(blob);

      // saveAs(blob, 'statistics.xlsx');

      // Create a URL for the blob
      // const url = window.URL.createObjectURL(blob);
      // const link = document.createElement('a');
      // link.href = url;
      //
      // // Suggest a filename for the downloaded file
      // link.setAttribute('download', 'statistic.xlsx');
      //
      // // Append to the DOM and click the link to trigger the download
      // document.body.appendChild(link);
      // link.click();
      //
      // // Clean up
      // document.body.removeChild(link);
      // window.URL.revokeObjectURL(url);

      console.log("Form submitted");
      this.showForm = false;
    }
  },
}
</script>

<style scoped>
@import "bootstrap/dist/css/bootstrap.min.css";

</style>
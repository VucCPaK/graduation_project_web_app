<template>
  <div class="container-sm">
    <fieldset>
      <legend> Create item </legend>
      <div class="mb-3">
        <label class="form-label">Amount: </label>
        <input v-model="item.amount" type="number" class="form-control">
      </div>
      <div class="mb-3">
        <label class="form-label">Categories: </label>
        <input v-model="item.categories" type="text" class="form-control">
      </div>
      <div class="mb-3">
        <label class="form-label">Description: </label>
        <input v-model="item.description" type="text" class="form-control">
      </div>
      <div class="mb-3">
        <label class="form-label">Name: </label>
        <input v-model="item.name" type="text" class="form-control">
      </div>
      <div class="mb-3">
        <label class="form-label">Price: </label>
        <input v-model="item.price" type="number" class="form-control">
      </div>
      <div class="mb-3">
        <label class="form-label">Type: </label>
        <input v-model="item.type" type="text" class="form-control">
      </div>
      <div class="mb-3">
        <label class="form-label">Add images:</label>
        <input type="file" multiple ref="files" v-on:change="handleFilesUpload()" class="form-control"/>
      </div>
      <button v-on:click="submit()" class="btn btn-primary">Confirm</button>
    </fieldset>
  </div>
</template>

<script>
import supplyService from "../../services/supplyService.js";
import authService from "../../services/authService.js"

export default {
  name: "create_item",

  data() {
    return {
      item: {},
      files: '',
    }
  },

  methods: {
    handleFilesUpload() {
      this.files = this.$refs.files.files;
    },

    submit() {
      let formData = new FormData();

      for (let i = 0; i < this.files.length; i++) {
        let file = this.files[i];

        formData.append('files', file);
      }

      this.item.supplierId = authService.getUserId();
      supplyService.createItem(this.item)
          .then((itemId) => {
            supplyService.addImages(itemId.data, formData);
          })

      this.$router.push(`/shop`)

    }
  }
}
</script>

<style scoped>
@import "bootstrap/dist/css/bootstrap.min.css";

</style>
<template>
  <div>
    <div class="large-12 medium-12 small-12 cell">
      <label>File
        <input type="file" id="file" ref="file" v-on:change="handleFileUpload()"/>
      </label>
      <button v-on:click="submitFile()">Submit</button>
      <p></p>
      <img id="pic" :src="url" alt="preview">
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "picture",
  data() {
    return {
      file: '',
      url: null
    }
  },
  methods: {
    handleFileUpload() {
      this.file = this.$refs.file.files[0];
    },

    submitFile() {
      let formData = new FormData();
      formData.append('file', this.file);
      axios.post( 'http://localhost:8081/api/items/91d4877d-75b1-46cf-b664-30b1d39f04ae/pictures',
          formData, {
            headers: {
              'Content-Type': 'multipart/form-data'
            }
          }
      ).then((response) => {
        console.log('SUCCESS!!')
        axios.get(`http://localhost:8081/api/items/91d4877d-75b1-46cf-b664-30b1d39f04ae/pictures/${response.data}`)
            .then((resp) => {
              console.log('SUCCESS!!')
              this.url = resp.data;
            })
      }).catch(() => {
        console.log('FAILURE!!');
      });
    },
  }
}
</script>

<style scoped>
</style>
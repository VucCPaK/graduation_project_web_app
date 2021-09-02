<template>
  <div>
    <img id="pic" :src="getUrl()" alt="preview">
  </div>
</template>

<script>

export default {
  name: "display_picture",

  data() {
    return {
    }
  },

  methods: {
    getUrl: function() {
      return `http://localhost:8081/api/items/1/pictures/aa26f74b-d662-4be8-b882-034b451d6d1b`;
    }
  }


}
</script>

<style scoped>

</style>
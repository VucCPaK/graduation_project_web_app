<template>
  <button class="active_button" v-on:click="addToCart()">Add to cart</button>
</template>

<script>

export default {
  name: "add_button",

  methods: {
    addToCart() {
      this.$parent.addToCart();
    }
  }
}
</script>

<style scoped>
.active_button {
  margin-left: 15px;
  cursor: pointer;
  background-color: #3a8bcd;
  outline: none;
  border-radius: 5px;
  padding: 10px 15px;
  display: inline-block;
  color: #fff;
  font-size: 14px;
  text-transform: uppercase;
  font-weight: 300;
  letter-spacing: 0.4px;
  text-decoration: none;
  transition: all 0.5s;
  box-shadow: none;
  border: none;
}
</style>
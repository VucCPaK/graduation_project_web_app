<template>
  <!-- not button = not click -->
  <div class="nonactive_button">Add to cart</div>
</template>

<script>
export default {
  name: "nonactive_add_button"
}
</script>

<style scoped>
.nonactive_button {
  margin-left: 15px;
  background-color: #616171;
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
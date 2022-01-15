<template>
  <div class="col mb-5">
    <div class="card h-100">
      <!-- Product image-->
      <div class="contain-img">
        <img class="card-img-top" :src="picture" alt="..."/>
        <button class="btn-cls" v-on:click="removeFromCart(id)">X</button>
      </div>
      <!-- Product details-->
      <div class="card-body p-4">
        <div class="text-center spec-container">
          <!-- Product name-->
          <h6 class="fw-bolder">{{ name }}</h6>
          <!-- Product price-->
          {{ price }}$
        </div>
      </div>
      <!-- Product actions-->
      <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
        <div class="text-center">
          Quantity:
          <input class="quantity-text"
                 v-model="quantity">
          {{ update() }}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import cartService from "../../services/cartService.js";

export default {
  name: "icon_commodity_cart",
  props: ['id', 'name', 'price', 'quant', 'picture'],

  data() {
    return {
      // get value from props.quantity
      quantity: this.quant,
    }
  },

  methods: {
    removeFromCart(id) {
      this.$parent.items = cartService.remove(id);
      cartService
          .getTotalPrice()
          .then(totalPrice => this.$parent.totalPrice = totalPrice);
    },

    update() {
      setTimeout(() => {
        if (!Number.isInteger(Number(this.quantity))) {
          this.quantity = '';
          return;
        }

        if (this.quantity < 1) {
          this.quantity = '';
          return;
        }

        cartService.updateAmount(this.id, this.quantity);
        cartService
            .getTotalPrice()
            .then(totalPrice => this.$parent.totalPrice = totalPrice);
      }, 1000);
    },
  },

  watch: {
    updateAmount: function(oldValue, newValue) {
      if (this.quantity !== oldValue)
        this.quantity = newValue;
    }
  }
}
</script>

<style scoped>
@import "bootstrap/dist/css/bootstrap.min.css";

.spec-container {
  height: 50px;
}

.contain-img {
  position: relative;
  height: 100%;
  width: 100%;
}

.contain-img img {
  object-fit: contain;
  width: 100%;
  height: 150px;
}

.contain-img .btn-cls {
  position: absolute;
  transform: translate(-50%, -50%);
  -ms-transform: translate(-50%, -50%);
  color: red;
  border-radius: 5px;
}

.quantity-text {
  margin-left: 10px;
  width: 44px;
  height: 44px;
  line-height: 42px;
  font-size: 14px;
  font-weight: 700;
  color: #000;
  display: inline-block;
  text-align: center;
  outline: none;
}
</style>
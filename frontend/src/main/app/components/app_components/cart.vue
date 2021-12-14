<template>
  <div>

    <!-- Header-->
    <header class="bg-dark py-5">
      <div class="container px-4 px-lg-5 my-5">
        <div class="text-center text-white">
          <h1 class="display-4 fw-bolder">Your purchases</h1>
          <p class="lead fw-normal text-white mb-0">Total price: <span>{{ totalPrice }}$</span>
            <button class="button" v-on:click="toOrder()">ORDER</button>
          </p>

        </div>
      </div>
    </header>

    <!-- Section-->
    <section class="py-5">
      <div class="container px-4 px-lg-5 mt-5">
        <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
          <div v-for="item in items" :key="item.id">
            <icon_commodity_cart
                v-bind:id="item.id"
                v-bind:name="item.name"
                v-bind:price="item.price"
                v-bind:quant="item.quantity"
                v-bind:picture="getPictureUrl(item.pictures[0])"
            />
          </div>
        </div>
      </div>
    </section>
    <!-- Footer-->
    <footer class="py-5 bg-dark footer">
      <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Your Website 2021</p></div>
    </footer>
  </div>
</template>

<script>
import icon_commodity_cart from "./cart_components/icon_commodity_cart.vue";
import cartService from "../services/cartService.js";
import shopService from "../services/shopService.js";
import authService from "../services/authService";

export default {
  name: "cart",
  components: {icon_commodity_cart},

  data() {
    return {
      items: [],
      totalPrice: 0
    }
  },

  created() {
    // cartService
    //     .getList()
    //     .then((items) => this.items = items)
    //     .then(() =>
    //         cartService
    //             .getTotalPrice()
    //             .then((totalPrice) => this.totalPrice = totalPrice));
    cartService.getList().forEach(item => this.items.push(item));

    cartService
        .getTotalPrice()
        .then((totalPrice) => this.totalPrice = totalPrice);
  },

  methods: {
    getPictureUrl(itemEPicture) {
      return shopService.getPictureUrl(itemEPicture);
    },

    toOrder() {
      if (authService.getRole() === "anonymous") {
        console.log("Only an authorized user can order!")
        window.location.href = "http://localhost:8085/auth/realms/my_realm/protocol/openid-connect/auth?client_id=my_client&response_type=code&redirect_uri=http://localhost:8082";
        return;
      }

      cartService.toOrder(authService.getUserId());
      cartService.items.length = 0;
      this.$router.push('/');
    }
  }
}
</script>

<style scoped>
.footer {
  position: fixed;
  left: 0;
  bottom: 0;
  width: 100%;
  text-align: center;
}

.button {
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
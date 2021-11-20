<template>
  <div>
    <div>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
      <meta name="description" content="">
      <meta name="author" content="">
      <link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700" rel="stylesheet">
    </div>
    <div>
      <!-- Page Content -->

      <!-- Single Starts Here -->
      <div class="single-product">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="section-heading">
                <div class="line-dec"></div>
                <h1>{{ this.item.type }}</h1>
              </div>
            </div>
            <div class="col-md-6">
              <div class="product-slider">
                <div class="flex-slider">
                  <img :src="this.src" alt="..." class="main-img"/>
                </div>
                <br/>
                <ul class="slides images-scrollbar wrapper">
                  <li v-for="img in getPictures()">
                    <img class="indent spec-img" :src="getPictureUrl(item.id, img)" alt="..."
                         v-on:click="changeImage(getPictureUrl(item.id, img))"/>
                  </li>
                  <!-- items mirrored twice, total of 12 -->
                </ul>
              </div>
            </div>
            <div class="col-md-6">
              <div class="right-content">
                <h4>{{ this.item.name }}</h4>
                <h6>{{ this.item.price }}$</h6>
                <p>{{ this.item.description }}</p>
                <span>{{ this.item.amount }} left on stock</span>
                <nonactive_add_button v-if="checkAmount()"/>
                <active_add_button v-else-if="!presentInCart"/>
                <item-in-the-cart v-else/>
                <div class="down-content">
                  <div class="categories">
                    <h6>Category: <span>{{ this.item.categories }}</span>
                    </h6>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- Single Page Ends Here -->

      <!-- Sub Footer Starts Here -->
      <div class="footer">
        <div class="sub-footer">
          <div class="container">
            <div class="row">
              <div class="col-md-12">
                <div class="copyright-text">
                  <p>Copyright &copy; 2019 Company Name - Design: <a rel="nofollow"
                                                                     href="https://www.facebook.com/tooplate">Tooplate</a>
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

import shopService from "../../services/shopService.js";
import cartService from "../../services/cartService.js";
import active_add_button from "./active_add_button.vue";
import itemInTheCart from "./itemInTheCart.vue";
import nonactive_add_button from "./nonactive_add_button.vue";

export default {

  name: "commodity",
  components: {active_add_button, itemInTheCart, nonactive_add_button},

  data() {
    return {
      item: {},
      src: null,
      presentInCart: false
    }
  },

  beforeCreate() {
    shopService
        .getItemById(this.$route.params.id)
        .then((item) => {
          this.item = item;
          this.src = this.getPictureUrl(this.item.id, this.item.pictures[0]);
          // cartService.isPresentInCart(this.item)
          //     .then((presentInCart) => {
          //       this.presentInCart = presentInCart;
          //     })
        });
  },

  mounted() {
    this.presentInCart = cartService.isPresentInCart(this.$route.params.id);
  },

  methods: {
    getPictureUrl(itemId, itemEPicture) {
      return shopService.getPictureUrl(itemId, itemEPicture)
    },

    getPictures() {
      return this.item.pictures;
    },

    changeImage(src) {
      this.src = src;
    },

    addToCart() {
      cartService.add(this.item);
      // for updating button
      this.presentInCart = true;
    },

    checkAmount() {
      return this.item.amount === 0;
    }
  }

}
</script>

<style scoped>
@import "../../css/commodity/bootstrap.min.css";
@import "../../css/commodity/flex-slider.css";
@import "../../css/commodity/owl.css";
@import "../../css/commodity/tooplate-main.css";

.images-scrollbar {
  display: flex;
}

.wrapper {
  width: auto;
  overflow-x: scroll;
  white-space: nowrap;
}

.indent {
  margin-bottom: 8px;
  margin-right: 8px;
  margin-top: 8px;
  border: 2px #5a6268;
}

.flex-slider a:hover {
  outline: none;
}

.spec-img {
  object-fit: contain;
  width: 120px;
  height: 120px;
}

.footer {
  position: fixed;
  left: 0;
  bottom: 0;
  width: 100%;
  text-align: center;
}

.main-img {
  object-fit: contain;
  width: 498px;
  height: 300px;
}

</style>
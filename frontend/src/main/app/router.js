import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

import home from './components/app_components/home.vue'
import shop from './components/app_components/shop.vue'
import cart from './components/app_components/cart.vue'
import commodity from "./components/app_components/shop_components/commodity.vue";
import cabinet from "./components/app_components/cabinet.vue";
import warehouse from "./components/app_components/warehouse.vue"

export default new Router({
    mode: 'history',
    routes: [
        {
            path: '/',
            component: home
        },
        {
            path: '/shop',
            component: shop,
        },
        {
            path: '/cart',
            component: cart
        },
        {
            path: '/shop/:id',
            component: commodity
        },
        {
            path: '/cabinet',
            component: cabinet
        },
        {
            path: '/warehouse',
            component: warehouse
        }
    ]
})
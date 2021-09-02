import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

import home from './components/app_components/home.vue'
import shop from './components/app_components/shop.vue'
import sign_in from './components/app_components/sign-in.vue'
import sign_up from './components/app_components/sign-up.vue'
import cart from './components/app_components/cart.vue'
import commodity from "./components/app_components/shop_components/commodity.vue";

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
            path: '/sign_in',
            component: sign_in
        },
        {
            path: '/sign_up',
            component: sign_up
        },
        {
            path: '/cart',
            component: cart
        },
        {
            path: '/shop/:id',
            component: commodity
        }
    ]
})
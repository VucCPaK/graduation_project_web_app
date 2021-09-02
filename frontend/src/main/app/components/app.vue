<template>
    <div>
        <div class="navig">
            <router-link class="a_left" to="/">HOME</router-link>
            <router-link class="a_left" to="/shop">SHOP</router-link>
            <router-link class="a_right" to="/cart">CART</router-link>
            <router-link class="a_right" to="/sign_in">SIGN IN</router-link>
        </div>

        <router-view />
    </div>
</template>

<script>
    export default {
        name: "app",
    }
</script>

<style scoped>
    .navig {
        overflow: hidden;
        background-color: #000000;
    }

    .navig a:hover {
        background-color: #ddd;
        color: black;
    }

    .a_left {
        float: left;
        color: #f2f2f2;
        text-align: center;
        padding: 14px 16px;
        text-decoration: none;
        font-size: 17px;
        margin-left: 20px;
    }

    .a_right {
        float: right;
        color: #f2f2f2;
        text-align: center;
        padding: 14px 16px;
        text-decoration: none;
        font-size: 17px;
        margin-right: 20px;
    }

    body {
        margin: 0;
        font-family: sans-serif;
    }
</style>
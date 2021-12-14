import Vue from 'vue';
import App from './components/app.vue'
import router from './router.js'
import axios from 'axios'
import authService from './components/services/authService.js'

axios.defaults.withCredentials = true;
localStorage.setItem('cartItems',JSON.stringify([]));

axios.interceptors.request.use(config => {
    config.headers = {
        'Authorization': `${sessionStorage.getItem('token_type')} ${sessionStorage.getItem('access_token')}`
    }
    return config;
});

// axios.interceptors.response.use(function (response) {
//     return response;
// }, function (error) {
//     if (authService.isRefreshTokenPresent()) {
//         authService.updateAccessToken();
//         return;
//     }
//     return Promise.reject(error);
// });

new Vue({
    router,
    axios,
    render: h => h(App)
}).$mount('#app');




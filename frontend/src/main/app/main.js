import Vue from 'vue';
import App from './components/app.vue'
import router from './router.js'
import axios from 'axios'

axios.defaults.withCredentials = true;

axios.interceptors.request.use(config => {
    config.headers = {
        'Authorization': `${sessionStorage.getItem('token_type')} ${sessionStorage.getItem('access_token')}`
    }
    return config;
});

new Vue({
    router,
    axios,
    render: h => h(App)
}).$mount('#app');




import Vue from 'vue';
import Vuelidate from 'vuelidate'
import App from './components/app.vue'
import router from './router.js'
import axios from 'axios'

axios.defaults.withCredentials = true;

Vue.use(Vuelidate);

new Vue({
    router,
    axios,
    render: h => h(App)
}).$mount('#app');


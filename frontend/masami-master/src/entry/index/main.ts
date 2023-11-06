import { createApp ,h} from 'vue'
// 524245
import App from './App.vue'
import router from './router'
import AppAsideBar from '../../layout/AppAsideBar.vue';
const app = createApp(App)
app.use(router)
app.component('app-aside-bar', AppAsideBar);
app.mount('#app')

import { createApp ,h} from 'vue'
// 524245
import App from './App.vue'
import router from './router'

const app = createApp(App)
app.use(router)
app.mount('#app')

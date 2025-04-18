import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './assets/gloable.css'
import request from './utils/request';

Vue.prototype.request = request  //全局注册request，其他文件可通过this.request使用分装好的request实例

Vue.config.productionTip = false
Vue.use(ElementUI);
Vue.prototype.request=request

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')

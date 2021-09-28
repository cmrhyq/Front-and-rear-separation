// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import $ from 'jquery'
import router from './router'
import store from "./store";
import ElementUI from 'element-ui'
import message from './assets/js/message'
import 'element-ui/lib/theme-chalk/index.css'
import axios from "axios";

Vue.use(ElementUI)
Vue.use(message)
Vue.config.productionTip = false

/**
 * 添加请求拦截器，在请求头中添加token
 */
axios.interceptors.request.use(
  config => {
    if (localStorage.getItem('token')) {
      config.headers.token = localStorage.getItem("token");
    }
    return config;
  },
  error => {
    return Promise.reject(error);
  }
)

/* eslint-disable no-new */
new Vue({  // vue全局组件
  el: '#app', // 绑定vue实例全局作用范围
  router, // 注册路由对象
  store,
  components: {App}, // 注册App组件
  template: '<App/>'
})

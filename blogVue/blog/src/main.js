// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import VueRouter from "vue-router";
import router from './router'
import $ from 'jquery'
import message from './assets/js/message'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

import Login from "./pages/Login";
import Home from "./pages/home/Home"

Vue.use(ElementUI)
Vue.use(message)
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: {Login},
  template: '<Login/>'
})

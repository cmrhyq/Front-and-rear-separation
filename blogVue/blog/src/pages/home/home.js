// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Home from './Home'
import VueRouter from "vue-router";
import router from '../../router'
import $ from 'jquery'
import message from '../../assets/js/message'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

Vue.use(ElementUI)
Vue.use(message)
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#home',
  router,
  components: {Home},
  template: '<Home/>'
})

export default Home

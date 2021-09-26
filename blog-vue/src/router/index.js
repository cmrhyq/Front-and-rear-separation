import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/login'
import Index from '@/components/index'
import EditUserInfo from '@/components/editUserInfo'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/index',
      name: 'Index',
      component: Index
    },
    {
      path: '/editUserInfo',
      name: 'EditUserInfo',
      component: EditUserInfo
    }
  ]
})

// import Vue from 'vue'
// import Router from 'vue-router'
// import Login from '@/components/login'
// import Index from '@/components/index'
// import ControlUser from '@/components/controlUser'
// import EditUserInfo from '@/components/editUserInfo'
//
// Vue.use(Router)
//
// export default new Router({
//   routes: [
//     {
//       path: '/',
//       name: 'Login',
//       component: Login
//     },
//     {
//       path: '/login',
//       name: 'Login',
//       component: Login
//     },
//     {
//       path: '/index',
//       name: 'Index',
//       component: Index
//     },
//     {
//       path: '/controlUser',
//       name: 'ControlUser',
//       component: ControlUser
//     },
//     {
//       path: '/editUserInfo',
//       name: 'EditUserInfo',
//       component: EditUserInfo
//     }
//   ]
// })


// const routes = [
//   {
//     path: '/',
//     component: login
//   }, {
//     path: '/index',
//     component: index,
//     name: '',
//     children: [{
//       path: '',
//       component: welcome,
//       meta: [],
//     }, {
//       path: '/controlUser',
//       component: controlUser,
//       meta: ['用户控制']
//     }, {
//       path: '/editUserInfo',
//       component: editUserInfo,
//       meta: ['编辑资料']
//     }
//     ]
//   }
// ]

// export default new Router({
//   routes,
// })


import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const login = r => require.ensure([], () => r(require('@/components/login')), 'login');
const register = r => require.ensure([], () => r(require('@/components/register')), 'register');
const index = r => require.ensure([], () => r(require('@/components/index')), 'index');
const controlUser = r => require.ensure([], () => r(require('@/components/controlUser')), 'controlUser');
const editUserInfo = r => require.ensure([], () => r(require('@/components/editUserInfo')), 'index');
const welcome = r => require.ensure([], () => r(require('@/components/welcome')), 'welcome');


const router = new Router({
  routes: [
    {
      path: '/',
      component: login
    },{
      path: "/reg",
      component: register
    }, {
      path: '/index',
      component: index,
      name: '',
      children: [{
        path: '',
        component: welcome,
        meta: [],
      }, {
        path: '/controlUser',
        component: controlUser,
        meta: ['用户控制']
      }, {
        path: '/editUserInfo',
        component: editUserInfo,
        meta: ['编辑资料']
      }
      ]
    }
  ]
})

/**
 * 导航守卫
 *
 * 使用router.beforeEach注册一个全局前置首位，判断用户是否登录
 */
router.beforeEach((to, from, next) => {
  if (to.path === '/') {
    next();
  } else {
    let token = localStorage.getItem('token');
    if (token === 'null' || token === '' || token === undefined) {
      next('/');
    } else {
      next();
    }
  }
})

export default router



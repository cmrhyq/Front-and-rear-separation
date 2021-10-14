import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
    // 存储token
    token: localStorage.getItem('token') ? localStorage.getItem('token') : '',
    nick: localStorage.getItem('nick') ? localStorage.getItem('nick') : '',
    sex: localStorage.getItem('sex') ? localStorage.getItem('sex') : '',
    phone: localStorage.getItem('phone') ? localStorage.getItem('phone') : '',
  },
  mutations: {
    // 修改token，并将token存入localStorage
    changeLogin(state, user){
      state.token = user.token;
      localStorage.setItem('token', user.token);
      state.nick = user.nick;
      localStorage.setItem('nick', user.nick);
      state.sex = user.sex;
      localStorage.setItem('sex', user.sex);
      state.phone = user.phone;
      localStorage.setItem('phone', user.phone);
    }
  }
});

export default store

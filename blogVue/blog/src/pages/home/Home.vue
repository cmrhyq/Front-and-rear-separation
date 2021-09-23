<template>
  <div class="main-box">
    <p>{{ loginStatus }}</p>
  </div>
</template>

<script>
import message from '../../assets/js/message'
import {successTips, failTips, infoTips, warnTips, saveSuccess} from "../../assets/js/tipsInfo";
import axios from 'axios'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

export default {
  name: "Home",
  data() {
    return {
      loginStatus: '登录成功!'
    }
  },
  methods() {

  }
}
</script>

<style>
.main-box {
  width: 100%;
  height: 100%;
  text-align: center;
  font-size: 50px;
}
</style>

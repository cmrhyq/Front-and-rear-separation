<template>
  <div class="main-box">
    首页
    <el-button type="primary" round @click="tokenCheck">
      token校验
    </el-button>
  </div>
</template>

<script>
import axios from "axios";
import {failTips, successTips, warnTips, outputTips} from "../assets/js/tipsInfo";

export default {
  name: "welcome",
  data() {
    return {}
  },
  methods: {
    /**
     * token验证的请求
     */
    tokenCheck() {
      var url = 'http://localhost:7778'
      axios.post(url + '/user/test/').then((res) => {
        outputTips(res.data)
      }).catch(function (error) {
        console.log(error)
      })
    }
  }
}
</script>

<style scoped>
@import "../assets/css/public.css";

</style>

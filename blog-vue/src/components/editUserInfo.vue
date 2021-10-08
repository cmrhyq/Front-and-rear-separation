<template>
  <div class="main-box">
    <div class="nav-box">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>资料编辑</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
<!--    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">-->
<!--      <el-form-item label="活动名称" prop="name">-->
<!--        <el-input v-model="ruleForm.name"></el-input>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="活动区域" prop="region">-->
<!--        <el-select v-model="ruleForm.region" placeholder="请选择活动区域">-->
<!--          <el-option label="区域一" value="shanghai"></el-option>-->
<!--          <el-option label="区域二" value="beijing"></el-option>-->
<!--        </el-select>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="活动时间" required>-->
<!--        <el-col :span="11">-->
<!--          <el-form-item prop="date1">-->
<!--            <el-date-picker type="date" placeholder="选择日期" v-model="ruleForm.date1" style="width: 100%;"></el-date-picker>-->
<!--          </el-form-item>-->
<!--        </el-col>-->
<!--        <el-col class="line" :span="2">-</el-col>-->
<!--        <el-col :span="11">-->
<!--          <el-form-item prop="date2">-->
<!--            <el-time-picker placeholder="选择时间" v-model="ruleForm.date2" style="width: 100%;"></el-time-picker>-->
<!--          </el-form-item>-->
<!--        </el-col>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="即时配送" prop="delivery">-->
<!--        <el-switch v-model="ruleForm.delivery"></el-switch>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="活动形式" prop="desc">-->
<!--        <el-input type="textarea" v-model="ruleForm.desc"></el-input>-->
<!--      </el-form-item>-->
<!--      <el-form-item>-->
<!--        <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>-->
<!--        <el-button @click="resetForm('ruleForm')">重置</el-button>-->
<!--      </el-form-item>-->
<!--    </el-form>-->
  </div>
</template>

<script>
import axios from "axios";
import {failTips, successTips, warnTips, outputTips} from "../assets/js/tipsInfo";

export default {
  name: "EditUserInfo",
  mounted() {
    this.tokenCheck();
  },
  methods: {
    /**
     * token验证的请求
     */
    tokenCheck() {
      var url = 'http://localhost:7778'
      var user = {userPhone: localStorage.getItem("phone")}
      axios.post(url + '/user/queryInfoAsPhone/',user).then((res) => {
        console.log(res);
        if (res.data.code === 5){
          outputTips(res.data)
        }
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

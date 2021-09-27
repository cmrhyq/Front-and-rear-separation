<template>
  <el-card class="box-card" shadow="always">
    <div slot="header" class="clearfix">
      <el-icon class="el-icon-user"></el-icon>
      <span>登录</span>
    </div>
    <el-form :model="loginData" status-icon ref="loginData" label-width="60px" class="demo-ruleForm">
      <el-form-item label="手机号">
        <el-input
          type="text"
          v-model="loginData.userPhone"
          prefix-icon="el-icon-mobile-phone"
          clearable
          autocomplete="off">
        </el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input
          type="password"
          v-model="loginData.userPassword"
          prefix-icon="el-icon-lock"
          clearable
          show-password
          @keyup.enter.native="doLogin"
          autocomplete="off">
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-checkbox v-model="checked">记住密码</el-checkbox>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="doLogin()">登录</el-button>
        <el-button @click="">注册</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script>
import message from '../assets/js/message'
import {successTips, failTips, infoTips, warnTips, saveSuccess} from "../assets/js/tipsInfo";
import axios from 'axios'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

export default {
  name: "Login",
  data() {
    return {
      timeInterval: null,
      logining: false,
      loginData: {
        userPhone: '',
        userPassword: '',
        verifyCode: ''
      },
      userPhone: [
        {
          required: true,
          message: '请输入手机号',
          trigger: 'blur'
        }
      ],
      userPassword: [
        {
          required: true,
          message: '请输入密码',
          trigger: 'blur'
        }
      ],
      checked: false
    };
  },
  /**
   * 获取cookie的方法
   * mounted等同于jquery中的 window.onload
   */
  mounted() {
    this.account();
  },
  methods: {
    /**
     * 登录
     */
    doLogin() {
      var url = 'http://localhost:7778'
      // 通过正则验证手机号正确性
      var phoneVerify = /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/
      if (this.loginData.userPhone === '' || this.loginData.userPassword === '') {
        warnTips('请将您的账号或密码填写完整！^_^');
      } else {
        if (!phoneVerify.test(this.loginData.userPhone)) {
          warnTips('您的手机号码填写有误！0_0')
        } else {
          var _this = this
          axios.post(url + '/user/login/', this.loginData).then((res) => {
            // 判断是否登录成功
            // 根据后端返回的自定义状态码判断
            // 登录成功
            if (res.data.status === 201) {
              this.logining = true;
              // 判断按钮选中状态
              if (this.checked === true) {
                //存入cookie
                this.setCookie(
                  this.loginData.userPhone,
                  this.loginData.userPassword,
                  1
                );
              } else {
                // 登录失败 清除信息
                this.clearCookie();
              }
              successTips("欢迎用户：" + res.data.list[0].userInfoNick)
              // 向主页传值
              this.$router.push({
                path: '/index',
                query: {
                  userName: res.data.list[0].userInfoName,
                  userNick: res.data.list[0].userInfoNick,
                  userSex: res.data.list[0].userInfoSex,
                  userPhone: res.data.list[0].userInfoPhone,
                  userIp: res.data.list[0].userInfoIp
                }
              });
            } else {
              // 登录失败 弹出提示信息
              failTips(res.data.msg)
              this.clearCookie();
            }
          }).catch(function (error) {
            console.log(error)
          })
        }
      }

    },
    account() {
      this.loginData.userPhone = this.getCookie("userPhone");
      this.loginData.userPassword = this.getCookie("userPassword");
    },
    // 往cookie中存入信息
    setCookie(c_phone, c_pwd) {
      var ex_date = new Date();
      ex_date.setTime(ex_date.getTime() + 24 * 60 * 60 * 1000 * ex_date);
      document.cookie = 'userPhone=' + c_phone + ";path=/;expires=" + ex_date.toLocaleString();
      document.cookie = 'userPassword=' + c_pwd + ";path=/;expires=" + ex_date.toLocaleString();
    },
    // 取得cookie中的信息
    getCookie(phone) {
      var arr = document.cookie.split(';');
      for (var i = 0; i < arr.length; i++) {
        var arr2 = arr[i].split('=');
        if (arr2[0].trim() === phone) {
          return arr2[1];
        }
      }
    },
    // 清除cookie中的信息
    clearCookie() {
      this.setCookie('', '', -1)
    }
  },
  beforeCreate() {
    document.querySelector("body").setAttribute("style", "background-color: #96B7C4;")
  },
  beforeDestroy() {
    document.querySelector("body").removeAttribute("style")
  }
}
</script>

<style>
* {
  outline: none;
  text-decoration: none;
}

body {
  margin: 0;
  padding: 0;
}

.box-card {
  width: 480px;
  margin: 0 auto;
  position: relative;
  top: 200px;
  left: 0;
  right: 0;
  bottom: 0;
  box-shadow: 0 2px 12px 0 rgb(241 235 235 / 80%);
}
</style>

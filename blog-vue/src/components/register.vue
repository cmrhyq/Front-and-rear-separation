<template>
  <div>
    <el-card class="box-card" shadow="always">
      <div slot="header" class="clearfix">
        <el-icon class="el-icon-monitor"></el-icon>
        <span>{{ formTitle }}</span>
      </div>
      <el-form
        :model="regForm"
        v-show="regBox"
        status-icon
        :rules="regs"
        ref="regForm"
        label-width="100px"
        class="demo-ruleForm">
        <el-form-item label="手机号" prop="phone">
          <el-input
            type="text"
            v-model="regForm.phone"
            prefix-icon="el-icon-mobile-phone"
            autocomplete="off"
            clearable>
          </el-input>
        </el-form-item>
        <el-form-item label="密码" prop="pass">
          <el-input
            type="password"
            v-model="regForm.pass"
            prefix-icon="el-icon-lock"
            autocomplete="off"
            show-password>
          </el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="checkPass">
          <el-input
            type="password"
            v-model="regForm.checkPass"
            prefix-icon="el-icon-lock"
            autocomplete="off"
            @keyup.enter.native="register"
            show-password>
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="register">注册</el-button>
          <el-button @click="resetForm">重置</el-button>
          <router-link to="/" class="to-login" style="float: right;margin-right: 20px">
            <el-icon class="el-icon-back"></el-icon>
            已有账号，去登陆
          </router-link>
        </el-form-item>
      </el-form>

      <el-form
        :model="regInfoForm"
        :rules="regInfos"
        ref="regInfoForm"
        v-show="infoBox"
        status-icon
        label-width="100px">
        <el-form-item label="手机号:" prop="phone">
          <el-input
            type="text"
            v-model="regInfoForm.phone"
            prefix-icon="el-icon-mobile-phone"
            autocomplete="off"
            disabled
            clearable>
          </el-input>
        </el-form-item>
        <el-form-item label="昵称:" prop="nick">
          <el-input
            type="text"
            v-model="regInfoForm.nick"
            prefix-icon="el-icon-notebook-2"
            autocomplete="off"
            placeholder="请输入一个心仪的昵称"
            clearable>
          </el-input>
        </el-form-item>
        <el-form-item label="姓名:" prop="name">
          <el-input
            type="text"
            v-model="regInfoForm.name"
            prefix-icon="el-icon-user"
            autocomplete="off"
            placeholder="请输入您的姓名"
            clearable>
          </el-input>
        </el-form-item>
        <el-form-item label="性别:">
          <el-radio-group v-model="regInfoForm.sex">
            <el-radio label="男" border></el-radio>
            <el-radio label="女" border></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="年龄:">
          <el-input
            type="text"
            v-model="regInfoForm.age"
            prefix-icon="el-icon-toilet-paper"
            autocomplete="off"
            placeholder="请输入输入您的年龄"
            clearable>
          </el-input>
        </el-form-item>
        <el-form-item label="证件类型:">
          <el-select placeholder="请选择证件类型" v-model="regInfoForm.idType" value="">
            <el-option label="身份证" value="身份证"></el-option>
            <el-option label="士官证" value="士官证"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="证件号:">
          <el-input
            type="text"
            v-model="regInfoForm.idNumber"
            prefix-icon="el-icon-postcard"
            autocomplete="off"
            placeholder="请输入您相应证件的证件号"
            clearable>
          </el-input>
        </el-form-item>
        <el-form-item label="Email:">
          <el-input
            type="text"
            v-model="regInfoForm.email"
            prefix-icon="el-icon-message"
            autocomplete="off"
            placeholder="请输入您的电子邮箱"
            clearable>
          </el-input>
        </el-form-item>
        <el-form-item label="省市区:">
          <el-cascader
            v-model="regInfoForm.address"
            :options="city"
            filterable
            clearable>
            <template slot-scope="{ node, data }">
              <span>{{ data.label }}</span>
              <span v-if="!node.isLeaf">({{ data.children.length }})</span>
            </template>
          </el-cascader>
        </el-form-item>
        <el-form-item label="详细地址:">
          <el-input
            v-model="regInfoForm.addDetail"
            placeholder="请输入您的详细地址"
            clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="saveInfo">保存</el-button>
        </el-form-item>
      </el-form>

      <el-divider content-position="left"></el-divider>
      <el-steps
        :active="active"
        :process-status="proStatus"
        :finish-status="status"
        align-center
        class="state-box">
        <el-step title="注册账号"></el-step>
        <el-step title="填写资料"></el-step>
        <el-step title="注册成功"></el-step>
      </el-steps>
    </el-card>
  </div>
</template>

<script>
import md5 from "js-md5";
import axios from "axios";
import 'element-ui/lib/theme-chalk/base.css';
import {regionData, CodeToText, TextToCode} from 'element-china-area-data'
import {failTips, successTips, warnTips, outputTips} from "../assets/js/tipsInfo";

export default {
  name: "register",
  data() {
    var validateEmail = (rule, value, callback) => {
      if (value === '') {
        callback(new Error("请输入您的email地址"));
      } else {
        var emailVerify = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
        if (!emailVerify.test(value)) {
          callback(new Error("Email格式错误"))
        }
        callback()
      }
    }

    var validatePhone = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入您的手机号'));
      } else {
        if (this.regForm.phone !== '') {
          var phoneVerify = /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/
          if (!phoneVerify.test(value)) {
            callback(new Error('手机号码填写有误'));
          }
        }
        callback();
      }
    };
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请设置您的密码'));
      } else {
        if (value.length < 6) {
          callback(new Error('密码长度小于六位'));
        } else {
          if (this.regForm.checkPass !== '') {
            this.$refs.regForm.validateField('checkPass');
          }
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入设置的密码'));
      } else {
        if (value.length < 6) {
          callback(new Error('密码长度小于六位'));
        } else {
          if (value !== this.regForm.pass) {
            callback(new Error('两次输入的密码不一致'))
          } else {
            callback();
          }
        }
      }
    };
    return {
      city: regionData,
      formTitle: '注册账号',
      proStatus: '',
      status: 'success',
      regBox: true,
      infoBox: false,
      regResult: false,
      active: 0,
      regInfoForm: {
        phone: '',
        nick: '',
        name: '',
        sex: '',
        age: '',
        idType: '身份证',
        idNumber: '',
        email: '',
        address: '',
        addDetail: '',
      },
      regInfos: {
        email: [
          {validator: validateEmail, trigger: 'blur'}
        ]
      },
      regForm: {
        phone: '',
        pass: '',
        checkPass: '',
      },
      regs: {
        phone: [
          {validator: validatePhone, trigger: 'blur'}
        ],
        pass: [
          {validator: validatePass, trigger: 'blur'}
        ],
        checkPass: [
          {validator: validatePass2, trigger: 'blur'}
        ]
      }
    };
  },
  methods: {
    resetForm(formName) {
      this.$refs['regForm'].resetFields();
    },
    register() {
      this.$refs['regForm'].validate((valid) => {
        if (valid) {
          var url = 'http://localhost:7778'
          var data = {userPhone: this.regForm.phone, userPassword: md5(this.regForm.pass)}
          axios.post(url + '/user/register/', data).then((res) => {
            if (outputTips(res.data)) {
              if (this.active++ > 2) {
                this.active = 0
              }
              this.regBox === true ? this.regBox = false : this.regBox = true;
              this.infoBox === true ? this.infoBox = false : this.infoBox = true;
              this.regInfoForm.phone = this.regForm.phone;
              this.formTitle = '填写个人资料';
            }
          }).catch(function (error) {
            console.log(error);
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    saveInfo() {
      this.$refs['regInfoForm'].validate((valid) => {
        var url = 'http://localhost:7778'
        var userInfo = {}
        // this.proStatus = 'success';
        // if (this.active++ > 2) {
        //   this.active = 0;
        // }
        // this.regResult === true ? this.regResult = false : this.regResult = true;
        // this.infoBox === true ? this.infoBox = false : this.infoBox = true;
      });
    }
  },
  beforeCreate() {
    document.querySelector("body").setAttribute("style", "background-color: #96B7C4;")
  },
  beforeDestroy() {
    // document.querySelector("body").removeAttribute("style")
  }
}
</script>

<style scoped>
@import "../assets/css/public.css";

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
  top: 100px;
  left: 0;
  right: 0;
  bottom: 0;
  box-shadow: 0 2px 12px 0 rgb(241 235 235 / 80%);
}

.to-login {
  color: #545c64;
}

.to-login:hover {
  color: #53a8ff;
}
</style>

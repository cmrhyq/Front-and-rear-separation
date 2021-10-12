<template>
  <div class="main-box">
    <div class="nav-box">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>资料编辑</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <el-divider></el-divider>
    <el-card class="body-box">
      <el-form :inline="false" label-position="left" label-width="80px" :model="infoForm">
        <el-form-item label="昵称:">
          <el-input v-model="infoForm.nick" clearable></el-input>
        </el-form-item>
        <el-form-item label="姓名:">
          <el-input v-model="infoForm.name" clearable></el-input>
        </el-form-item>
        <el-form-item label="手机号:">
          <el-input v-model="infoForm.phone" disabled></el-input>
        </el-form-item>
        <el-form-item label="邮箱:">
          <el-input v-model="infoForm.email" clearable></el-input>
        </el-form-item>
        <el-form :inline="true" label-position="left" label-width="80px">
          <el-form-item label="性别:">
            <el-radio-group v-model="infoForm.sex">
              <el-radio label="男" border></el-radio>
              <el-radio label="女" border></el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="年龄:" style="margin-left: 100px">
            <el-tag
              type="success"
              effect="plain">
              {{ infoForm.age }}
            </el-tag>
          </el-form-item>
          <el-form-item label="证件类型:" style="float: right">
            <el-select v-if="infoForm.idType === ''" placeholder="请选择证件类型">
              <el-option label="身份证" value="身份证"></el-option>
              <el-option label="士官证" value="士官证"></el-option>
            </el-select>
            <el-tag v-else type="success">{{ infoForm.idType }}</el-tag>
          </el-form-item>
        </el-form>
        <el-form-item label="证件号:">
          <el-input v-if="infoForm.idNumber !== ''" v-model="infoForm.idNumber" disabled></el-input>
          <el-input v-else v-model="infoForm.idNumber" clearable></el-input>
        </el-form-item>
        <el-form-item label="省市区:">
          <el-cascader
            v-model="infoForm.address"
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
          <el-input v-model="infoForm.addDetail" clearable></el-input>
        </el-form-item>
        <el-form-item label="注册时间:">
          <el-input v-model="infoForm.regTime" disabled></el-input>
        </el-form-item>
        <el-form-item label="最近登录:">
          <el-input v-model="infoForm.lastLoginTime" disabled></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-edit" @click="updateUserInfo">保存</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import axios from "axios";
import moment from 'moment'
import {failTips, successTips, warnTips, outputTips} from "../assets/js/tipsInfo";
import {regionData, CodeToText, TextToCode} from 'element-china-area-data'

export default {
  name: "EditUserInfo",
  mounted() {
    this.getUserInfo();
  },
  data() {
    return {
      infoForm: {
        id: '',
        nick: '',
        name: '',
        sex: '',
        age: '',
        phone: '',
        email: '',
        idType: '',
        idNumber: '',
        address: '',
        addDetail: '',
        regTime: '',
        lastLoginTime: '',
      },
      city: regionData
    }
  },
  methods: {
    /**
     * 数据获取的方法
     */
    getUserInfo() {
      var url = 'http://localhost:7778'
      var user = {userPhone: localStorage.getItem("phone")}
      axios.post(url + '/user/queryInfoAsPhone/', user).then((res) => {
        console.log(res);
        if (res.data.code === 5) {
          outputTips(res.data)
          // 页面跳转
          this.$router.push({path: '/'});
        }
        this.infoForm.id = res.data.userId
        this.infoForm.nick = res.data.userInfoNick
        this.infoForm.name = res.data.userInfoName
        this.infoForm.sex = res.data.userInfoSex
        this.infoForm.age = res.data.userInfoAge
        this.infoForm.phone = res.data.userPhone
        this.infoForm.email = res.data.userInfoEmail
        this.infoForm.idType = res.data.userInfoIdType
        this.infoForm.idNumber = res.data.userInfoIdNumber
        this.infoForm.address = this.cityToCode(res.data.userInfoProvince)
        this.infoForm.addDetail = res.data.userInfoAddress
        this.infoForm.regTime = moment(res.data.userRegTime).format("YYYY-MM-DD HH:mm:ss")
        this.infoForm.lastLoginTime = moment(res.data.userLastTime).format("YYYY-MM-DD HH:mm:ss")
      }).catch(function (error) {
        console.log(error)
      })
    },
    /**
     * 更新个人信息
     */
    updateUserInfo() {
      var url = 'http://localhost:7778'
      var userinfo = {
        userInfoNick: this.infoForm.nick,
        userInfoName: this.infoForm.name,
        userInfoSex: this.infoForm.sex,
        userInfoEmail: this.infoForm.email,
        userInfoProvince: this.codeToCity(this.infoForm.address),
        userInfoAddress: this.infoForm.addDetail,
        userLoginId: this.infoForm.id
      }
      console.log(userinfo)
      axios.post(url + '/user/edit/userInfo/',userinfo).then((res) => {
        console.log(res);
        if (res.data.code === 5) {
          outputTips(res.data)
          // 页面跳转
          this.$router.push({path: '/'});
        }
      }).catch(function (error) {
        console.log(error)
      })
    },
    /**
     * 省市区code解析成汉字
     * @param data
     * @returns {string}
     */
    codeToCity(data) {
      let area = ''
      switch (data.length) {
        case 1:
          area += CodeToText[data[0]];
          break;
        case 2:
          area += CodeToText[data[0]] + "/" + CodeToText[data[1]];
          break;
        case 3:
          area += CodeToText[data[0]] + "/" + CodeToText[data[1]] + "/" + CodeToText[data[2]];
          break;
        default:
          console.log("error:The result length is too long when parsing the city code array")
          break;
      }
      return area
    },
    /**
     * 将汉字解析成code
     * @param data
     * @returns {*[]}
     */
    cityToCode(data) {
      var city = []
      var arr = data.split('/');
      switch (arr.length) {
        case 1:
          city.push(TextToCode[arr[0]]);
          break;
        case 2:
          city.push(TextToCode[arr[0]][arr[1]]);
          break;
        case 3:
          city.push(TextToCode[arr[0]][arr[1]][arr[2]]);
          break;
        default:
          console.log("error:The length of the result is too long when parsing the city array")
          break;
      }
      return city[0].code
    }
  }
}
</script>

<style scoped>
@import "../assets/css/public.css";

.body-box {
  margin: 0 auto;
  width: 800px;
}
</style>

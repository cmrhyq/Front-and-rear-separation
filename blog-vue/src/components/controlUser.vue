<template>
  <div class="main-box">
    <div class="nav-box" style="">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>系统设置</el-breadcrumb-item>
        <el-breadcrumb-item>用户设置</el-breadcrumb-item>
        <el-breadcrumb-item>用户控制</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <el-table
      border
      :data="tableData.filter(
      data => !search ||
      // data.name.toLowerCase().includes(search.toLowerCase()) ||
      data.userPhone.includes(search))"
      stripe="stripe"
      fit>
      <el-table-column
        label="Id"
        prop="userId">
      </el-table-column>
      <el-table-column
        label="Phone"
        prop="userPhone">
      </el-table-column>
      <el-table-column
        label="Password"
        prop="userPassword">
      </el-table-column>
      <el-table-column
        label="Account status"
        prop="userStatus">
      </el-table-column>
      <el-table-column
        label="Register time"
        prop="userRegTime">
      </el-table-column>
      <el-table-column
        label="Last login time"
        prop="userLastLoginIp">
      </el-table-column>
      <el-table-column
        label="Last login ip"
        prop="userLastTime">
      </el-table-column>
      <el-table-column
        label="Session id"
        prop="userSession">
      </el-table-column>
      <el-table-column
        align="right">
        <template slot="header" slot-scope="scope">
          <el-input
            v-model="search"
            size="mini"
            placeholder="输入关键字搜索"/>
        </template>
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="handleEdit(scope.$index, scope.row)">Edit
          </el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)">Delete
          </el-button>
        </template>
      </el-table-column>
    </el-table>

  </div>
</template>

<script>
import axios from "axios";
import {outputTips} from "../assets/js/tipsInfo";

export default {
  name: "ControlUser",
  data() {
    return {
      tableData: [],
      search: ''
    }
  },
  mounted() {
    this.loadTableData();
  },
  methods: {
    loadTableData() {
      var url = 'http://localhost:7778'
      axios.post(url + '/user/table/controllerUser/').then((res) => {
        if (res.data.code === 5){
          outputTips(res.data)
        }
        this.tableData = res.data;
      }).catch(function (error) {
        console.log(error)
      })
    },
    handleEdit(index, row) {
      console.log(index, row)
    },
    handleDelete(index, row) {
      console.log(index, row)
    }
  }
}
</script>

<style scoped>
@import "../assets/css/public.css";
el-table{
  margin: 0 auto;
}
</style>

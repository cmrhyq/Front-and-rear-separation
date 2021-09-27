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
      :data="tableData.filter(
      data => !search ||
      data.id.includes(search) ||
      data.name.toLowerCase().includes(search.toLowerCase()) ||
      data.nick.includes(search) ||
      data.phone.includes(search))"
      fit>
      <el-table-column
        label="Id"
        prop="id">
      </el-table-column>
      <el-table-column
        label="Name"
        prop="name">
      </el-table-column>
      <el-table-column
        label="Nick"
        prop="nick">
      </el-table-column>
      <el-table-column
        label="Phone"
        prop="phone">
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
export default {
  name: "ControlUser",
  data() {
    return {
      tableData: [{
        id: '待',
        name: '开',
        nick: "发",
        phone: '中'
      }],
      search: ''
    }
  },
  mounted() {
    this.loadTableData();
  },
  methods: {
    loadTableData() {

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

<style>
* {
  outline: none;
  text-decoration: none;
}

body {
  margin: 0;
  padding: 0;
}

.main-box {
  width: 100%;
  height: 100%;
}

.nav-box {
  overflow: hidden;
  padding: 50px;
}
</style>

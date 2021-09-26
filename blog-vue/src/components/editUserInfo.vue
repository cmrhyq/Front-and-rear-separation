<template>
  <el-table
    :data="tableData.filter(
      data => !search ||
      data.id.includes(search) ||
      data.name.toLowerCase().includes(search.toLowerCase()) ||
      data.nick.includes(search) ||
      data.phone.includes(search))"
    style="width: 1000px"
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
</template>

<script>
export default {
  name: "EditUserInfo",
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
body {
  display: flex;
  align-items: center;
  justify-content: center;
  background-image: linear-gradient(-25deg, #70eed1 0%, #96B7C4 100%);
}

el-table {
  width: 100%;
  height: 100%;
  margin: 0;
  padding: 0;
}
</style>

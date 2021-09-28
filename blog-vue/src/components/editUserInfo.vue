<template>
  <div class="main-box">
    <div class="nav-box">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>资料编辑</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
  </div>
</template>

<script>
export default {
  name: "EditUserInfo"
}
</script>

<style scoped>
@import "../assets/css/public.css";

</style>

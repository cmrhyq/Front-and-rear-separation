<template>
<div class="main-box">
  <div class="nav-box">
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
    </el-breadcrumb>
  </div>
</div>
</template>

<script>
export default {
  name: "welcome"
}
</script>

<style scoped>
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

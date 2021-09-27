<template>
  <div class="main-box">
    <el-row>
      <!--    顶栏-->
      <el-col>
        <el-menu
          :default-active="activeIndex"
          class="el-menu-demo"
          mode="horizontal"
          @select="handleSelect"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b">
          <el-submenu index="1" style="float: right">
            <template slot="title">
              <i class="el-icon-user"></i>
              <span>{{ userNick }}</span>
            </template>
            <router-link to="editUserInfo">
              <el-menu-item index="editUserInfo">编辑资料</el-menu-item>
            </router-link>
            <el-menu-item index="modifyPwd">修改密码</el-menu-item>
            <el-menu-item index="logout">退出系统</el-menu-item>
          </el-submenu>
        </el-menu>
      </el-col>
      <!--    侧栏-->
      <el-col :span="12" style="width: 200px;float: left">
        <el-menu
          default-active="2"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b"
          style="height: 900px;overflow:auto;">
          <div class="logo-item">
            <h1 style="text-align: center">Logo</h1>
          </div>
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-location"></i>
              <span>首页</span>
            </template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="1-1">选项1</el-menu-item>
              <el-menu-item index="1-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="1-3">选项3</el-menu-item>
              <el-menu-item index="1-3">选项4</el-menu-item>
              <el-menu-item index="1-3">选项5</el-menu-item>
              <el-menu-item index="1-3">选项6</el-menu-item>
              <el-menu-item index="1-3">选项7</el-menu-item>
              <el-menu-item index="1-3">选项8</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="1-4">
              <template slot="title">选项4</template>
              <el-menu-item index="1-4-1">选项1</el-menu-item>
              <el-menu-item index="1-2">选项2</el-menu-item>
              <el-menu-item index="1-3">选项3</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-menu"></i>
              <span slot="title">工作台</span>
            </template>
          </el-submenu>
          <el-menu-item index="3">
            <i class="el-icon-document"></i>
            <span slot="title">导航三</span>
          </el-menu-item>
          <el-submenu index="4">
            <template slot="title">
              <i class="el-icon-setting"></i>
              <span slot="title">系统设置</span>
            </template>
            <el-menu-item-group title="用户设置">
              <router-link to="controlUser">
                <el-menu-item index="controlUser">用户控制</el-menu-item>
              </router-link>
            </el-menu-item-group>
            <el-menu-item-group title="权限管理">
              <router-link to="">
                <el-menu-item index="authorControl">用户权限管理</el-menu-item>
              </router-link>
            </el-menu-item-group>
            <el-menu-item-group title="系统设置">
              <router-link to="">
                <el-menu-item index="menuSetting">菜单设置</el-menu-item>
              </router-link>
              <router-link to="">
                <el-menu-item index="logManage">日志管理</el-menu-item>
              </router-link>
            </el-menu-item-group>
          </el-submenu>
        </el-menu>
      </el-col>
      <el-col :span="20" style="height: 100%;overflow: auto;">
        <keep-alive>
          <router-view></router-view>
        </keep-alive>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Index",
  data() {
    return {
      userNick: '',
      activeIndex: '1'
    }
  },
  created() {
    this.getParams()
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath)
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath)
    },
    handleSelect(key, keyPath) {
      console.log(key, keyPath)
    },
    getParams() {
      this.userNick = this.$route.query.userNick
    }
  }
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

.el-menu-vertical-demo::-webkit-scrollbar { /*滚动条整体样式*/
  width: 10px; /*高宽分别对应横竖滚动条的尺寸*/
  height: 1px;
}

.el-menu-vertical-demo::-webkit-scrollbar-thumb { /*滚动条里面小方块*/
  border-radius: 10px;
  -webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
  background: #ffffff;
}

.el-menu-vertical-demo::-webkit-scrollbar-track { /*滚动条里面轨道*/
  /*-webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);*/
  /*border-radius: 10px;*/
  background: #545c64;
}

.main-box {
  width: 100%;
  height: 100%;
}

.logo-item {
  color: #ffffff;
}
</style>

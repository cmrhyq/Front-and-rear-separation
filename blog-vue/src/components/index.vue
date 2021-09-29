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
          <el-menu-item class="logo-item">
            <router-link to="index">
              logo
            </router-link>
          </el-menu-item>
          <el-menu-item @click="hiddenOrShowNav" class="fold" title="收起菜单">
            <i id="fold-menu" class="el-icon-s-fold"></i>
          </el-menu-item>
          <el-submenu index="1" style="float: right">
            <template slot="title">
              <i class="el-icon-user"></i>
              <span>{{ userNick }}</span>
            </template>
            <router-link to="editUserInfo">
              <el-menu-item index="editUserInfo">编辑资料</el-menu-item>
            </router-link>
            <el-menu-item index="modifyPwd">修改密码</el-menu-item>
            <el-menu-item index="logout" @click="logout">退出系统</el-menu-item>
          </el-submenu>
          <el-menu-item index="2" style="float: right">
            <i class="el-icon-s-comment"></i>
            <span>消息中心</span>
          </el-menu-item>
        </el-menu>
      </el-col>
      <!--    侧栏-->
      <el-col :span="12" style="width: 230px;float: left">
        <el-menu
          default-active="2"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b"
          :collapse="isCollapse"
          style="height: 880px;overflow:auto;">
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-location"></i>
              <span>首页</span>
            </template>
            <el-menu-item-group>
              <el-menu-item>

              </el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-menu"></i>
              <span slot="title">交易管理</span>
            </template>
            <el-menu-item-group title="订单管理">
              <router-link to="">
                <el-menu-item index="productSell">已卖出宝贝</el-menu-item>
              </router-link>
            </el-menu-item-group>
            <el-menu-item-group title="物流管理">
              <router-link to="">
                <el-menu-item index="productShip">发货</el-menu-item>
              </router-link>
            </el-menu-item-group>
            <el-menu-item-group title="投诉与申诉">
              <router-link to="">
                <el-menu-item index="complaint">我要投诉</el-menu-item>
              </router-link>
            </el-menu-item-group>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title">
              <i class="el-icon-document"></i>
              <span slot="title">商品管理</span>
            </template>
            <el-menu-item-group title="商品管理">
              <router-link to="">
                <el-menu-item index="productShelves">上架管理</el-menu-item>
              </router-link>
            </el-menu-item-group>
          </el-submenu>
          <el-submenu index="4">
            <template slot="title">
              <i class="el-icon-setting"></i>
              <span slot="title">店铺设置</span>
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
  name: "index",
  data() {
    return {
      isCollapse: false,
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
    /**
     * 退出登录
     * 删除localStorage中的token
     */
    logout() {
      localStorage.removeItem('token');
      this.$router.push('/');
    },
    /**
     * 显示隐藏侧边栏
     */
    hiddenOrShowNav() {
      if (this.isCollapse === true) {
        this.isCollapse = false;
        $(".fold").attr("title", "收起左侧侧边栏")
        $("#fold-menu").removeClass("el-icon-s-unfold").addClass("el-icon-s-fold");
      } else {
        this.isCollapse = true;
        $(".fold").attr("title", "展开左侧侧边栏")
        $("#fold-menu").removeClass("el-icon-s-fold").addClass("el-icon-s-unfold");
      }
    },
    /**
     * 获取昵称
     */
    getParams() {
      this.userNick = this.$route.query.userNick
    }
  }
}
</script>

<style scoped>
@import "../assets/css/public.css";

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

.logo-item {
  color: #ffffff;
  margin-left: 100px;
  font-family: "Sitka Display", serif;
  font-style: italic;
  font-size: 30px;
  text-align: center
}
</style>

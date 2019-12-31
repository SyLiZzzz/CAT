<template>
  <div>
    <el-container>
      <el-aside width="200px">
        <el-menu
              default-active="1"
              @select="changeRoute"
              class="el-menu-vertical-demo"
              background-color="#545c64"
              text-color="#fff"
              active-text-color="#ffd04b">
              <el-menu-item index="1">
                <i class="el-icon-menu"></i>
                <span slot="title">用户管理</span>
              </el-menu-item>
              <el-menu-item index="2">
                <i class="el-icon-goblet-square-full"></i>
                <span slot="title">饮品管理</span>
              </el-menu-item>
              <el-menu-item index="3">
                <i class="el-icon-document"></i>
                <span slot="title">订单管理</span>
              </el-menu-item>
              <el-menu-item index="4">
                <i class="el-icon-map-location"></i>
                <span slot="title">地址管理</span>
              </el-menu-item>
            </el-menu>
      </el-aside>
      <el-container>
        <el-header>
          <div class="personal-left fl"><i class="el-icon-coffee-cup"></i>名典咖啡语茶后台管理系统</div>
          <div class="personal-right fr">
           <el-dropdown @command="check">
             <span class="el-dropdown-link">
               欢迎您：{{user}}<i class="el-icon-arrow-down el-icon--right"></i>
               </span>
             <el-dropdown-menu slot="dropdown">
               <el-dropdown-item :command="1">退出登录</el-dropdown-item>
             </el-dropdown-menu>
           </el-dropdown>
          </div>
        </el-header>
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script>
export default {
  data () {
    return {
      dialogFormVisible: false,
      user: ''
    }
  },
  components: {

  },
  computed: {

  },
  methods: {
    changeRoute (index) {
      let map = new Map()
      map.set('1', 'userManager')
      map.set('2', 'shoesManager')
      map.set('3', 'orderManager')
      map.set('4', 'addressManager')
      let routeUrl = map.get(index)
      this.$router.push(routeUrl)
    },
    check (command) {
      if (command === 1) {
        sessionStorage.clear()
        this.$router.push('/adminlogin')
      }
    },
    checkLogin () {
      let userId = sessionStorage.getItem('user_id')
      let account = sessionStorage.getItem('account')
      let userRoleId = sessionStorage.getItem('userRoleId')
      if (userId === null && account === null && userRoleId !== 1) {
        this.$router.push('/adminlogin')
        this.$message({
          type: 'error',
          message: '亲，请先登录哦'
        })
      }
    }
  },
  mounted () {
    this.checkLogin()
    this.user = sessionStorage.getItem('account')
  }
}
</script>

<style lang="scss">
@import '../../assets/style/common.scss';
 .el-header {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
    border-bottom: 1px solid red;
    .personal-right {
      .el-dropdown-link {
        font-size: 16px;
        margin-right: 50px;
        cursor: pointer;
      }
    }
  }

  .el-aside {
    background-color: #545c64;
    color: #333;
    text-align: center;
    .el-menu-vertical-demo {
      margin-top: 30px;
    }
  }

  .el-main {
    background-color: #E9EEF3;
    color: #333;
    height: 678px;
  }
</style>

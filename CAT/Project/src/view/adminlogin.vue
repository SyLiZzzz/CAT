<template>
      <!--最大的父层-->
      <el-row type="flex" justify="center" align="middle" class="row">
        <div class="bg"></div>
        <el-col :span='5' class="form-box">
        <el-row type="flex" justify="center" align="middle">
            <el-col  class="title">
              <h3>名典咖啡语茶后台管理系统</h3>
            </el-col>
          </el-row>
        <el-row type='flex'  justify="center" align="middle" class="form-body">
          <el-col :span='16'>
            <el-form ref="loginForm" size="small" :rules="rules" :model="user">
              <el-form-item prop="account" label="账号">
                <el-input v-model="user.account"  clearable>
                  <i class="el-icon-s-custom" slot="prefix"></i>
                </el-input>
              </el-form-item>
              <el-form-item prop="password" label="密码">
                <el-input v-model="user.password" clearable show-password>
                  <i class="el-icon-lock" slot="prefix"></i>
                </el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" class="btn-login" @click="login('loginForm')">登录</el-button>
              </el-form-item>
            </el-form>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
</template>
<script>
import http from '@/utils/HttpUtil'
export default {
  data () {
    let checkPhone = (rule, value, callback) => {
      let phoneReg = /^1[3456789]\d{9}$/
      if (!phoneReg.test(value)) {
        return callback(new Error('账号必需为手机号格式!'))
      }
      callback()
    }
    let checkPwd = (rule, value, callback) => {
      let pwdReg = /^[a-z0-9_-]{6,18}$/
      if (!pwdReg.test(value)) {
        return callback(new Error('请输入六位数以上的字符'))
      }
      callback()
    }
    return {
      user: {
        account: '',
        password: ''
      },
      rules: {
        account: [{
          required: true, message: '请输入用户名', trigger: 'blur'
        },
        {
          validator: checkPhone, trigger: 'blur'
        }],
        password: [{
          required: true, message: '请输入正确的密码', trigger: 'blur'
        },
        {
          validator: checkPwd, trigger: 'blur'
        }]
      },
      action: {
        login: 'CAT/User/loginAdmin'
      },
      newPwd: ''
    }
  },
  components: {

  },
  mounted () {
  },
  methods: {
    login (refName) {
      this.$refs[refName].validate((valid) => {
        if (valid) {
          let params = {
            phone: this.user.account,
            password: this.user.password
          }
          http.xhrPost(this, this.action.login, params, (res) => {
            this.$Spin.show()
            sessionStorage.setItem('user_id', res.data.userId)
            sessionStorage.setItem('account', res.data.account)
            sessionStorage.setItem('phone', res.data.phone)
            sessionStorage.setItem('userRoleId', res.data.userRoleId)
            setTimeout(() => {
              this.$message({
                type: 'success',
                message: res.message
              })
              this.$Spin.hide()
              this.$router.push('/adminindex')
            }, 1000)
          })
        }
      })
    }
  }
}
</script>
<style lang="scss">
  @import '../assets/style/common.scss';
  .row {
    height: 100%;
      .bg {
        width: 100%;
        height: 40%;
        background: url(../assets/img/login/login.png) no-repeat;
        background-size: 100% 100%;
      }
     .form-box {
        position: absolute;
        height: 60%;
        background-color: #fff;
        border-radius: 5px;
        border: 1px solid #e4e4e4;
        box-shadow: 1px 1px 5px rgba(0,0,0,.5);
        border-top: 5px solid $red;
        .title {
            margin-top: 20px;
            text-align: center;
            h3 {
              color: $red;
              font-size: 16px;
              font-weight: 300;
            }
        }
     }
     .form-body{
       margin-top: 40px;
       i {
          color: $red;
       }
       .btn-login {
         width: 100%;
       }
       .outer-chain {
         color: $red;
         i  {
           margin-right: 3px;
         }
       }
     }
  }
</style>

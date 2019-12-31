<template>
<el-container>
    <!-- 头部导航栏-->
  <el-header class="head" height="85px">
      <!-- 左部logo-->
    <div class="logo fl">
        <p class="p1">名典</p>
        <p class="p2">Best Drinks</p>
    </div>
    <!-- 右部信息-->
    <div class="entrance fr">
        <div class="entrance-left">
            <!-- 级联信息-->
            <Cascader :data="data">
            <span>饮品</span><i class="el-icon-caret-bottom"></i>
            </Cascader>
        </div>
        <div class="entrance-right">
            <i class="el-icon-s-custom"></i>
            <span @click="modal1 = true">注册</span><span @click="modal2 = true">登录</span>
            <!-- 注册对话框-->
            <Modal v-model='modal1' title="注册" @on-ok="onloading('formValidate')" @on-cancel="clear">
                <template>
                      <Form ref="formValidate" :model="form" :label-width="80" :rules="ruleValidate">
                              <FormItem label="用户名" prop="user">
                                  <i-input type="text" v-model="form.user" placeholder="Username">
                                      <Icon type="ios-person-outline" slot="prepend"></Icon>
                                  </i-input>
                              </FormItem>
                              <FormItem label="手机号" prop="telephone">
                                  <i-input type="text" v-model="form.telephone" placeholder="Telephone">
                                      <Icon type="ios-phone-portrait" slot="prepend"></Icon>
                                  </i-input>
                              </FormItem>
                              <FormItem label="性别" prop="gender">
                                <RadioGroup v-model="form.gender">
                                    <Radio label="0">男</Radio>
                                    <Radio label="1">女</Radio>
                                </RadioGroup>
                               </FormItem>
                               <FormItem label="密码" prop="password">
                                   <i-input type="password" v-model="form.password" placeholder="Password">
                                       <Icon type="ios-lock-outline" slot="prepend"></Icon>
                                   </i-input>
                               </FormItem>
                               <FormItem label="二次密码" prop="newPwd">
                                   <i-input type="password" v-model="form.newPwd" placeholder="Secondary password">
                                       <Icon type="ios-lock-outline" slot="prepend"></Icon>
                                   </i-input>
                               </FormItem>
                          </Form>
                          <a class="fl" style="font-size: 12px;color: #ff6700;margin-left: 16%;" @click="tologins">立即登录</a>
                </template>
            </Modal>
            <!-- 登录对话框-->
            <Modal v-model='modal2' title="账号登录"  @on-ok="onloading2('formValidate2')" @on-cancel="clear">
                <template>
                      <Form ref="formValidate2" :model="form" :label-width="80" :rules="ruleValidate">
                              <FormItem label="用户名" prop="user">
                                  <i-input type="text" v-model="form.user" placeholder="Username">
                                      <Icon type="ios-person-outline" slot="prepend"></Icon>
                                  </i-input>
                              </FormItem>
                               <FormItem label="密码" prop="password">
                                   <i-input type="password" v-model="form.password" placeholder="Password">
                                       <Icon type="ios-lock-outline" slot="prepend"></Icon>
                                   </i-input>
                               </FormItem>
                          </Form>
                          <a class="fl" style="font-size: 12px;color: #ff6700;margin-left: 30%;" @click="MessageCode">短信登录</a>
                          <a class="fr" style="font-size: 12px;color: #999;margin-right: 30%;" @click="tologin">立即注册</a>
                </template>
            </Modal>
            <!-- 手机登录对话框-->
            <Modal v-model='modal3' title="手机短信登录"  @on-ok="onloading3('formValidate3')" @on-cancel="clear">
                <template>
                      <Form ref="formValidate3" :model="form" :label-width="80" :rules="ruleValidate">
                              <FormItem label="手机号" prop="telephone">
                                  <i-input type="text" v-model="form.telephone" placeholder="Telephone">
                                      <Icon type="ios-person-outline" slot="prepend"></Icon>
                                  </i-input>
                              </FormItem>
                               <FormItem label="验证码" prop="code">
                                   <i-input type="text" v-model="form.sendCode" placeholder="Verification Code">
                                       <Icon type="ios-lock-outline" slot="prepend"></Icon>
                                   </i-input>
                               </FormItem>
                          </Form>
                          <a class="fl" style="font-size: 12px;color: #ff6700;margin-left: 24%;" @click="AccountCode">账号登录</a>
                          <a class="fl aa" style="font-size: 12px;color:#999;margin-left: 22%;" @click="sendCode" :disabled="disabled=!show">
                              <span v-show="show">获取验证码</span>
                              <span v-show="!show" class="count">{{count}} s后获取验证码</span>
                          </a>
                </template>
            </Modal>
        </div>
    </div>
  </el-header>
  <!-- 主体信息-->
  <el-main class="body">
      <div class="content">
          <!-- 标语-->
        <div class="Slogan">
            <p class="p1">为您提供最好的咖啡语茶ღ</p>
            <p class="p2">Provide you with the best coffee and tea</p>
        </div>
        <!-- 搜索框-->
        <!-- <div class="search">
            <div class="bg">
                <div class="form">
                    <el-input placeholder="  Enter the name of your drink" suffix-icon="el-icon-coffee-cup" v-model="name" class="input1 fl" large clearable></el-input>
                    <el-button type="danger" class="button">Search</el-button>
                </div>
            </div>
        </div> -->
      </div>
  </el-main>
  <el-footer class="tail" height="40px">
      <div><span>Copyright © 2019-2020 SyLiZzzz版权所有。</span></div>
  </el-footer>
</el-container>
</template>
<script>
import Http from '@/utils/HttpUtil'
const TIME_COUNT = 30
export default{
  data () {
    const checkName = (rule, value, callback) => {
      let Name = /^[\u4e00-\u9fa5_a-zA-Z0-9_]{2,10}$/
      if (!Name.test(value)) {
        callback(new Error('用户名需2-10位的中文、英文字母和数字及_'))
      }
      callback()
    }
    const checkPho = (rule, value, callback) => {
      let phone = /^1[3456789]\d{9}$/
      if (!phone.test(value)) {
        callback(new Error('手机号格式不正确'))
      }
      callback()
    }
    const checkPwd = (rule, value, callback) => {
      let pwd = /^[a-z0-9_-]{6,18}$/
      if (!pwd.test(value)) {
        callback(new Error('密码需在6-18位'))
      }
      callback()
    }
    const checknewPwd = (rule, value, callback) => {
      if (this.form.password) {
        if (!value) {
          callback(new Error('请确认您的密码'))
        } else if (!(value === this.form.password)) {
          callback(new Error('两次密码不一致'))
        }
        callback()
      }
    }
    return {
      show: true, // 初始启用按钮
      count: '', // 初始化次数
      timer: null,
      code: '',
      action: {
        register: 'CAT/User/addUser',
        login: 'CAT/User/login',
        login2: 'CAT/User/login2',
        sendCode: 'CAT/Send/sendCode',
        loginByAP: 'CAT/User/loginByAP'
      },
      form: {
        user: '',
        telephone: '',
        password: '',
        newPwd: '',
        gender: '',
        sendCode: ''
      },
      ruleValidate: {
        user: [{required: true, message: '请输入用户名', trigger: 'blur'}, {validator: checkName, trigger: 'blur'}],
        telephone: [{required: true, message: '请输入手机号', trigger: 'blur'}, {validator: checkPho, trigger: 'blur'}],
        password: [{required: true, message: '请输入密码', trigger: 'blur'}, {validator: checkPwd, trigger: 'blur'}],
        newPwd: [{required: true, message: '请输入二次密码', trigger: 'blur'}, {validator: checknewPwd, trigger: 'blur'}],
        gender: [{required: true, message: '请选择性别', trigger: 'change'}]
      },
      modal1: false,
      modal2: false,
      modal3: false,
      name: '',
      select: '',
      i: 0,
      option: [{
        id: 1,
        label: '美式咖啡'
      },
      {
        id: 2,
        label: '浓缩咖啡'
      },
      {
        id: 3,
        label: '碧螺春'
      },
      {
        id: 4,
        label: '阿萨姆红茶'
      },
      {
        id: 5,
        label: '祁门红茶'
      }],
      data: [{
        value: 'Coffee',
        label: '咖啡',
        children: [
          {
            value: 'nongsuo',
            label: '浓缩咖啡'
          },
          {
            value: 'meishi',
            label: '美式咖啡'
          },
          {
            value: 'maqi',
            label: 'Macchiato'
          },
          {
            value: 'natie',
            label: '拿铁咖啡'
          }
        ]
      }, {
        value: 'Tea',
        label: '茶',
        children: [
          {
            value: 'green',
            label: '绿茶'
          },
          {
            value: 'red',
            label: '红茶'
          },
          {
            value: 'white',
            label: '白茶'
          },
          {
            value: 'yellow',
            label: '黄茶'
          }
        ]
      }]
    }
  },
  components: {

  },
  methods: {
    onloading (name) {
      this.$refs[name].validate((vaild) => {
        if (vaild) {
          let params = {
            account: this.form.user,
            phone: this.form.telephone
          }
          Http.xhrPost(this, this.action.loginByAP, params, (res) => {
            this.$Spin.show()
            setTimeout(() => {
              if (res.resultCode === 0) {
                let params = {
                  account: this.form.user,
                  sex: this.form.gender,
                  password: this.form.password,
                  phone: this.form.telephone
                }
                Http.xhrPost(this, this.action.register, params, (res) => {
                  this.$Spin.show()
                  setTimeout(() => {
                    this.$message({
                      type: 'success',
                      message: res.message
                    })
                    this.$Spin.hide()
                    this.modal1 = false
                    this.$refs.formValidate.resetFields()
                  }, 1000)
                })
              }
              if (res.resultCode === -1) {
                this.$message({
                  type: 'success',
                  message: res.message
                })
              }
              this.$Spin.hide()
              this.modal1 = false
              this.$refs.formValidate.resetFields()
            }, 1000)
          })
        } else {
          this.$Spin.show()
          setTimeout(() => {
            this.$Spin.hide()
            this.modal1 = true
            this.$Message.error('注册失败!')
          }, 1000)
        }
      })
    },
    onloading2 (name) {
      this.$refs[name].validate((vaild) => {
        if (vaild) {
          let params = {
            account: this.form.user,
            password: this.form.password
          }
          Http.xhrPost(this, this.action.login, params, (res) => {
            console.log(res)
            sessionStorage.setItem('user_id', res.data.userId)
            sessionStorage.setItem('account', res.data.account)
            this.$Spin.show()
            setTimeout(() => {
              this.$message({
                type: 'success',
                message: res.message
              })
              this.$Spin.hide()
              this.modal2 = false
              this.$refs.formValidate.resetFields()
              this.$router.push('/home')
            }, 1000)
          })
        } else {
          this.$Spin.show()
          setTimeout(() => {
            this.$Spin.hide()
            this.modal2 = true
            this.$Message.error('登录失败!')
          }, 1000)
        }
      })
    },
    onloading3 (name) {
      this.$refs[name].validate((vaild) => {
        let code = sessionStorage.getItem('code')
        if (this.form.sendCode === code) {
          let params = {
            phone: this.form.telephone
          }
          Http.xhrPost(this, this.action.login2, params, (res) => {
            sessionStorage.setItem('user_id', res.data.userId)
            sessionStorage.setItem('account', res.data.account)
            this.$Spin.show()
            setTimeout(() => {
              this.$message({
                type: 'success',
                message: res.message
              })
              this.$Spin.hide()
              this.modal2 = false
              this.$refs.formValidate.resetFields()
              this.$router.push('/home')
            }, 1000)
          })
        } else {
          this.$Spin.show()
          setTimeout(() => {
            this.$Spin.hide()
            this.modal3 = true
            this.$Message.error('验证码不正确!')
          }, 1000)
        }
      })
    },
    sendCode () {
      if (!this.timer) {
        let phone = {
          phone: this.form.telephone
        }
        Http.xhrPost(this, this.action.sendCode, phone, (res) => {
          sessionStorage.setItem('code', res.data)
        })
        this.count = TIME_COUNT
        this.show = false
        this.timer = setInterval(() => {
          if (this.count > 0 && this.count <= TIME_COUNT) {
            this.count--
          } else {
            this.show = true
            clearInterval(this.timer) // 清除定时器
            this.timer = null
          }
        }, 1000)
      }
    },
    clear () {
      this.$refs.formValidate.resetFields()
      this.$refs.formValidate2.resetFields()
      this.$refs.formValidate3.resetFields()
    },
    MessageCode () {
      this.modal2 = false
      this.modal3 = true
    },
    AccountCode () {
      this.modal3 = false
      this.modal2 = true
    },
    tologin () {
      this.modal2 = false
      this.modal1 = true
    },
    tologins () {
      this.modal1 = false
      this.modal2 = true
    }
  }
}
</script>

<style scoped lang="scss">

 .el-container{
   height: 100%;
   background: url(../assets/img/bg.jpg) no-repeat;
   background-size: 100% 100%;
    .head{
      margin: 0px;
      padding: 0px;
      width: 100%;
      background-color: #ff0000;
      .logo{
          height: 100%;
          width: 10%;
          text-align: center;
          vertical-align: middle;
          position: relative;
          left: 8.58%;
          .p1{
              font-family: '华文行楷';
              font-weight: 400;
              font-style: normal;
              font-size: 48px;
              color:#FFFFFF;
              margin-top: 12px;
          }
          .p2{
              font-size: 12px;
              color:#FFFFFF;
              font-family: 'Arial Normal', 'Arial';
              font-weight: 400;
              font-style: normal;
              position: relative;
              top: -10px;
          }
      }
      .entrance{
          height: 100%;
          width: 23.35%;
          margin-right: 100px;
          .entrance-left{
              color:#FFFFFF;
              font-family: 'Arial Normal', 'Arial';
              font-weight: 500;
              font-style: normal;
              width: 14%;
              position:relative;
              top: 36%;
              left: 10%;
              span{
                  font-size: 16px;
              }
          }
          .entrance-right{
              width: 39%;
              position:relative;
              top: 6%;
              left: 37%;
              color: #FFFFFF;
              text-align: center;
              vertical-align: middle;
              i{
                  font-size: 30px;
                  margin-right: 5px;
              }
              span{
                  margin-right: 18px;
                  font-size: 16px;
                  font-family: 'Arial Normal', 'Arial';
                  font-weight: 400;
                  position: relative;
                  top: -2px;
              }
          }
      }
 }
.body{
     margin: 0px;
     padding: 0px;
     .content{
        position: absolute;
        left: 128px;
        top: 165px;
        width: 741px;
        height: 205px;
        overflow: hidden;
        .Slogan{
            color: #FFFFFF;
            position: relative;
            left: 36px;
            .p1{
                font-family: '华文仿宋 Bold', '华文仿宋';
                font-weight: 700;
                font-style: normal;
                font-size: 36px;
            }
            .p2{
                font-family: 'French Script MT Bold', 'French Script MT Normal', 'French Script MT';
                font-weight: 700;
                font-style: normal;
                font-size: 36px;
                margin-left: 25px;
                margin-top: -2px;
            }
        }
        .search{
            width: 705px;
            height: 86px;
            position: absolute;
            right: 0px;
            bottom: 0px;
            .bg{
                width: 100%;
                height: 100%;
                background-color: rgba(51, 51, 51, .7);
                .form{
                    z-index: 1;
                    position: absolute;
                    top: 26%;
                    left: 27px;
                    .input1{
                        width: 500px;
                        margin: 0px;
                        margin-left: 10px;
                        padding: 0px;
                    }
                    .select{
                        margin: 0px;
                        padding: 0px;
                        width: 128px;
                    }
                    .button{
                        margin: 0px;
                        padding: 0px;
                        width: 117px;
                        height: 41px;
                    }
                }
            }
        }
     }
 }
 .tail{
    background-color: rgba(204, 204, 204, .5);
    text-align: center;
    vertical-align: middle;
    span{
        line-height: 40px;
        font-family: 'Arial Normal', 'Arial';
        font-weight: 400;
        font-style: normal;
        font-size: 13px;
        opacity: 0.5;
    }
 }

}
</style>

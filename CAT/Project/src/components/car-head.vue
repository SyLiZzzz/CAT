<template>
<el-container>
    <!-- 头部导航栏-->
  <el-header class="head" height="99px">
      <!-- 左部logo-->
    <div class="logo fl">
        <p class="p1 fl">我的购物车</p>
        <p class="p2 fl">温馨提示：产品是否购买成功，以最终下单为准哦，请尽快结算</p>
    </div>
    <!-- 右部信息-->
    <div class="entrance fr">
        <el-dropdown @command="handleCommand">
              <span class="el-dropdown-link">
                {{userName}}<i class="el-icon-arrow-down el-icon--right"></i>
              </span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item v-for="item in topNavs"
                  :icon="item.icon" :key="item.id" :command="item.id">{{item.name}}</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
        <span style="color: #e4ebf6;">|</span>
        <span style="font-size: 12px;color: #757575;" @click="out"><i class="el-icon-refresh-left"></i>退出登录</span>
        <!-- 修改信息模态框-->
        <Modal v-model='modal1' title="个人信息修改" @on-ok="onloading('formValidate')" @on-cancel="clear">
            <template>
                  <Form ref="formValidate" :model="form" :label-width="80" :rules="ruleValidate">
                          <FormItem label="用户名" prop="account">
                              <i-input type="text" v-model="form.account" placeholder="Username">
                                  <Icon type="ios-person-outline" slot="prepend"></Icon>
                              </i-input>
                          </FormItem>
                          <FormItem label="手机号" prop="phone">
                              <i-input type="text" v-model="form.phone" placeholder="Telephone">
                                  <Icon type="ios-phone-portrait" slot="prepend"></Icon>
                              </i-input>
                          </FormItem>
                          <FormItem label="性别">
                            <RadioGroup v-model="form.sex">
                                <Radio :label="0">男</Radio>
                                <Radio :label="1">女</Radio>
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
            </template>
        </Modal>
        <!-- 订单详情-->
        <Modal v-model='modal2' fullscreen title="订单详情" footer-hide>
            <el-table stripe border :data="tables" style="margin-top: 70px">
                <el-table-column label="订单号" prop="order_id"></el-table-column>
                <el-table-column label="订单信息" prop="drink_name"></el-table-column>
                <el-table-column label="支付价格" prop="price"></el-table-column>
                <el-table-column label="收货地址" prop="addressInfo"></el-table-column>
                <el-table-column label="详细地址" prop="infos"></el-table-column>
                <el-table-column label="状态" >
                    <template slot-scope="scope">
                        <span>{{stateArr[scope.row.state]}}</span>
                    </template>
                </el-table-column>
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <el-button type="danger" @click="deleteOr(scope.row.order_id)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </Modal>
    </div>
  </el-header>
</el-container>
</template>
<script>
import Http from '@/utils/HttpUtil'
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
      action: {
        selectById: 'CAT/User/selectById',
        updateuUser: 'CAT/User/updateuUser',
        selectOrById: 'CAT/Order/selectOrById',
        deleteOrderById: 'CAT/Order/deleteOrderById'
      },
      userName: '',
      active: '1',
      modal1: false,
      modal2: false,
      form: {
        account: '',
        phone: '',
        password: '',
        newPwd: '',
        sex: ''
      },
      ruleValidate: {
        account: [{required: true, message: '请输入用户名', trigger: 'blur'}, {validator: checkName, trigger: 'blur'}],
        phone: [{required: true, message: '请输入手机号', trigger: 'blur'}, {validator: checkPho, trigger: 'blur'}],
        password: [{required: true, message: '请输入密码', trigger: 'blur'}, {validator: checkPwd, trigger: 'blur'}],
        newPwd: [{required: true, message: '请输入二次密码', trigger: 'blur'}, {validator: checknewPwd, trigger: 'blur'}]
      },
      topNavs: [{
        id: 1,
        name: '个人信息',
        icon: 'el-icon-s-custom'
      },
      {
        id: 2,
        name: '地址管理',
        icon: 'el-icon-location-information'
      },
      {
        id: 3,
        name: '我的订单',
        icon: 'el-icon-tickets'
      }],
      tables: [],
      stateArr: ['未通过', '已通过']
    }
  },
  components: {

  },
  methods: {
    handleCommand (command) {
      if (command === 1) {
        let id = {
          userId: sessionStorage.getItem('user_id')
        }
        Http.xhrGet(this, this.action.selectById, id, (res) => {
          this.form = res.data
        })
        this.modal1 = true
      }
      if (command === 2) {
        this.$router.push('/address')
      }
      if (command === 3) {
        let id = {
          userId: sessionStorage.getItem('user_id')
        }
        Http.xhrGet(this, this.action.selectOrById, id, (res) => {
          this.tables = res.data
        })
        this.modal2 = true
      }
    },
    onloading (name) {
      this.$refs[name].validate((vaild) => {
        if (vaild) {
          let params = {
            userId: sessionStorage.getItem('user_id'),
            account: this.form.account,
            sex: this.form.sex,
            password: this.form.password,
            phone: this.form.phone
          }
          Http.xhrPost(this, this.action.updateuUser, params, (res) => {
            this.$Spin.show()
            setTimeout(() => {
              this.$message({
                type: 'success',
                message: res.message
              })
              this.$Spin.hide()
              this.modal1 = false
              this.$refs.formValidate.resetFields()
              sessionStorage.clear()
              this.$router.push('/')
            }, 1000)
          })
        } else {
          this.$Spin.show()
          setTimeout(() => {
            this.$Spin.hide()
            this.modal1 = true
            this.$Message.error('修改信息失败!')
          }, 1000)
        }
      })
    },
    deleteOr (index) {
      let params = {
        orderId: index
      }
      this.$confirm('您正在执行删除操作，是否继续', '是否继续', {
        confirmButtonText: '残忍丢弃',
        cancelButtonText: '容我再想想'
      }).then(() => {
        Http.xhrPost(this, this.action.deleteOrderById, params, (res) => {
          this.$message({
            type: 'success',
            message: res.message
          })
          let id = {
            userId: sessionStorage.getItem('user_id')
          }
          Http.xhrGet(this, this.action.selectOrById, id, (res) => {
            this.tables = res.data
          })
        })
      })
    },
    clear () {
      this.$refs.formValidate.resetFields()
    },
    out () {
      sessionStorage.clear()
      this.$router.push('/')
    }
  },
  mounted () {
    this.userName = sessionStorage.getItem('account')
  }
}
</script>

<style scoped lang="scss">

 .el-container{
   height: 100%;
   background-size: 100% 100%;
    .head{
      margin: 0px;
      padding: 0px;
      width: 100%;
      background-color: #ffffff;
      border-bottom: 2px solid #ff6700;
      .logo{
          text-align: center;
          vertical-align: middle;
          position: relative;
          left: 15%;
          top: 25px;
          .p1{
              font-size: 28px;
              line-height: 48px;
              font-weight: normal;
              color:#424242;
          }
          .p2{
              height: 20px;
              line-height: 20px;
              margin-top: 20px;
              margin-left: 15px;
              color: #757575;
              font-size: 12px;
          }
      }
      .entrance{
        margin-right: 140px;
        margin-top: 40px;
        .el-dropdown{
            margin-right: 20px;
        }
      }
 }

}
</style>

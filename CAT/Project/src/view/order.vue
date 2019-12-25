<template>
  <div>
      <head1></head1>
      <el-main class="main">
          <div class="content">
              <!-- 内容-->
              <el-row class="info">
                  <div class="address">
                      <h3>收货地址</h3><div class="clear"></div>
                      <!-- 地址列表-->
                      <div class="boxs">
                          <div class="box fl" v-for="(item,index) in address" :key="item.addressId" tabindex="-1" :class="isActive ===index ? 'border-red' : 'border-grey'" @focusin="inDiv(index,item.addressId)">
                              <dl>
                                <dt>
                                <span class="tag"></span>
                                    <em class="uname">{{item.name}}</em>
                                </dt>
                                <dd>{{item.phone}}</dd>
                                <dd>
                                {{item.addressInfo}}<br>
                                {{item.infos}}</dd>
                                </dl>
                          </div>
                          <!-- 新增地址-->
                          <div class="box fl box2" @click="modal =true">
                               <div class="box-content">
                                   <p class="p1"><i class="el-icon-circle-plus-outline"></i></p>
                                   <p class="p2">添加新地址</p>
                               </div>
                          </div>
                          <!-- modal-->
                          <Modal v-model="modal" :styles="{top: '20%'}" title="添加收货地址" width="600" class="modal" @on-cancel="clear">
                              <template>
                                    <Form :model="forms" inline class="form" :rules="ruleValidate" ref="formValidate">
                                            <FormItem prop="user">
                                                <i-input type="text"  placeholder="姓名" style="width: 250px" size="large" clearable v-model="forms.user">
                                                </i-input>
                                            </FormItem>
                                            <FormItem prop="telephone">
                                                <i-input type="text"  placeholder="手机号" style="width: 250px" size="large" clearable v-model="forms.telephone">
                                                </i-input>
                                            </FormItem>
                                             <FormItem>
                                                  <v-distpicker @selected="onSelected"></v-distpicker>
                                             </FormItem>
                                             <FormItem prop="infos">
                                                 <i-input type="textarea" :autosize="true" style="width: 515px" placeholder="详细信息" clearable v-model="forms.infos"></i-input>
                                             </FormItem>
                                        </Form>
                              </template>
                              <div slot="footer">
                                  <Button type="primary" size="large" long @click="submit('formValidate')">提交</Button>
                              </div>
                          </Modal>
                      </div>
                      <div class="clear"></div>
                      <div class="drink_title">
                          <span>饮品</span>
                      </div>
                      <div class="drink-content" v-for="item in card" :key="item.id">
                            <img :src="'../../static/upload/'+item.picture">
                            <span class="span1">{{item.name}}</span>
                            <span class="span2">{{item.price}}元</span>
                      </div>
                      <div class="drink-price">
                          <span class="span1"><img src="../assets/img/payOnline_zfb.png" @click="modal2=true"></span>
                          <span class="span1"><img src="../assets/img/weixinpay.png" @click="modal3=true"></span>
                          <span class="price">应付总额:<i><em>{{this.counts}}</em>元</i></span>
                      </div>
                      <Modal v-model="modal2"  class="modal2" width="290" :mask-closable="false" @on-ok="change">
                          <div class="img">
                              <img src="../assets/img/TIM图片20191202213056.jpg">
                          </div>
                      </Modal>
                      <Modal v-model="modal3"  class="modal2" width="290" :mask-closable="false" @on-ok="change">
                          <div class="img">
                              <img src="../assets/img/TIM图片20191202213142.jpg">
                          </div>
                      </Modal>
                  </div>
              </el-row>
              <!-- 底部-->
              <div class="tail">
                  <div class="over fr">
                  <a class="a1 fl" @click="toCar">返回购物车</a>
                  </div>
              </div>
          </div>
      </el-main>
  </div>
</template>
<script>
import head1 from '@/components/order-head'
import VDistpicker from 'v-distpicker'
import Http from '@/utils/HttpUtil'
export default{
  data () {
    const checkName = (rule, value, callback) => {
      let Name = /^[\u4e00-\u9fa5_a-zA-Z0-9]{2,10}$/
      if (!Name.test(value)) {
        callback(new Error('用户名格式不正确'))
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
    return {
      action: {
        selectAllById: 'CAT/Address/selectAllById',
        inserAdd: 'CAT/Address/insertAddress',
        selectById: 'CAT/Car/selctCarById',
        inserToOrder: 'CAT/Order/insertOrder',
        deleteCar: 'CAT/Car/deleteCar'
      },
      addressId: 0,
      province: '',
      city: '',
      area: '',
      list: '',
      modal: false,
      modal2: false,
      modal3: false,
      isActive: -1,
      counts: 0,
      address: [],
      nameList: [],
      newNamelist: [],
      ruleValidate: {
        user: [{required: true, message: '请输入用户名', trigger: 'blur'}, {validator: checkName, trigger: 'blur'}],
        telephone: [{required: true, message: '请输入手机号', trigger: 'blur'}, {validator: checkPho, trigger: 'blur'}],
        infos: [{required: true, message: '请输入详细地址', trigger: 'blur'}]
      },
      forms: {
        user: '',
        telephone: '',
        infos: ''
      },
      card: []
    }
  },
  components: {
    head1, VDistpicker
  },
  methods: {
    inDiv (index, id) {
      this.isActive = index
      this.addressId = id
    },
    onSelected (data) {
      let newProvince = data.province.value
      let newCity = data.city.value
      let newArea = data.area.value
      this.list = newProvince + ',' + newCity + ',' + newArea
      console.log(this.list)
    },
    clear () {
      this.$refs.formValidate.resetFields()
    },
    submit (name) {
      this.$refs[name].validate((vaild) => {
        if (vaild) {
          let params = {
            userId: sessionStorage.getItem('user_id'),
            name: this.forms.user,
            phone: this.forms.telephone,
            addressInfo: this.list,
            infos: this.forms.infos
          }
          Http.xhrPost(this, this.action.inserAdd, params, (res) => {
            this.$Spin.show()
            setTimeout(() => {
              this.$message({
                type: 'success',
                message: res.message
              })
              this.$Spin.hide()
              this.modal = false
              this.$refs.formValidate.resetFields()
              this.getAddressList()
            }, 1000)
          })
        } else {
          this.$Spin.show()
          setTimeout(() => {
            this.$Spin.hide()
            this.modal = true
            this.$Message.error('新增地址失败!')
          }, 1000)
        }
      })
    },
    toCar () {
      this.$router.push('/car')
    },
    getAddressList () {
      let params = {
        userId: sessionStorage.getItem('user_id')
      }
      Http.xhrGet(this, this.action.selectAllById, params, (res) => {
        this.address = res.data
        console.log(res)
      })
    },
    getCarList () {
      let params = {
        userId: sessionStorage.getItem('user_id')
      }
      Http.xhrPost(this, this.action.selectById, params, (res) => {
        console.log(res.data)
        this.card = res.data
        // 重组数据
        this.card.forEach((res) => {
          this.nameList += res.name + ','
        })
        this.newNamelist = (this.nameList.substring(this.nameList.length - 1) === ',') ? this.nameList.substring(0, this.nameList.length - 1) : this.nameList
      })
    },
    change () {
      if (this.isActive === -1) {
        this.$message({
          type: 'error',
          message: '亲~请选择地址哦！'
        })
      } else {
        let params = {
          userId: sessionStorage.getItem('user_id'),
          userName: sessionStorage.getItem('account'),
          addressId: this.addressId,
          drinkName: this.newNamelist,
          price: sessionStorage.getItem('price')
        }
        Http.xhrPost(this, this.action.inserToOrder, params, (res) => {
          this.$Spin.show()
          setTimeout(() => {
            this.$Spin.hide()
            this.$message({
              type: 'success',
              message: res.message
            })
            let id = {
              userId: sessionStorage.getItem('user_id')
            }
            Http.xhrPost(this, this.action.deleteCar, id, (res) => {
              this.$router.push('/home')
            })
          }, 1500)
        })
      }
    },
    checkLogin () {
      let userId = sessionStorage.getItem('user_id')
      let account = sessionStorage.getItem('account')
      if (userId === null && account === null) {
        this.$router.push('/')
        this.$message({
          type: 'error',
          message: '亲，请先登录哦'
        })
      }
    }
  },
  mounted () {
    this.checkLogin()
    this.getAddressList()
    this.getCarList()
    this.counts = sessionStorage.getItem('price')
  }
}
</script>

<style scoped lang="scss">
    .main{
        background-color: #f5f5f5;
        width: 100%;
        height: 100%;
        margin: 0px;
        padding: 0px;
        overflow: hidden;
        .content{
            width: 79%;
            margin: auto auto;
            margin-top: 40px;
            background-color: #f5f5f5;
            .info{
               width: 100%;
               background-color: #FFFFFF;
               .address{
                   margin-left: 48px;
                   margin-right: 20px;
                   h3{
                       float: left;
                       color:#333;
                       font-size: 18px;
                       line-height: 20px;
                       font-weight: normal;
                       margin-top: 50px;
                   }
                   .boxs{
                       width: 100%;
                       margin-top: 20px;
                       .box{
                          width: 268px;
                          height: 178px;
                          cursor: pointer;
                          border: 1px solid #e4e4e4;
                          margin-right: 15px;
                          margin-bottom: 10px;
                          dl{
                              padding: 15px 24px 0;
                              margin: 0;
                              dt{
                                  margin: 0 0 10px;
                                  line-height: 30px;
                                  .tag{
                                      float: right;
                                      color: #b0b0b0;
                                  }
                                  .uname{
                                      font-size: 18px;
                                      color:#333;
                                      font-style: normal;
                                  }
                              }
                              dd{
                                  margin: 0;
                                  line-height: 22px;
                                  color: #757575;
                              }
                          }

                       }
                       .box2{
                           &:hover{
                               border: 1px solid #000000;
                               transition: 1s;
                               .p1{
                                   color: #000000;
                                   transition: 1s;
                               }
                               .p2{
                                   color: #000000;
                                   transition: 1s;
                               }
                           }
                           .box-content{
                               text-align: center;
                               color: #b0b0b0;
                               margin-top: 63px;
                               .p1{
                                   i{
                                       font-size: 30px;
                                   }
                               }
                             }
                       }
                       .border-red {
                         border: solid 1px #ff0000
                       }
                       .border-grey {
                         border:  solid 1px #cccccc
                       }
                   }
                   .drink_title{
                     width: 100%;
                     border-bottom: 1px solid #e0e0e0;
                     margin-top: 15px;
                     padding-bottom: 10px;
                     span{
                         color:#333;
                         font-size: 18px;
                         font-weight: normal;
                     }
                   }
                   .drink-content{
                     width: 100%;
                     border-bottom: 1px solid #e0e0e0;
                     margin-top: 20px;
                     padding-bottom: 10px;
                     img{
                         width: 40px;
                         height: 40px;
                         margin-left: 25px;
                     }
                     .span1{
                         position: relative;
                         top: -13px;
                         left: 40px;
                         color:#424242;
                     }
                     .span2{
                         color: #ff6700;
                         position: relative;
                         top: 12px;
                         float: right;
                         right: 120px;
                     }
                   }
                   .drink-price{
                       width: 100%;
                       margin-top: 30px;
                       padding-bottom: 30px;
                       .span1{
                           cursor: pointer;
                           margin-right: 20px;
                       }
                       .price{
                           color: #757575;
                           position: relative;
                           left: 545px;
                           top: -15px;
                           i{
                               font-style: normal;
                               color: #ff6700;
                               em{
                                   font-style: normal;
                                   font-size: 30px;
                               }
                           }
                       }
                   }
               }
            }
            .tail{
                width: 100%;
                height: 80px;
                background-color: #FFFFFF;
                margin-top: 2px;
                .over{
                    margin-top: 23px;
                    margin-right: 40px;
                    .a1{
                        width: 158px;
                        height: 38px;
                        line-height: 38px;
                        font-size: 14px;
                        margin-left: 20px;
                        vertical-align: top;
                        background:#ff6700;
                        border-color:#ff6700;
                        color:#fff;
                        text-align: center;
                    }
                    .a2{
                        width: 158px;
                        height: 38px;
                        line-height: 38px;
                        font-size: 14px;
                        margin-left: 20px;
                        vertical-align: top;
                        background:#ff6700;
                        border-color:#ff6700;
                        color:#fff;
                        text-align: center;
                    }
                }

            }
        }
    }
    .modal{
        .form{
            margin-top: 20px;
            margin-left: 20px;

        }
    }
    .modal2{
        .img{
            width: 220px;
            height: 200px;
            margin: auto auto;
            img{
                width: 100%;
                height: 100%;
            }
        }
    }
</style>

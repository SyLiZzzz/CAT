<template>
  <div>
      <head1></head1>
      <el-main class="main">
          <div class="content">
              <!-- 内容-->
              <el-row class="info">
                  <div class="address">
                      <!-- 地址列表-->
                      <div class="boxs">
                          <div class="box fl" v-for="(item,index) in address" :key="item.address_id" tabindex="-1" :class="isActive ===index ? 'border-red' : 'border-grey'" @focusin="inDiv(index)">
                              <div class="icon" @click="deleteAddr(item.addressId)"><i class="el-icon-close"></i></div>
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
                  </div>
              </el-row>
          </div>
      </el-main>
  </div>
</template>
<script>
import head1 from '@/components/address-head'
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
        deleteById: 'CAT/Address/deleteAddById'
      },
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
      ruleValidate: {
        user: [{required: true, message: '请输入用户名', trigger: 'blur'}, {validator: checkName, trigger: 'blur'}],
        telephone: [{required: true, message: '请输入手机号', trigger: 'blur'}, {validator: checkPho, trigger: 'blur'}],
        infos: [{required: true, message: '请输入详细地址', trigger: 'blur'}]
      },
      forms: {
        user: '',
        telephone: '',
        infos: ''
      }
    }
  },
  components: {
    head1, VDistpicker
  },
  methods: {
    inDiv (index) {
      this.isActive = index
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
    deleteAddr (id) {
      let params = {
        addressId: id
      }
      this.$confirm('您正在执行删除操作，是否继续', '是否继续', {
        confirmButtonText: '残忍丢弃',
        cancelButtonText: '容我再想想'
      }).then(() => {
        Http.xhrPost(this, this.action.deleteById, params, (res) => {
          this.$message({
            type: 'success',
            message: res.message
          })
          this.getAddressList()
        })
      })
    },
    getAddressList () {
      let params = {
        userId: sessionStorage.getItem('user_id')
      }
      Http.xhrGet(this, this.action.selectAllById, params, (res) => {
        this.address = res.data
      })
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
  }
}
</script>

<style scoped lang="scss">
    .main{
        background-color: #f5f5f5;
        width: 100%;
        height: 625px;
        margin: 0px;
        padding: 0px;
        overflow: hidden;
        .content{
            width: 79%;
            margin: auto auto;
            margin-top: 40px;
            background-color: #f5f5f5;
            height: 100%;
            .info{
               width: 100%;
               background-color: #FFFFFF;
               height: 100%;
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
                          .icon{
                              position: relative;
                              top: 0px;
                              right: 5px;
                              float: right;
                          }
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

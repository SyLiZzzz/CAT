<template>
  <div class="userManager-box">
    <el-row class="btn-group" type="flex" justify="center">
      <el-col :offset="8" :span="9"><el-input v-model="name" prefix-icon="el-icon-search" placeholder="请输入搜索的用户"></el-input><el-button type="primary" @click="reachUser">搜索</el-button></el-col>
      <el-col :offset="3" :span="2"><el-button type="primary" @click="modal1 =true">新增</el-button></el-col>
      <el-col :span="2"><el-button type="danger" @click="bathdeleteUser">批量删除</el-button></el-col>
    </el-row>
    <el-row class="userManager-content">
      <el-table :data="user" @selection-change="getIds" stripe>
        <el-table-column type="selection" label="全选"></el-table-column>
        <el-table-column label="id" width="40px" prop="addressId"></el-table-column>
        <el-table-column label="用户id"  prop="userId"></el-table-column>
        <el-table-column label="用户名" prop="name"></el-table-column>
        <el-table-column label="手机号码" prop="phone"></el-table-column>
        <el-table-column label="收货地址" prop="addressInfo"></el-table-column>
        <el-table-column label="详细地址" prop="infos"></el-table-column>
        <el-table-column label="创建时间" width="180">
          <template slot-scope="scope">
            {{format(scope.row.createTime)}}
          </template>
        </el-table-column>
        <el-table-column label="是否删除" prop="isDeleted" width="200">
          <template slot-scope="scope">
            <el-switch v-model="user[scope.$index].isDeleted" active-text="未删除" inactive-text="已删除" :active-value="0" :inactive-value="1" @click.native="tiggerState(scope.row.addressId,scope.row.isDeleted)"></el-switch>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="text" @click="update(scope.row.addressId)">修改</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-row>
    <el-row type="flex" justify="center">
      <el-pagination
            @size-change="pageSizeChange"
            @current-change="currPageChange"
            :current-page="curr_page"
            :page-sizes="[10, 50, 100, 500, 1000]"
            :page-size="page_size"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total">
          </el-pagination>
    </el-row>
        <Modal v-model='modal1' title="地址新增" @on-ok="addUsers('addUser')" @on-cancel="clear">
            <template>
                  <Form ref="addUser" :model="params" :label-width="80" :rules="rules">
                          <FormItem label="用户id" prop="userId">
                              <i-input type="text" v-model="params.userId" placeholder="userId" clearable>
                                  <Icon type="ios-person-outline" slot="prepend"></Icon>
                              </i-input>
                          </FormItem>
                          <FormItem label="用户名" prop="name">
                              <i-input type="text" v-model="params.name" placeholder="name" clearable>
                                  <Icon type="ios-person-outline" slot="prepend"></Icon>
                              </i-input>
                          </FormItem>
                          <FormItem label="手机号" prop="phone">
                              <i-input type="text" v-model="params.phone" placeholder="phone" clearable>
                                  <Icon type="ios-phone-portrait" slot="prepend"></Icon>
                              </i-input>
                          </FormItem>
                          <FormItem label="收货地址" prop="address">
                               <v-distpicker @selected="onSelected"></v-distpicker>
                          </FormItem>
                          <FormItem label="详情" prop="info">
                              <i-input type="textarea" :autosize="true" placeholder="详细信息" clearable v-model="params.infos"></i-input>
                          </FormItem>
                      </Form>
            </template>
        </Modal>
        <Modal v-model='modal2' title="地址" @on-ok="updateUsers('addUser')" @on-cancel="clear">
            <template>
                  <Form ref="addUser" :model="params" :label-width="80" :rules="rules">
                          <FormItem label="用户id" prop="userId">
                              <i-input type="text" v-model="params.userId" placeholder="userId" clearable>
                                  <Icon type="ios-person-outline" slot="prepend"></Icon>
                              </i-input>
                          </FormItem>
                          <FormItem label="用户名" prop="name">
                              <i-input type="text" v-model="params.name" placeholder="name" clearable>
                                  <Icon type="ios-person-outline" slot="prepend"></Icon>
                              </i-input>
                          </FormItem>
                          <FormItem label="手机号" prop="phone">
                              <i-input type="text" v-model="params.phone" placeholder="phone" clearable>
                                  <Icon type="ios-phone-portrait" slot="prepend"></Icon>
                              </i-input>
                          </FormItem>
                          <FormItem label="收货地址" prop="address">
                               <v-distpicker @selected="onSelected"></v-distpicker>
                          </FormItem>
                          <FormItem label="详情" prop="info">
                              <i-input type="textarea" :autosize="true" placeholder="详细信息" clearable v-model="params.infos"></i-input>
                          </FormItem>
                      </Form>
            </template>
        </Modal>
  </div>
</template>
<script>
import http from '@/utils/HttpUtil'
import dateUtil from '@/utils/DateUtil'
import VDistpicker from 'v-distpicker'
export default {
  data () {
    const checkName = (rule, value, callback) => {
      let Name = /^[\u4e00-\u9fa5_a-zA-Z0-9_]{2,10}$/
      if (!Name.test(value)) {
        callback(new Error('用户名需2-10位的中文、英文字母和数字及_'))
      }
      callback()
    }
    let checkPhone = (rule, value, callback) => {
      let phoneReg = /^1[3456789]\d{9}$/
      if (!phoneReg.test(value)) {
        return callback(new Error('手机号格式不正确!'))
      }
      callback()
    }
    return {
      total: 0,
      page_size: 10,
      curr_page: 1,
      modal1: false,
      modal2: false,
      name: '',
      user: [],
      list: '',
      ids: '',
      userId: '',
      params: {
        addressId: '',
        userId: '',
        name: '',
        phone: '',
        infos: ''
      },
      action: {
        AdminSelectAll: 'CAT/Address/AdminSelectAll',
        AdminAddAddress: 'CAT/Address/AdminAddAddress',
        AdminSelectAddressById: 'CAT/Address/AdminSelectAddressById',
        AdminUpdateAddress: 'CAT/Address/AdminUpdateAddress',
        AdminDeleteAddress: 'CAT/Address/AdminDeleteAddress',
        deleteAddress: 'CAT/Address/deleteAddress'
      },
      rules: {
        userId: [{required: true, message: '请输入用户id', trigger: 'blur'}],
        name: [{required: true, message: '请输入用户名', trigger: 'blur'}, {validator: checkName, trigger: 'blur'}],
        phone: [{required: true, message: '手机号不能为空', trigger: 'blur'}, {validator: checkPhone, trigger: 'blur'}]
      }
    }
  },
  components: {
    VDistpicker
  },
  computed: {

  },
  methods: {
    // 时间格式转化
    format (date) {
      // 时间格式
      return dateUtil.formatDate(date, 'yyyy-MM-dd HH:mm:ss')
    },
    onSelected (data) {
      let newProvince = data.province.value
      let newCity = data.city.value
      let newArea = data.area.value
      this.list = newProvince + ',' + newCity + ',' + newArea
      console.log(this.list)
    },
    // 修改状态
    tiggerState (id, isDeleted) {
      let params = {
        addressId: id,
        isDeleted: isDeleted
      }
      console.log(params)
      http.xhrPost(this, this.action.AdminDeleteAddress, params, (res) => {
        this.$message({
          type: 'success',
          message: res.message
        })
      })
    },
    reachUser () {
      this.getUserList(this.name, 1, this.pageSize)
    },
    pageSizeChange (pageSize) {
      this.getUserList(this.name, 1, pageSize)
    },
    currPageChange (currPage) {
      this.getUserList(this.name, currPage, this.page_size)
    },
    getUserList (name = null, curr_page = 1, page_size = 10) {
      const params = {
        'curr_page': curr_page,
        'page_size': page_size
      }
      if (name !== null && name !== '') {
        params['name'] = name
      }
      http.xhrGet(this, this.action.AdminSelectAll, params, (res) => {
        console.log(res)
        // 总条数，当前页，当前页最大条数，数据
        this.total = res.data.total
        this.curr_page = res.data.pageNum
        this.page_size = res.data.pageSize
        this.user = res.data.list
      })
    },
    addUsers (refName) {
      this.$refs[refName].validate((valid) => {
        if (valid) {
          let params = {
            userId: this.params.userId,
            name: this.params.name,
            phone: this.params.phone,
            addressInfo: this.list,
            infos: this.params.infos
          }
          http.xhrPost(this, this.action.AdminAddAddress, params, (res) => {
            this.$Spin.show()
            setTimeout(() => {
              this.$message({
                type: 'success',
                message: res.message
              })
              this.$Spin.hide()
              this.modal1 = false
              this.$refs.formValidate.resetFields()
              this.getUserList(this.name, this.curr_page, this.page_size)
            }, 1000)
          })
        } else {
          this.$Spin.show()
          setTimeout(() => {
            this.$Spin.hide()
            this.modal1 = true
            this.$Message.error('新增失败!')
          }, 1000)
        }
      })
    },
    getIds (user) {
      // 清楚上次选中的id
      this.ids = ''
      user.forEach((item, index) => {
        this.ids += item.addressId + ','
      })
    },
    bathdeleteUser () {
      if (this.ids !== '') {
        this.$confirm('您正在执行批量删除操作', '提示', {
          confirmButtonText: '残忍丢弃',
          cancelButtonText: '容我想想',
          type: 'warning'
        }).then(() => {
          let Ids = this.ids.substring(0, this.ids.length - 1)
          this.deleted(Ids)
        })
      } else {
        this.$message({
          type: 'error',
          message: '请至少勾选一条数据'
        })
      }
    },
    deleted (Ids) {
      http.xhrPost(this, this.action.deleteAddress + '/' + Ids, null, (res) => {
        this.$message({
          type: 'success',
          message: res.message
        })
        this.getUserList(this.name, this.curr_page, this.page_size)
      }, 'delete')
    },
    update (id) {
      let addressId = {
        addressId: id
      }
      http.xhrGet(this, this.action.AdminSelectAddressById, addressId, (res) => {
        this.params = res.data
        this.modal2 = true
      })
    },
    updateUsers (refName) {
      this.$refs[refName].validate((vaild) => {
        if (vaild) {
          let params = {
            addressId: this.params.addressId,
            userId: this.params.userId,
            name: this.params.name,
            phone: this.params.phone,
            addressInfo: this.list,
            infos: this.params.infos
          }
          http.xhrPost(this, this.action.AdminUpdateAddress, params, (res) => {
            this.$Spin.show()
            setTimeout(() => {
              this.$message({
                type: 'success',
                message: res.message
              })
              this.$Spin.hide()
              this.getUserList(this.name, this.curr_page, this.page_size)
              this.modal2 = false
              this.$refs.formValidate.resetFields()
            }, 1000)
          })
        } else {
          this.$Spin.show()
          setTimeout(() => {
            this.$Spin.hide()
            this.modal2 = true
            this.$Message.error('修改信息失败!')
          }, 1000)
        }
      })
    },
    clear () {
      this.$refs.addUser.resetFields()
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
    this.getUserList()
  }
}
</script>

<style lang="scss">
@import '../../assets/style/common.scss';
  .userManager-box {
    margin: 0px;
    padding: 0px;
    .btn-group {
      .el-input {
        width: 250px;
      }
    }
    .userManager-content {
      margin-top: 20px;
    }
    .addForm {

    }
  }
</style>

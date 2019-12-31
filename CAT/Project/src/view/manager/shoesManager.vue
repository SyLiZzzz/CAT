<template>
  <div class="userManager-box">
    <el-row class="btn-group" type="flex" justify="center">
      <el-col :offset="8" :span="9"><el-input v-model="name" prefix-icon="el-icon-search" placeholder="请输入搜索的饮品"></el-input><el-button type="primary" @click="reachUser">搜索</el-button></el-col>
      <el-col :offset="3" :span="2"><el-button type="primary" @click="modal1 =true">新增</el-button></el-col>
      <el-col :span="2"><el-button type="danger" @click="bathdeleteUser">批量删除</el-button></el-col>
    </el-row>
    <el-row class="userManager-content">
      <el-table :data="drinks" @selection-change="getIds" stripe>
        <el-table-column type="selection" label="全选"></el-table-column>
        <el-table-column label="id" width="40px" prop="drinkId"></el-table-column>
        <el-table-column label="饮品名" prop="drinkName"></el-table-column>
        <el-table-column label="图片">
                <template slot-scope="scope"><img :src="'../../static/upload/'+scope.row.drinkPicture" style="width: 100px; height: 100px;"></template>
        </el-table-column>
        <el-table-column label="价格" prop="price" width="50"></el-table-column>
        <el-table-column label="描述" prop="explain" width="200"></el-table-column>
        <el-table-column label="分类" width="50">
            <template slot-scope="scope">
              <span>{{category[scope.row.parentId]}}</span>
            </template>
        </el-table-column>
        <el-table-column label="评分" prop="score" width="50"></el-table-column>
        <el-table-column label="创建时间" width="180">
          <template slot-scope="scope">
            {{format(scope.row.createTimesd)}}
          </template>
        </el-table-column>
        <el-table-column label="是否删除" prop="isDeleted" width="200">
          <template slot-scope="scope">
            <el-switch v-model="drinks[scope.$index].isDeleted" active-text="未删除" inactive-text="已删除" :active-value="0" :inactive-value="1" @click.native="tiggerState(scope.row.drinkId,scope.row.isDeleted)"></el-switch>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="text" @click="update(scope.row.drinkId)">修改</el-button>
            <el-upload
              action="/CAT/Drink/upload"
              :data="{drinkId: scope.row.drinkId}"
              :before-remove="beforeRemove"
              :on-success="success"
              :on-exceed ="exceed"
              :limit="1">
              <el-button size="small" type="primary">上传图片</el-button>
            </el-upload>
            <el-button type="text" @click="score(scope.row.drinkId)">评分</el-button>
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
        <Modal v-model='modal1' title="新增饮品" @on-ok="addUsers('addUser')" @on-cancel="clear">
            <template>
                  <Form ref="addUser" :model="params" :label-width="80" :rules="rules">
                          <FormItem label="饮品名" prop="drinkName">
                              <i-input type="text" v-model="params.drinkName" placeholder="请输入饮品名" clearable>
                                  <Icon type="ios-wine" slot="prepend"></Icon>
                              </i-input>
                          </FormItem>
                          <FormItem label="价格" prop="price">
                            <i-input type="text" v-model="params.price" placeholder="请输入价格"  clearable>
                                <Icon type="logo-usd" slot="prepend"></Icon>
                            </i-input>
                           </FormItem>
                          <FormItem label="描述" prop="explain" >
                              <i-input type="textarea" v-model="params.explain" placeholder="请输入描述" clearable>
                              </i-input>
                          </FormItem>
                          <FormItem label="分类" prop="parentId">
                            <RadioGroup v-model="params.parentId">
                                <Radio label="1">咖啡</Radio>
                                <Radio label="2">茶</Radio>
                            </RadioGroup>
                           </FormItem>
                      </Form>
            </template>
        </Modal>
        <Modal v-model='modal2' title="修改饮品" @on-ok="updateDrink('addUser')" @on-cancel="clear">
            <template>
                  <Form ref="addUser" :model="params" :label-width="80" :rules="rules">
                          <FormItem label="饮品名" prop="drinkName">
                              <i-input type="text" v-model="params.drinkName" placeholder="请输入饮品名" clearable>
                                  <Icon type="ios-wine" slot="prepend"></Icon>
                              </i-input>
                          </FormItem>
                          <FormItem label="上传图片:">
                               <el-upload
                                 action="/CAT/Drink/upload"
                                 :data="{drinkId: params.drinkId}"
                                 :limit="1">
                                 <el-button size="small" type="primary">上传图片</el-button>
                               </el-upload>
                               </FormItem>
                          <FormItem label="价格" prop="price">
                            <i-input type="text" v-model="params.price" placeholder="请输入价格"  clearable>
                                <Icon type="logo-usd" slot="prepend"></Icon>
                            </i-input>
                           </FormItem>
                          <FormItem label="描述" prop="explain" >
                              <i-input type="textarea" v-model="params.explain" placeholder="请输入描述" clearable>
                                  <Icon type="ios-lock-outline" slot="prepend"></Icon>
                              </i-input>
                          </FormItem>
                          <FormItem label="分类" prop="parentId">
                            <RadioGroup v-model="params.parentId">
                                <Radio label="1">咖啡</Radio>
                                <Radio label="2">茶</Radio>
                            </RadioGroup>
                           </FormItem>
                      </Form>
            </template>
        </Modal>
        <Modal v-model='modal3' title="请为该饮品评分" @on-ok="subScore">
            <RadioGroup v-model="scoreGroup" style="margin-left: 20%;">
                <Radio label="1">1星</Radio>
                <Radio label="2">2星</Radio>
                <Radio label="3">3星</Radio>
                <Radio label="4">4星</Radio>
                <Radio label="5">5星</Radio>
            </RadioGroup>
        </Modal>
  </div>
</template>
<script>
import http from '@/utils/HttpUtil'
import dateUtil from '@/utils/DateUtil'
export default {
  data () {
    const checkName = (rule, value, callback) => {
      let Name = /^[\u4e00-\u9fa5_a-zA-Z0-9_]{2,10}$/
      if (!Name.test(value)) {
        callback(new Error('饮品名需2-10位的中文、英文字母和数字及_'))
      }
      callback()
    }
    return {
      category: ['', '咖啡', '茶'],
      total: 0,
      page_size: 10,
      curr_page: 1,
      modal1: false,
      modal2: false,
      modal3: false,
      name: '',
      drinks: [],
      ids: '',
      drinId: '',
      params: {
        drinkId: '',
        drinkName: '',
        drinkPicture: '',
        price: '',
        explain: '',
        parentId: ''
      },
      scoreGroup: 0,
      action: {
        selectDrink: 'CAT/Drink/AdminSelectDrink',
        AdminAddDrink: 'CAT/Drink/AdminAddDrink',
        AdminSelectById: 'CAT/Drink/AdminSelectById',
        AdminDeleteDrink: 'CAT/Drink/AdminDeleteDrink',
        deleteDrink: 'CAT/Drink/deleteDrink',
        ChangeScore: 'CAT/Drink/ChangeScore',
        AdminUpdateDrink: 'CAT/Drink/AdminUpdateDrink'
      },
      rules: {
        drinkName: [{required: true, message: '请输入饮品名', trigger: 'blur'}, {validator: checkName, trigger: 'blur'}],
        price: [{required: true, message: '价格不能为空', trigger: 'blur'}],
        explain: [{required: true, message: '描述不能为空', trigger: 'blur'}],
        parentId: [{required: true, message: '请选择', trigger: 'blur'}],
        score: [{required: true, message: '请选择', trigger: 'blur'}]
      }
    }
  },
  components: {

  },
  computed: {

  },
  methods: {
    // 时间格式转化
    format (date) {
      // 时间格式
      return dateUtil.formatDate(date, 'yyyy-MM-dd HH:mm:ss')
    },
    // 修改用户状态
    tiggerState (id, isDeleted) {
      let params = {
        drinkId: id,
        isDeleted: isDeleted
      }
      http.xhrPost(this, this.action.AdminDeleteDrink, params, (res) => {
        this.$message({
          type: 'success',
          message: res.message
        })
      })
    },
    reachUser () {
      this.getDrinkList(this.name, 1, this.pageSize)
    },
    pageSizeChange (pageSize) {
      this.getDrinkList(this.name, 1, pageSize)
    },
    currPageChange (currPage) {
      this.getDrinkList(this.name, currPage, this.page_size)
    },
    getDrinkList (name = null, curr_page = 1, page_size = 10) {
      const params = {
        'curr_page': curr_page,
        'page_size': page_size
      }
      if (name !== null && name !== '') {
        params['drink_name'] = name
      }
      http.xhrGet(this, this.action.selectDrink, params, (res) => {
        // 总条数，当前页，当前页最大条数，数据
        this.total = res.data.total
        this.curr_page = res.data.pageNum
        this.page_size = res.data.pageSize
        this.drinks = res.data.list
        console.log(this.drinks)
      })
    },
    addUsers (refName) {
      this.$refs[refName].validate((valid) => {
        if (valid) {
          let params = {
            drinkName: this.params.drinkName,
            price: this.params.price,
            explain: this.params.explain,
            parentId: this.params.parentId
          }
          console.log(params)
          http.xhrPost(this, this.action.AdminAddDrink, params, (res) => {
            this.$Spin.show()
            setTimeout(() => {
              this.$message({
                type: 'success',
                message: res.message
              })
              this.$Spin.hide()
              this.modal1 = false
              this.$refs.addUser.resetFields()
              this.getDrinkList(this.name, this.curr_page, this.page_size)
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
        this.ids += item.drinkId + ','
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
      http.xhrPost(this, this.action.deleteDrink + '/' + Ids, null, (res) => {
        this.$message({
          type: 'success',
          message: res.message
        })
        this.getDrinkList(this.name, this.curr_page, this.page_size)
      }, 'delete')
    },
    update (id) {
      let drinkId = {
        drinkId: id
      }
      http.xhrGet(this, this.action.AdminSelectById, drinkId, (res) => {
        this.params = res.data
        this.modal2 = true
        console.log(this.params)
      })
    },
    updateDrink (refName) {
      this.$refs[refName].validate((vaild) => {
        if (vaild) {
          let params = {
            drinkId: this.params.drinkId,
            drinkName: this.params.drinkName,
            price: this.params.price,
            explain: this.params.explain,
            parentId: this.params.parentId
          }
          http.xhrPost(this, this.action.AdminUpdateDrink, params, (res) => {
            this.$Spin.show()
            setTimeout(() => {
              this.$message({
                type: 'success',
                message: res.message
              })
              this.$Spin.hide()
              this.getDrinkList(this.name, this.curr_page, this.page_size)
              this.modal2 = false
              this.$refs.addUser.resetFields()
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
    score (id) {
      this.modal3 = true
      this.drinId = id
    },
    subScore () {
      if (this.scoreGroup === '') {
        this.$Message.error('请填写分数!')
      } else {
        let params = {
          commentId: this.drinId,
          score: this.scoreGroup
        }
        console.log(params)
        http.xhrPost(this, this.action.ChangeScore, params, (res) => {
          console.log(params)
          this.$Spin.show()
          setTimeout(() => {
            this.$message({
              type: 'success',
              message: res.message
            })
            this.$Spin.hide()
            this.getDrinkList(this.name, this.curr_page, this.page_size)
            this.modal3 = false
          }, 1000)
        })
      }
    },
    beforeRemove (file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`)
    },
    success (response, file, fileList) {
      this.getDrinkList(this.name, this.curr_page, this.page_size)
      return this.$message({
        type: 'success',
        message: '上传成功'
      })
    },
    exceed (files, fileList) {
      return this.$message({
        type: 'error',
        message: '只能上传一个图片哦~~'
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
    this.getDrinkList()
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

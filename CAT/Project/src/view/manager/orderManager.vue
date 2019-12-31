<template>
  <div class="userManager-box">
    <el-row class="btn-group" type="flex" justify="center">
      <el-col :offset="8" :span="9"><el-input v-model="name" prefix-icon="el-icon-search" placeholder="请输入搜索的用户"></el-input><el-button type="primary" @click="reachUser">搜索</el-button></el-col>
      <el-col :span="2"><el-button type="primary" @click="bathChange">批量通过</el-button></el-col>
      <el-col :span="2"><el-button type="danger" @click="bathdeleteUser">批量删除</el-button></el-col>
    </el-row>
    <el-row class="userManager-content">
      <el-table :data="user" @selection-change="getIds" stripe>
        <el-table-column type="selection" label="全选"></el-table-column>
        <el-table-column label="id"  prop="order_id" width="40"></el-table-column>
        <el-table-column label="用户id"  prop="user_id" width="65"></el-table-column>
        <el-table-column label="地址id" prop="address_id" width="65"></el-table-column>
        <el-table-column label="用户名" prop="user_name" width="65"></el-table-column>
        <el-table-column label="饮品名" prop="drink_name"></el-table-column>
        <el-table-column label="价格" prop="price" width="65"></el-table-column>
        <el-table-column label="收货地址" prop="addressInfo"></el-table-column>
        <el-table-column label="详细地址" prop="infos"></el-table-column>
        <el-table-column label="创建时间" width="180">
          <template slot-scope="scope">
            {{format(scope.row.create_time)}}
          </template>
        </el-table-column>
        <el-table-column label="状态" prop="state" width="180">
            <template slot-scope="scope">
              <el-switch v-model="user[scope.$index].state" active-text="已通过" inactive-text="未通过" :active-value="1" :inactive-value="0" @click.native="tiggerState(scope.row.order_id,scope.row.state)"></el-switch>
            </template>
        </el-table-column>
        <el-table-column label="操作" width="170">
          <template slot-scope="scope">
            <el-switch v-model="user[scope.$index].is_deleted" active-text="已删除" inactive-text="未删除" :active-value="1" :inactive-value="0" @click.native="tiggerState2(scope.row.order_id,scope.row.is_deleted)"></el-switch>
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
  </div>
</template>
<script>
import http from '@/utils/HttpUtil'
import dateUtil from '@/utils/DateUtil'
export default {
  data () {
    return {
      total: 0,
      page_size: 10,
      curr_page: 1,
      modal1: false,
      modal2: false,
      name: '',
      user: [],
      ids: '',
      userId: '',
      params: {
        account: '',
        password: '',
        sex: '',
        phone: '',
        userRoleId: ''
      },
      action: {
        AdminSelectAll: 'CAT/Order/AdminSelectAll',
        AdminChangeState: 'CAT/Order/AdminChangeState',
        AdminChangDeleted: 'CAT/Order/AdminChangDeleted',
        AdmindeleteAll: 'CAT/Order/AdmindeleteAll',
        ChangeAllState: 'CAT/Order/ChangeAllState'
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
    tiggerState (id, state) {
      let params = {
        order_id: id,
        state: state
      }
      http.xhrPost(this, this.action.AdminChangeState, params, (res) => {
        this.$message({
          type: 'success',
          message: res.message
        })
      })
    },
    // 修改用户是否删除
    tiggerState2 (id, isdeleted) {
      let params = {
        order_id: id,
        is_deleted: isdeleted
      }
      http.xhrPost(this, this.action.AdminChangDeleted, params, (res) => {
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
        params['user_name'] = name
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
    getIds (user) {
      // 清楚上次选中的id
      this.ids = ''
      user.forEach((item, index) => {
        this.ids += item.order_id + ','
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
      http.xhrPost(this, this.action.AdmindeleteAll + '/' + Ids, null, (res) => {
        this.$message({
          type: 'success',
          message: res.message
        })
        this.getUserList(this.name, this.curr_page, this.page_size)
      }, 'delete')
    },
    bathChange () {
      if (this.ids !== '') {
        this.$confirm('您正在执行批量改变状态', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '容我想想',
          type: 'warning'
        }).then(() => {
          let Ids = this.ids.substring(0, this.ids.length - 1)
          this.change(Ids)
        })
      } else {
        this.$message({
          type: 'error',
          message: '请至少勾选一条数据'
        })
      }
    },
    change (Ids) {
      http.xhrPost(this, this.action.ChangeAllState + '/' + Ids, null, (res) => {
        this.$message({
          type: 'success',
          message: res.message
        })
        this.getUserList(this.name, this.curr_page, this.page_size)
      }, 'delete')
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

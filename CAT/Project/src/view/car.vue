<template>
  <div>
      <head1></head1>
      <el-main class="main">
          <div class="content">
              <el-table :data="data" style="width: 100%" class="table" @selection-change="change">
                  <el-table-column type="selection" width="90px"></el-table-column>
                  <el-table-column width="150px">
                      <template slot-scope="scope"><img :src="'../../static/upload/'+scope.row.picture" class="img"></template>
                  </el-table-column>
                  <el-table-column label="饮品名称" width="400px">
                      <template slot-scope="scope">
                          <span class="name">{{scope.row.name}}</span>
                      </template>
                  </el-table-column>
                  <el-table-column label="单价"  width="70px">
                      <template slot-scope="scope">
                          <span class="price">{{scope.row.price}}元</span>
                      </template>
                  </el-table-column>
                  <el-table-column label="数量" align="center" width="280px">
                      <template slot-scope="scope">
                      <el-input-number v-model="scope.row.num"  :min="1" :max="10" disabled  size="small" ></el-input-number>
                      </template>
                  </el-table-column>
                  <el-table-column label="小计"  width="120px">
                      <template slot-scope="scope">
                          <span class="prices">{{scope.row.prices = scope.row.num *scope.row.price}}元</span>
                      </template>
                  </el-table-column>
                  <el-table-column label="操作">
                      <template slot-scope="scope"><i class="el-icon-close"  @click="deleteCar(scope.row.carId)"></i></template>
                  </el-table-column>
              </el-table>
              <div class="tail">
                  <a class="a1">继续购物</a>
                  <span class="gang">|</span>
                  <span>共 <i>{{total}}</i> 件商品，已选择 <i>{{totals}}</i> 件</span>
                  <span class="datas">合计：<i>{{datas}}</i>元</span>
                  <a class="a2 fr" @click="toOrder">去结算</a>
              </div>
          </div>
      </el-main>
  </div>
</template>
<script>
import head1 from '@/components/car-head'
import Http from '@/utils/HttpUtil'
export default{
  data () {
    return {
      id: 0,
      checkAll: false,
      checked: false,
      total: 0,
      totals: 0,
      datas: 0,
      data: [],
      action: {
        selectById: 'CAT/Car/selctCarById',
        deleteById: 'CAT/Car/deleteById'
      }
    }
  },
  components: {
    head1
  },
  methods: {
    change (data) {
      this.datas = 0
      this.totals = 0
      data.forEach((res) => {
        this.datas += res.prices
        this.totals += res.num
        sessionStorage.setItem('price', this.datas)
      })
    },
    getCarList () {
      let params = {
        userId: sessionStorage.getItem('user_id')
      }
      Http.xhrPost(this, this.action.selectById, params, (res) => {
        if (res.data.length === 0) {
          this.$message({
            type: 'warning',
            message: '请先选好饮品哦！'
          })
          this.$router.push('/home')
        }
        this.data = res.data
        this.total = this.data.length
        console.log(this.data)
      })
    },
    deleteCar (index) {
      let params = {
        carId: index
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
          this.getCarList()
        })
      })
    },
    toOrder () {
      if (this.totals !== this.total) {
        this.$message({
          type: 'error',
          message: '亲~请选择要结算的商品哦！不需要的商品请删除哦'
        })
      }
      if (this.totals === this.total) {
        this.$router.push('/order')
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
    this.getCarList()
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
            .table{
                .img{
                    width: 90px;
                    height: 90px;
                }
                i{
                    font-size: 20px;
                   &:hover{
                       cursor: pointer;
                       color: #ff6700;
                   }
                }
                .name{
                    color: #424242;
                    font-size: 20px;
                }
                .price{
                      padding-right: 18px;
                      color:#424242;
                }
                .prices{
                    color:#ff6700;
                    font-size: 16px;
                }
            }
            .tail{
                width: 100%;
                height: 48px;
                background-color: #FFFFFF;
                margin-top: 25px;
                .a1{
                    line-height: 48px;
                    margin-left: 32px;
                    color: #757575;
                    &:hover{
                        color:#ff6700
                    }
                }
                .gang{
                    color: #f6f6f6;
                    margin-left: 15px;
                    margin-right: 15px;
                }
                i{
                    font-style:normal;
                    color: #ff6700;
                }
                .a2{
                    width: 200px;
                    height: 48px;
                    line-height: 48px;
                    font-size: 18px;
                    margin-left: 50px;
                    vertical-align: top;
                    background:#ff6700;
                    border-color:#ff6700;
                    color:#fff;
                    text-align: center;
                }
                .datas{
                    position: relative;
                    left: 540px;
                    font-size: 14px;
                    color: #ff6700;
                    i{
                        font-size: 30px;
                    }
                }
            }
        }
    }
</style>

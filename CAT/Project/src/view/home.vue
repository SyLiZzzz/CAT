<template>
    <div class="main">
        <head1></head1>
        <div class="bg">
            <div class="content">
                <!-- 标语-->
                <div class="Slogan">
                    <p class="p1">为您提供最好的饮品ღ</p>
                    <p class="p2">Provide you with the best coffee</p>
                </div>
            </div>
        </div>
        <!-- 浮动搜索框-->
        <div class="nav">
            <div class="logo fl">
                <p @click="logo">All Drinks</p>
            </div>
            <div class="search">
                <i-input search enter-button="搜索" placeholder="请输入查询的饮品" v-model="input" size="large" @on-search="search"/>
            </div>
            <div class="menu fr">
                <Cascader :data="data" class="choose fl" @on-change="change">
                    <span>饮品类型
                        <Icon type="ios-arrow-down" /></span>
                </Cascader>
                <Dropdown class="choose fl" trigger="click">
                    <span>评分
                        <Icon type="ios-arrow-down" /></span>
                    <DropdownMenu slot="list">
                        <DropdownItem v-for="item in price" :key="item.id" @click.native="ScoreOrder(item.id)">{{item.value}}</DropdownItem>
                    </DropdownMenu>
                </Dropdown>
                <Dropdown class="choose fl" trigger="click">
                    <span>价格
                        <Icon type="ios-arrow-down" /></span>
                    <DropdownMenu slot="list">
                        <DropdownItem v-for="item in price" :key="item.id" @click.native="PriceOrder(item.id)">{{item.value}}</DropdownItem>
                    </DropdownMenu>
                </Dropdown>
            </div>
        </div>
        <div class="clear"></div>
        <!-- 饮品内容-->
        <el-row class="row">
            <el-card shadow="hover" v-for="item in drinks" :key="item.drinkId" @click.native="showModel(item.drinkId)" class="fl">
                <div class="img">
                <img :src="'../../static/upload/'+item.drinkPicture">
                </div>
                <span>{{item.drinkName}}</span>
            </el-card>
            <!-- 详情 -->
              <Modal v-model="modal1" footer-hide class="modal">
                  <div class="img">
                      <img :src="'../../static/upload/'+img">
                  </div>
                  <div class="content">
                      <span class="title">{{title}}</span>
                      <Divider size="small">Café caliente</Divider>
                      <p class="price fl"><span>¥</span>{{value}}</p>
                      <Button :size="buttonSize" icon="md-cart" type="info" @click="Car">加入购物车</Button>
                      <span class="rate"><el-rate :value="rate" disabled  text-color="#ff9900"></el-rate></span>
                      <p class="info">{{info}}</p>
                  </div>
              </Modal>
        </el-row>

        <!-- 分页条 -->
          <el-row type="flex" justify="center" style="margin-top: 20px;">
            <el-pagination
                  @size-change="pageSizeChange"
                  @current-change="currPageChange"
                  :current-page="curr_page"
                  :page-size="page_size"
                  layout="total, prev, pager, next, jumper"
                  :total="total">
                </el-pagination>
          </el-row>
    </div>
</template>
<script>
import head1 from '@/components/head'
import Http from '@/utils/HttpUtil'
export default {
  data () {
    return {
      action: {
        drink: 'CAT/Drink/selectPageDrink',
        coffee: 'CAT/Drink/selectCoffee',
        tea: 'CAT/Drink/selectTea',
        scoreAsc: 'CAT/Drink/selectScoreAsc',
        scoreDesc: 'CAT/Drink/selectScoreDesc',
        priceAsc: 'CAT/Drink/selectPriceAsc',
        priceDesc: 'CAT/Drink/selectPriceDesc',
        selectById: 'CAT/Drink/selectById',
        addtoCar: 'CAT/Car/addtoCar'
      },
      total: 0,
      page_size: 16,
      curr_page: 1,
      drinks: [],
      name: '',
      modal1: false,
      title: '',
      img: '',
      input: '',
      info: '',
      rate: 0,
      value: 0,
      did: 0,
      buttonSize: 'large',
      data: [{
        value: 'coffee',
        label: '咖啡'
      }, {
        value: 'tea',
        label: '茶'
      }],
      price: [{
        id: 1,
        value: '升序'
      },
      {
        id: 2,
        value: '降序'
      }
      ]
    }
  },
  components: {
    head1
  },
  mounted () {
    this.DrinkList()
    this.checkLogin()
  },
  methods: {
    // 查询全部
    DrinkList (name = null, curr_page = 1, page_size = 16) {
      const params = {
        'curr_page': curr_page,
        'page_size': page_size
      }
      if (name !== null && name !== '') {
        params['drink_name'] = name
      }
      Http.xhrGet(this, this.action.drink, params, (res) => {
        console.log(res)
        // 总条数，当前页，当前页最大条数，数据
        this.total = res.data.total
        this.curr_page = res.data.pageNum
        this.page_size = res.data.pageSize
        this.drinks = res.data.list
      })
    },
    // 查询coffee
    CoffeeList (name = null, curr_page = 1, page_size = 16) {
      const params = {
        'curr_page': curr_page,
        'page_size': page_size
      }
      if (name !== null && name !== '') {
        params['drink_name'] = name
      }
      Http.xhrGet(this, this.action.coffee, params, (res) => {
        console.log(res)
        // 总条数，当前页，当前页最大条数，数据
        this.total = res.data.total
        this.curr_page = res.data.pageNum
        this.page_size = res.data.pageSize
        this.drinks = res.data.list
      })
    },
    // 查询Tea
    TeaList (name = null, curr_page = 1, page_size = 16) {
      const params = {
        'curr_page': curr_page,
        'page_size': page_size
      }
      if (name !== null && name !== '') {
        params['drink_name'] = name
      }
      Http.xhrGet(this, this.action.tea, params, (res) => {
        console.log(res)
        // 总条数，当前页，当前页最大条数，数据
        this.total = res.data.total
        this.curr_page = res.data.pageNum
        this.page_size = res.data.pageSize
        this.drinks = res.data.list
      })
    },
    // 分数Asc
    ScoreAscList (name = null, curr_page = 1, page_size = 16) {
      const params = {
        'curr_page': curr_page,
        'page_size': page_size
      }
      if (name !== null && name !== '') {
        params['drink_name'] = name
      }
      Http.xhrGet(this, this.action.scoreAsc, params, (res) => {
        console.log(res)
        // 总条数，当前页，当前页最大条数，数据
        this.total = res.data.total
        this.curr_page = res.data.pageNum
        this.page_size = res.data.pageSize
        this.drinks = res.data.list
      })
    },
    // 分数desc
    ScoreDescList (name = null, curr_page = 1, page_size = 16) {
      const params = {
        'curr_page': curr_page,
        'page_size': page_size
      }
      if (name !== null && name !== '') {
        params['drink_name'] = name
      }
      Http.xhrGet(this, this.action.scoreDesc, params, (res) => {
        console.log(res)
        // 总条数，当前页，当前页最大条数，数据
        this.total = res.data.total
        this.curr_page = res.data.pageNum
        this.page_size = res.data.pageSize
        this.drinks = res.data.list
      })
    },
    // 价格Asc
    PriceAscList (name = null, curr_page = 1, page_size = 16) {
      const params = {
        'curr_page': curr_page,
        'page_size': page_size
      }
      if (name !== null && name !== '') {
        params['drink_name'] = name
      }
      Http.xhrGet(this, this.action.priceAsc, params, (res) => {
        console.log(res)
        // 总条数，当前页，当前页最大条数，数据
        this.total = res.data.total
        this.curr_page = res.data.pageNum
        this.page_size = res.data.pageSize
        this.drinks = res.data.list
      })
    },
    // 价格desc
    PriceDescList (name = null, curr_page = 1, page_size = 16) {
      const params = {
        'curr_page': curr_page,
        'page_size': page_size
      }
      if (name !== null && name !== '') {
        params['drink_name'] = name
      }
      Http.xhrGet(this, this.action.priceDesc, params, (res) => {
        console.log(res)
        // 总条数，当前页，当前页最大条数，数据
        this.total = res.data.total
        this.curr_page = res.data.pageNum
        this.page_size = res.data.pageSize
        this.drinks = res.data.list
      })
    },
    pageSizeChange (pageSize) {
      this.DrinkList(this.name, 1, pageSize)
    },
    // 当前页改变时函数
    currPageChange (currPage) {
      this.DrinkList(this.name, currPage, this.page_size)
    },
    search (value) {
      let name = value
      this.DrinkList(name, 1, 16)
    },
    change (value) {
      if (value[0] === 'coffee') {
        this.CoffeeList()
      }
      if (value[0] === 'tea') {
        this.TeaList()
      }
    },
    ScoreOrder (res) {
      if (res === 1) {
        this.ScoreAscList()
      }
      if (res === 2) {
        this.ScoreDescList()
      }
    },
    PriceOrder (res) {
      if (res === 1) {
        this.PriceAscList()
      }
      if (res === 2) {
        this.PriceDescList()
      }
    },
    logo () {
      this.DrinkList()
    },
    showModel (index) {
      let param = {
        'drink_id': index
      }
      Http.xhrGet(this, this.action.selectById, param, (res) => {
        console.log(res)
        this.did = res.data.drinkId
        this.title = res.data.drinkName
        this.img = res.data.drinkPicture
        this.value = res.data.price
        this.rate = res.data.score
        this.info = res.data.explain
      })
      this.modal1 = true
    },
    Car () {
      let userId = sessionStorage.getItem('user_id')
      let params = {
        userId: userId,
        drinkId: this.did,
        picture: this.img,
        name: this.title,
        price: this.value
      }
      Http.xhrPost(this, this.action.addtoCar, params, (res) => {
        this.$message({
          type: 'success',
          message: res.message
        })
        this.modal1 = false
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
  }
}
</script>

<style scoped lang="scss">
    .main {
        width: 100%;
        background-color: #f7f7f7;
        .bg {
            width: 100%;
            height: 244px;
            background: url(../assets/img/bg.jpg) no-repeat;
            background-size: cover;
        }

        .content {
            position: absolute;
            left: 198px;
            top: 164px;
            width: 425px;
            height: 92px;
            overflow: hidden;

            .Slogan {
                color: #FFFFFF;
                position: relative;
                left: 36px;

                .p1 {
                    font-family: '华文仿宋 Bold', '华文仿宋';
                    font-weight: 700;
                    font-style: normal;
                    font-size: 36px;
                }

                .p2 {
                    font-family: 'French Script MT Bold', 'French Script MT Normal', 'French Script MT';
                    font-weight: 700;
                    font-style: normal;
                    font-size: 36px;
                    margin-left: 25px;
                    margin-top: -2px;
                }
            }
        }

        .nav {
            position: sticky;
            top: 0px;
            background-color: #b4eff6;
            height: 41px;
            width: 80%;
            margin: auto auto;
            margin-top: 15px;
            z-index: 10;
            .logo {
                height: 100%;
                width: 10%;
                text-align: center;
                vertical-align: middle;
                position: relative;
                left: 7.5%;
                cursor: pointer;
                p {
                    font-family: '华文新魏';
                    font-weight: 400;
                    font-style: normal;
                    font-size: 20px;
                    color: #18acfc;
                    font-size: 25px;
                    padding-top: 10px;
                }
            }

            .search {
                width: 35%;
                height: 100%;
                margin: auto 27%;
            }

            .menu {
                height: 100%;
                width: 30%;
                position: relative;
                top: -40px;
                right: 10px;

                .choose {
                    text-align: center;

                    span {
                        background: inherit;
                        background-color: inherit;
                        border: none;
                        border-radius: 0px;
                        box-shadow: none;
                        font-family: '华文新魏';
                        font-weight: 400;
                        font-style: normal;
                        font-size: 20px;
                        color: #17233d;
                        margin-right: 30px;
                        line-height: 38px;
                    }
                }
            }
        }
        .row{
            margin-top: 10px;
            width: 85%;
            margin-left: 135px;
            .el-card{
                margin-right: 18px;
                margin-left: 18px;
                margin-top: 10px;
                width: 275px;
                text-align: center;
                .img{
                    height: 230px;
                    width: 230px;
                    overflow: hidden;
                   img{
                       width: 100%;
                       height: 100%;
                       cursor: pointer;
                       transition: all 0.6s;
                       &:hover{
                           transform: scale(1.2);
                       }
                   }
                }
                span{
                    color: #409eff;
                    font-family: '\534E\6587\65B0\9B4F';
                    font-weight: 400;
                    font-style: normal;
                    font-size: 25px;
                }
            }

        }
    }
    .modal{
        .img{
          width: 230px;
          height: 230px;
          overflow: hidden;
          img{
              width: 100%;
              height: 100%;
              cursor: pointer;
              transition: all 0.6s;
              &:hover{
                  transform: scale(1.2);
              }
          }
        }
        .content{
            width: 246px;
            position: absolute;
            top: 40px;
            left: 260px;
            .title{
                color:#3e1415;
                font-family: '\534E\6587\65B0\9B4F';
                font-weight: 500;
                font-style: normal;
                font-size: 30px;
                margin-left: 10%;
            }
            .price{
                color:red;
                font-weight: bold;
                font-size: 27px;
                margin-left: 15px;
                span{
                    margin-right: 5px;
                }
            }
            .ivu-btn-info{
                margin-left: 17px;
            }
            .info{
                width: 100%;
                margin-top: 5px;
                font-size: 13px;
            }
            .rate{
                text-align: center;
                .el-rate{
                    margin-top: 9px;
                    margin-left: -110px;
                }
            }
        }
    }
</style>

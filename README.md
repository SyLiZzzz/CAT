# About

此项目笔者的毕业设计项目，使用前后端分离开发。

前端使用Vue+element-ui+iview、后端使用SSM框架。

部分为模拟小米的电商平台+后台管理，因全部页面代码为笔者手写故有部分地方有不影响运行的美观问题。

# 说明

如果对此项目有兴趣，可以点击 "**Star**" 支持一下，感激不尽。

下载本项目后会有两个项目文件，demo为java代买，Project为Vue代码

本项目的数据库安装在Linux系统上。如想在本地运行，请在本地Mysql中运行**CAT.sql**文件，并修改 **demo\src\main\resources\jdbc.properties**中的 **jdbcUrl**

本项目使用前后端分离开发，前端使用HbuilderX开发，后端使用Idea开发，并使用Vmware虚拟Linux环境安装MySql5.5，Linux发行版本为CentOS 6.8

## 注意事项

**前后端项目都导入后部分功能失效的问题。除本文提到的需修改的地方外还有以下注意事项**

1. 如想启用短信验证码功能，请自行在https://www.ihuyi.com/sms.html注册领取短信资格（注册就送66条），因笔者剩余条数不多，故删除部分代码，请读者根据官方文档添加。

2. 因为上传的图片名为uuid+名字，导致超出Git上传长度，故删除图片。需要图片下过请自行在后台上传

3. 如想使用MyBatis逆向工程请在配置文件中配置数据库账户密码

4. 图片上传后端代码，请在DrinkController中填写自己安装前端的项目路径，例如下

   ```java
   public BaseModel upload(@RequestParam("file") MultipartFile file, Drink drink, HttpServletRequest request, BaseModel baseModel) throws Exception {
          // String path = request.getSession().getServletContext().getRealPath("/upload");
           String path = "D:/tool/Vue/实训项目/Project/static/upload";
           File filePath = new File(path);
           System.out.println("文件的保存路径：" + path);
   ```

#  技术栈

**前端**

​	vue + vuex + vue-router + axios + webpack + ES6 + sass + element-ui + iview + V - Distpicker

**后端**

​	maven + Spring + SpringMVC  + MyBatis + Generator

# 项目运行

下载的Vue项目不带模块包，需在项目路径下运行npm i 指令

要求：Tomcat 启动端口为8080 否则前台访问不到后台接口地址，如有要求可在 **Project\config\index.js**中修改

``` js
proxyTable: {
        // 解决跨域问题
        '/CAT': {
          // 接口域名
          target: 'http://localhost:8080/CAT',
          // 是否跨域
          changeOrigin: true,
          // 开启身份认证
          ws: true,
          // 进行前缀匹配
          pathRewrite: {
            '^/CAT': ''
          }
        }
    },
```

**终端运行指令**

```xml
cd Project //项目路径
npm i
npm run dev //会提示访问地址 如：http://localhost:8081
```

**idea中使maven更新jar包**

选择右侧导航栏-->点击maven-->点击第一个图标（双螺旋的那个）

或者选择右侧导航栏-->点击maven-->demo-->Plugins-->clean-->install

**idea中配置Tomcat**

点击 Edit Configurations --> +  --> 添加Tomcat Server local --->添加Tomcat服务器-->URL 为 http://localhost:8080/CAT/

如果Tomcat配置有误，请自行百度，但启动url必须为8080/CAT/ 。当然如果要改url地址，就又得改前端的跨域问题，楼上有。

# 功能列表

- [x] 登录/注册 -- 完成
- [x] 手机验证码登录  -- 完成
- [x] 添加商品入购物车 -- 完成
- [x] 商品下单 -- 完成
- [x] 地址管理 -- 完成
- [x] 购物车管理 -- 完成
- [x] 筛选查询商品 -- 完成
- [x] 用户管理 -- 完成
- [x] 商品管理 -- 完成
- [x] 图片上传 -- 完成
- [x] 地址管理 -- 完成
- [x] 订单管理 -- 完成
- [x] 权限管理 -- （假）完成
- [ ] redis缓存 -- 未完成
- [ ] jsr303校验 -- 未完成
- [ ] shiro校验 -- 未完成

# 项目截图

在PPT中

# 最后

CAT并不是猫的意思

而是coffee and tea 的缩写

再见！
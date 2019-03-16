## 项目介绍



###  数据库

数据库名：shoppingcenter   四张表：用户表user、商品表commodity、购物车表shoppingcart、财务表finance

1. 用户表 userID、userName、password（md5加密存储）、userType（0-买家，1-卖家） 

   数据库中初始化两个用户 

   * buyer  密码-reyub  userType-0 
   * seller   密码 -relles  userType-1

   索引：userName用户名

2. 商品表 commodityID、title、abstractMsg、currentPrice、text、picture、soldAmount、关联用户表的卖家ID-userID

   picture：

   * 图片地址 图片的url地址
   * 本地上传 上传的图片存储在项目根目录/static/image目录下 存入数据库的图片url是相对路径

   索引：commodityID

3. 购物车表 commodityID、amout、userID

   两个外键：

   * commodity  关联商品表的commodityID
   * user 关联用户表的userID（买家对应的ID）

   索引：建立userID字段的索引 用于加速对买家购物车中商品的查询以及删除

4. 财务表 userID、commodityID、boughtPrice、amount、purchaseTime

   两个外键：

   - commodity  关联商品表的commodityID
   - user 关联用户表的userID（买家对应的ID）

   索引：commodityID字段建立索引 加速显示已购买商品和未购买商品的查询

   ​

### 项目的初始化流程（SSM架构）

1. 加载Spring上下文环境配置文件。Web容器读取web.xml配置文件，读取<listener>和<context-param>两个结点，<context-param>定义了Spring的配置文件，创建ServletContext对象，<listener>中的ContextLoaderListener监听ServletContext对象的变化，自动装配ApplicationContext的配置信息。

   Spring容器中扫描了非控制器的业务组件注册为bean，定义了dbcp数据库连接池，通过定义SqlSessionFactoryBean实现与mybatis的整合，加载mapper文件，通过jbdc的DataSourceTransactionManager进行事务的管理，并定义了一个执行批量的sqlSession用于购物车的清空。

2. 过滤器的初始化，定义了字符编码过滤器CharacterEncodingFilter以及可以发送PUT、DELETE类型请求的过滤器HttpPutFormContentFilter和jsp过滤器（避免不通过登录访问到不该访问的页面）

3. SpringMVC加载过程通过Servlet节点（web应用启动时加载因为配置了<load-on-start>）。web.xml配置了DispatcherServlet前端控制器，负责调度控制web应用的流程。一些静态资源还是需要tomcat来处理。@Controller注解的类将会被标识为控制器注册在SpringMVC容器中，SpringMVC容器中定义了对aspectJ的支持，用于通过日志切面将Controller层的日志输出。

   ​

   ​

### 项目部署（阿里云服务器ECS）

1. 修改db.properties文件中的mysql jdbcUrl、以及用户名和密码连接到阿里云数据库rds（Mysql）---内网连接
2. 项目打成war包
3. 上传至tomcat的webapps目录下 在tomcat的bin目录运行startup.sh命令开启tomcat，通过url（云服务器外网地址）进行访问
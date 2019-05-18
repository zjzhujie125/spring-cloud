#### 介绍

**spring-cloud-archetype，本项目为SpringCloud 微服务框架的脚手架**

**可用于个人入门学习及基础微服务架构搭建**

#### 软件架构

**springCloud脚手架**

![1558202796957](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1558202796957.png)

#### 安装教程

**下载项目，配好GIT环境，配好JAVA环境，配好Maven环境，配好RabbitMQ环境，将代码导入IDE即可运行。**

#### **使用说明**

# -------------------环境信息及工具

**操作系统：**

​	**Windows**

**电脑内存：**

​	**16G+**

**远程仓库：**

​	**码云**<https://gitee.com/>

**开发工具：**

​	**IntelliJ IDEA Maven项目**

**JDK版本：**

​	**JDK1.8**

**Maven版本：**

​	**apache-maven-3.5.2**

**RabbitMQ版本：**

​	**rabbitmq_server-3.6.5**

**zipkin服务端：**

​	**zipkin-server-2.10.1****

**spring-boot：**

​	**2.0.3.RELEASE**

**spring-cloud：**

​	**Finchley.RELEASE**

# -------------------启动顺序及单元测试指引

# 0.本地启动rabbitMQ服务

- ## 端口：

  - ### 5672

- ## 用户名及密码：

  - ### guest guest

- ## 测试地址：

  - ### <http://localhost:15672/>



# 1.启动Zipkin链路追踪服务器

- ## 	Jar启动（支持rabbitMQ）：

  - ### 			whyuan-sc>cd sc-zipkin-server

  - ### 			whyuan-sc\sc-zipkin-server>java -jar zipkin-server-2.10.1-exec.jar --zipkin.collector.rabbitmq.addresses=localhost

- ## 	端口：

  - ### 		9411

- ## 	测试地址：

  - ### 		<http://localhost:9411/zipkin/>



# 2.启动Eureka注册中心服务EurekaServerApplication

- ## 		应用名称：

  - ### 				sc-eureka-server

- ## 		端口：

  - ### 				1111

- ## 		测试地址：

  - ### 				<http://localhost:1111/>



# 3.启动Config Server 配置中心服务ConfigServerApplication

- ## 应用名称：

  - ### sc-config-server

- ## 端口：

  - ### 8888

- ## git地址：（注意修改成自己仓库地址）

  - ### https://gitee.com/xiaowugui521/whyuan-sc

- ## git分支：

  - ### master

- ## git搜索目录：（注意修改成自定义的目录）

  - ### sc-config-server

- ## 测试地址：

  - ### <http://localhost:8888/version/dev>	

  - ### <http://localhost:8888/version/prod>



# 4.启动Eureka注册中心客户端，注册产品服务EurekaClientApplication【启动三个】

- ## 		应用名称：

  - ### 				sc-eureka-client

- ## 		端口：

  - ### 				2222 2223 2224

- ## 		测试地址：

  - ### 				<http://localhost:2222/products>

  - ### 				<http://localhost:2223/products>

  - ### 				<http://localhost:2224/products>



# 5.启动Ribbo客户端

- ## 		应用名称：

  - ### 				sc-ribbon

- ## 		端口：

  - ### 				3333

- ## 		测试地址（多刷新几次，可看到随机负载均衡效果）：

  - ### 				<http://localhost:3333/products>

- ## 		调用链路：

  - ### 				http://localhost:3333/products  ---------》服务发现---------》http://sc-eureka-client/products ---------》负载均衡调用sc-eureka-client



# 6.启动Feign客户端，注册视图服务FeignApplication【启动三个】

- # 		应用名称：

  - ### 				sc-feign

- # 		端口：

  - ### 				4444 4445 4446

- ## 		测试地址（多刷新几次，可看到随机负载均衡效果）：

  - ### 				<http://localhost:4444/products>

  - ### 		<http://localhost:4445/products>

  - ### 		<http://localhost:4446/products>

- ## 		调用链路：

  - ### 				http://localhost:4444/products  ---------》服务发现---------》http://sc-eureka-client/products ---------》负载均衡调用sc-eureka-client



# 7.启动Hystrix监控服务HystrixDashboardApplication

- ## 	应用名称：

  - ### 		sc-hystrix-dashboard

- ## 	端口：

  - ### 		5555

- ## 	测试地址：

  - ### 		<http://localhost:5555/hystrix/>



# 8.启动Turbine聚合监控服务TurbineApplication

- ## 	应用名称：

  - ### 		sc-turbine

- ## 	端口：

  - ### 		6666

- ## 	测试：

  - ### 		通过启动控制台将看到默认聚合监控的URL，例：XXX... Url for host: http://IP:4445/actuator/hystrix.stream default



# 9.启动Zuul网关服务ZuulApplication

- ## 	应用名称：

  - ### 		sc-zuul

- ## 	端口：

  - ## 		7777

- ## 	测试地址：

  - ### 		http://localhost:7777/api-data/products			访问sc-eureka-client,返回JSON

  - ### 		http://localhost:7777/api-view/products			访问sc-feign,返回视图

  - ### <http://localhost:1111/>                                                       所有都启动完毕，注册中心将看到以下实例

# Instances currently registered with Eureka

| Application          | AMIs        | Availability Zones | Status                                                       |
| :------------------- | :---------- | :----------------- | :----------------------------------------------------------- |
| **SC-CONFIG-SERVER** | **n/a** (1) | (1)                | **UP** (1) - [192.168.158.204:sc-config-server:8888](http://192.168.158.204:8888/actuator/info) |
| **SC-EUREKA-CLIENT** | **n/a** (3) | (3)                | **UP** (3) - [192.168.158.204:sc-eureka-client:2222](http://localhost:2222/actuator/info) , [192.168.158.204:sc-eureka-client:2223](http://localhost:2223/actuator/info) , [192.168.158.204:sc-eureka-client:2224](http://localhost:2224/actuator/info) |
| **SC-FEIGN**         | **n/a** (3) | (3)                | **UP** (3) - [192.168.158.204:sc-feign:4444](http://192.168.158.204:4444/actuator/info) , [192.168.158.204:sc-feign:4446](http://192.168.158.204:4446/actuator/info) , [192.168.158.204:sc-feign:4445](http://192.168.158.204:4445/actuator/info) |
| **SC-RIBBON**        | **n/a** (1) | (1)                | **UP** (1) - [192.168.158.204:sc-ribbon:3333](http://192.168.158.204:3333/actuator/info) |
| **SC-TURBINE**       | **n/a** (1) | (1)                | **UP** (1) - [192.168.158.204:sc-turbine:6666](http://192.168.158.204:6666/actuator/info) |
| **SC-ZUUL**          | **n/a** (1) | (1)                | **UP** (1) - [192.168.158.204:sc-zuul:7777](http://192.168.158.204:7777/actuator/info) |

# **进程：**

## ![1558200128831](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1558200128831.png)

# -------------------特殊功能测试指引

# *.Hytrix 断路器，降级服务演示：

- ## 		所有服务按照顺序正常启动


- ## 		停掉所有sc-eureka-client启动的产品服务


- ## 		测试地址：

  - ### 				<http://localhost:4444/products>



# *.Hystrix 断路器监控，演示：

- ## 	所有服务按照顺序正常启动

  - ### 		访问http://localhost:4444/products，确认产品服务正常

  - ### 		访问http://localhost:5555/hystrix/，确认监控服务正常

- ## 	启动sc-feign项目下的 com.whyuan.sc.feign.util.AccessFeignService

  - ### 		将周期访问http://localhost:4444/products，http://localhost:4445/products，http://localhost:4446/products

- ## 	测试：

  - ### 		浏览器访问http://localhost:4444/actuator/hystrix.stream （4445,4446都可以）

    - #### 			PING回显正常

  - ### 		浏览器访问http://localhost:5555/hystrix/

    - #### 			Dashboard中输入http://localhost:4444/actuator/hystrix.stream ，点击Monitor Stread，将看到监控信息面板（4445,4446都可以）



# *.Turbine聚合监控，演示：

- ## 	所有服务按照顺序正常启动

  - ### 		访问http://localhost:4444/products，确认产品服务正常

  - ### 		访问http://localhost:5555/hystrix/，确认监控服务正常

- ## 	启动sc-feign项目下的 com.whyuan.sc.feign.util.AccessFeignService

  - ### 		将周期访问http://localhost:4444/products，http://localhost:4445/products，http://localhost:4446/products

- ## 	测试：

  - ### 		浏览器访问http://localhost:5555/hystrix/

  - #### 			Dashboard中输入http://localhost:6666/turbine.stream ，点击Monitor Stread，将看到聚合监控信息面板



# *.配置中心客户端，演示：

- ## 启动Eureka注册中心服务EurekaServerApplication

- ## 启动Eureka注册中心客户端，注册产品服务EurekaClientApplication

- ## 启动Config Server 配置中心服务ConfigServerApplication（端口8888）

- ## 启动Feign客户端，注册视图服务，注册配置中心客户端FeignApplication（端口4444）

- ## 测试：

  - ### 启动Feign客户端时，将看到拉取配置中心配置的日志

    -  **XXXc.c.c.ConfigServicePropertySourceLocator : Fetching config from server at : http://192.168.158.204:8888/**

    - **XXXc.c.c.ConfigServicePropertySourceLocator : Located environment: name=sc-feign, profiles=[prod], label=master, version=9d22eda1643d6a943321617f971ed26b7ef6f8ce, state=null**

  - ### 浏览器访问<http://localhost:8888/version/dev>

    - 将看到配置信息

      {

      - **name**: "version",

      - profiles:[

        - "dev"

        ],

      - **label**: null,

      - **version**: "9d22eda1643d6a943321617f971ed26b7ef6f8ce",

      - **state**: null,

      - propertySources:[

        - 

          {

          - **name**: "<https://gitee.com/xiaowugui521/whyuan-sc/sc-config-server/application-dev.properties>",

          - source:{

            - **version**: "dev version 1.0"

            }

          }

        ]

      }

  - ### 浏览器访问<http://localhost:4444/products>

    - **将看到视图返回版本信息prod version 2.0**

- ### BUS动态刷新配置测试：

  - ### 修改sc-config-server下的application-prod.properties内容为：version= prod version 2.0 -> prod version 999

  - ### 推送修改后的application-prod.properties至git远程仓库

  - ### 启动sc-feign项目下的com.whyuan.sc.feign.util.FreshConfigUtil（通过BUS端点动态刷新配置信息）

  - ### 浏览器访问http://localhost:4444/products，可以看到版本栏框中内容变成了修改后的值。

# -------------------自己搭建，整合顺序推荐

# 知识点汇总：

- ## sc-服务注册中心eureka-server：

  - ### 依赖：spring-cloud-starter-netflix-eureka-server		

  - ### 注解：@EnableEurekaServer

- ## sc-服务注册中心eureka-client：

  - ### 依赖：spring-cloud-starter-netflix-eureka-client		   

  - ### 注解：@EnableEurekaClient

- ## sc-服务发现，调用，负载均衡Ribbon（RestTemplate）：	

  - ### 依赖：spring-web

  - ### 注解：@EnableDiscoveryClient	@LoadBalanced

- ## sc-服务发现，调用，负载均衡feign（ribbon）：

  - ### 依赖：spring-cloud-starter-openfeign	

  - ### 注解：@EnableFeignClients	@FeignClient

- ## sc-服务调用链路追踪（sleuth+zipkin）：

  - ### 依赖：zipkin-server   zipkin-autoconfigure-ui   spring-cloud-starter-sleuth   spring-cloud-starter-zipkin

  - ### 注解：@EnableZipkinServer

- ## sc-服务容错断路器hystrix：.

  - ### 依赖：spring-cloud-starter-netflix-hystrix	

  - ### 注解：@EnableCircuitBreaker 	@HystrixCommand

- ## sc-服务容错断路器监控hystrix-dashboard：

  - ### 依赖：spring-cloud-starter-netflix-hystrix-dashboard	

  - ### 注解：@EnableHystrixDashboard

- ## sc-服务容错断路器聚合监控turbine：

  - ### 依赖：spring-cloud-starter-netflix-turbine	

  - ### 注解：@EnableTurbine

- ## sc-服务网关zuul：

  - ### 依赖：spring-cloud-starter-netflix-zuul	

  - ### 注解：@EnableZuulProxy

- ## sc-服务配置中心服务器：

  - ### 依赖：spring-cloud-config-server		

  - ### 注解：@EnableConfigServer

- ## sc-服务配置中心客户端：

  - ### 依赖：spring-cloud-starter-config

- ## sc-服务消息总线bus：

  - ### 依赖：spring-cloud-starter-bus-amqp

# 整合推荐顺序：

- ## 	1.sc-eureka-server

  - **启动Eureka注册中心服务**

- ## 	2.sc-eureka-client

  * **启动一个Eureka客户端，向注册中心注册一个基础服务。（可暴露不同端口启动多次，构成服务集群）**

- ## 	3.sc-ribbon

  - **启动一个Eureka客户端，向注册中心注册一个服务，用于服务发现，该服务是通过ribbon的RestTemplate负载均衡访问sc-eureka-client的服务。**

- ## 	**4.sc-feign**

  - **启动一个Eureka客户端，向注册中心注册一个服务，用于服务发现，该服务是通过Feign客户端负载均衡访问sc-eureka-client的服务。（Feign包装了RestTemplate）**

- ## 	5.sc-zipkin-server

  - **启动Zipkin链路追踪服务，需要被纳入追踪的应用需配置Zipkin客户端，向Zipkin服务上报追踪信息。（eg：改造sc-feign为被追踪应用）**

- ## 	6.sc-hystrix-dashboard

  - **启动一个hystrix断路器监控面板服务，需要被纳入监控的应用需配置@EnableCircuitBreaker激活服务短路。（eg：改造sc-feign为被监控应用）**

- ## 	7.sc-turbine

  - **启动一个turbine聚合监控服务，将自动扫描hytrix-dashboard监控的URL，将其聚合上报。**

- ## 8.sc-zuul

  - **启动一个Eureka客户端，向注册中心注册一个网关服务，用于服务发现。**

- ## 9.sc-config-server

  - **启动一个Eureka客户端，向注册中心注册配置中心服务，基于git的外部化配置。**
  - **配置中心客户端将通过服务发现获取配置中心的配置信息。（eg：改造sc-feign为配置中心客户端）**
  - **配置中心客户端可在不重启的情况下，通过BUS-AMQP动态刷新配置信息。（eg：改造sc-feign接入BUS）**
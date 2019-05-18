#### 介绍

spring-cloud-archetype

用于个人学习及基础微服务架构搭建

#### 软件架构

springCloud脚手架

#### 安装教程

下载项目，配好JAVA开发的环境变量，导入IDE即可运行。

#### 使用说明

# ------------------------------------------------------------------------------------------------------------------------环境信息及工具

电脑内存：

​	16G+

开发工具：

​	IntelliJ IDEA Maven项目

JDK版本：

​	JDK1.8

spring-boot：

​	2.0.3.RELEASE

spring-cloud：

​	Finchley.RELEASE

zipkin服务端：

​	zipkin-server-2.10.1

# -----------------------------------------------------------------------------------------------------------------------启动顺序及单元测试指引

# 1.启动Zipkin链路追踪服务器

- ## 	Jar启动：

  - ### 			whyuan-sc>cd sc-zipkin-server

  - ### 			whyuan-sc\sc-zipkin-server>java -jar zipkin-server-2.10.1-exec.jar

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

# 3.启动Eureka注册中心客户端，注册产品服务EurekaClientApplication【启动三个】

- ## 		应用名称：

  - ### 				sc-eureka-client

- ## 		端口：

  - ### 				2222 2223 2224

- ## 		测试地址：

  - ### 				<http://localhost:2222/products>

  - ### 				<http://localhost:2223/products>

  - ### 				<http://localhost:2224/products>

# 4.启动Ribbo客户端

- ## 		应用名称：

  - ### 				sc-ribbon

- ## 		端口：

  - ### 				3333

- ## 		测试地址（多刷新几次，可看到随机负载均衡效果）：

  - ### 				<http://localhost:3333/products>

- ## 		调用链路：

  - ### 				http://localhost:3333/products  ---------》服务发现---------》http://sc-eureka-client/products ---------》负载均衡调用sc-eureka-client

# 5.启动Feign客户端，注册视图服务FeignApplication【启动三个】

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



# 6.启动Hystrix监控服务HystrixDashboardApplication

- ## 	应用名称：

  - ### 		sc-hystrix-dashboard

- ## 	端口：

  - ### 		5555

- ## 	测试地址：

  - ### 		<http://localhost:5555/hystrix/>



# 7.启动Turbine聚合监控服务TurbineApplication

- ## 	应用名称：

  - ### 		sc-turbine

- ## 	端口：

  - ### 		6666

- ## 	测试：

  - ### 		通过启动控制台将看到默认聚合监控的URL，例：XXX... Url for host: http://IP:4445/actuator/hystrix.stream default



# 8.启动Zuul网关服务ZuulApplication

- ## 	应用名称：

  - ### 		sc-zuul

- ## 	端口：

  - ## 		7777

- ## 	测试地址：

  - ### 		http://localhost:7777/api-data/products			访问sc-eureka-client,返回JSON

  - ### 		http://localhost:7777/api-view/products			访问sc-feign,返回视图

# -----------------------------------------------------------------------------------------------------------------------------------------------特殊功能测试指引

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

# --------------------------------------------------------------------------------------------------------------------------------------自己搭建，整合顺序推荐

# 整合推荐顺序：

- ## 	1.sc-eureka-server


​			**启动Eureka注册中心服务**

- ## 	2.sc-eureka-client


​			**启动一个Eureka客户端，向注册中心注册一个基础服务。（可暴露不同端口启动多次，构成服务集群）**		

- ## 	3.sc-ribbon


​			**启动一个Eureka客户端，向注册中心注册一个服务，用于服务发现，该服务是通过ribbon的RestTemplate负载均衡访问sc-eureka-client的服务。**

- ## 	**4.sc-feign**


​			**启动一个Eureka客户端，向注册中心注册一个服务，用于服务发现，该服务是通过Feign客户端负载均衡访问sc-eureka-client的服务。（Feign包装了RestTemplate）**

- ## 	5.sc-zipkin-server


​			**启动Zipkin链路追踪服务，需要被纳入追踪的应用需配置Zipkin客户端，向Zipkin服务上报追踪信息。**

- ## 	6.sc-hystrix-dashboard


​			**启动一个hystrix断路器监控面板服务，需要被纳入监控的应用需配置@EnableCircuitBreaker激活服务短路。**

- ## 	7.sc-turbine


​			**启动一个turbine聚合监控服务，将自动扫描hytrix-dashboard监控的URL，将其聚合上报。**
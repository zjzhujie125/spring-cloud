package com.whyuan.sc.server;

import com.whyuan.sc.utils.PortUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

//1.启动注册中心服务
//http://localhost:1111/
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

    public static void main(String[] args) {
        //1111 这个端口是默认的，就不要修改了，后面的子项目，都会访问这个端口。
        int port = 1111;
        PortUtil.checkPort(port, "Eureka 注册中心服务", false);
        new SpringApplicationBuilder(EurekaServerApplication.class).properties("server.port=" + port).run(args);
    }
}

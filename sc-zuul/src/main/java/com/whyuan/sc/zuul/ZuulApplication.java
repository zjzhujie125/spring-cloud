package com.whyuan.sc.zuul;

import com.whyuan.sc.utils.PortUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 〈服务网关〉
 *
 * @author whyuan
 * @create 2019/5/18
 * @since 1.0.0
 */

//http://localhost:7777/api-data/products       访问sc-eureka-client,返回JSON
//http://localhost:7777/api-view/products       访问sc-feign,返回视图
@SpringBootApplication  //Boot应用
@EnableEurekaClient //Eureka客户端注册
@EnableDiscoveryClient  //服务发现
@EnableZuulProxy    //服务网关
public class ZuulApplication {

    public static void main(String[] args) {
        //7777 这个端口是默认的，就不要修改了。
        int port = 7777;
        PortUtil.checkPort(port, "Zuul 网关服务", false);
        new SpringApplicationBuilder(ZuulApplication.class).properties("server.port=" + port).run(args);
    }

}
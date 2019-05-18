package com.whyuan.sc.ribbon;

import com.whyuan.sc.ribbon.config.WebConfig;
import com.whyuan.sc.utils.PortUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

/**
 * 〈服务发现，负载均衡〉
 *
 * @author whyuan
 * @create 2019/5/17
 * @since 1.0.0
 */
@SpringBootApplication
@EnableEurekaClient//服务注册
@EnableDiscoveryClient//服务发现
@Import(WebConfig.class)//引入Config类
public class RibbonApplication {
    public static void main(String[] args) {

        //3333 这个端口是默认的，就不要修改了。
        int port = 3333;
        PortUtil.checkPort(port, "Ribbon 通过注册中心调用Eureka客户端，负载均衡到某个端口的服务", false);
        new SpringApplicationBuilder(RibbonApplication.class).properties("server.port=" + port).run(args);

    }

    //Ribbon相关：注入RestTemplate
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
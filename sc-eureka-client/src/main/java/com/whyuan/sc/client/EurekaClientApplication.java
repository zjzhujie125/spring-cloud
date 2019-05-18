package com.whyuan.sc.client;


import brave.sampler.Sampler;
import com.whyuan.sc.utils.PortUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

//2.向注册中心注册服务，改变端口可注册多次
@SpringBootApplication
@EnableEurekaClient
//@EnableDiscoveryClient//非Eureka注册中心推荐用这个
public class EurekaClientApplication {

    public static void main(String[] args) {
        System.out.println("请输入端口号, 推荐  2222 、 2223 、 2224");
        Scanner scanner = new Scanner(System.in);
        int port = scanner.nextInt();
        scanner.close();
        if (PortUtil.isUsed(port)) {
            System.err.println(port + "端口已被占用");
            System.exit(1);
        }
        new SpringApplicationBuilder(EurekaClientApplication.class).properties("server.port=" + port).run(args);

    }

    //Zipkin 相关：持续抽样
    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }
}
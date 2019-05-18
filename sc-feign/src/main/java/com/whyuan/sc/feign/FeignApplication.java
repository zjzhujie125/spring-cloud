package com.whyuan.sc.feign;

import brave.sampler.Sampler;
import com.whyuan.sc.utils.PortUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

/**
 * 〈服务调用代理〉
 *
 * @author whyuan
 * @create 2019/5/17
 * @since 1.0.0
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients //开启Feign客户端代理功能
@EnableCircuitBreaker //激活服务短路，=@EnableHystrix + SpringCloud
//@EnableHystrix//没有SpringCloud的功能，如端点
public class FeignApplication {

    public static void main(String[] args) {
        System.out.println("请输入端口号, 推荐  4444 、 4445 、 4446");
        Scanner scanner = new Scanner(System.in);
        int port = scanner.nextInt();
        scanner.close();
        if (PortUtil.isUsed(port)) {
            System.err.println(port + "端口已被占用");
            System.exit(1);
        }
        new SpringApplicationBuilder(FeignApplication.class).properties("server.port=" + port).run(args);

    }

    //Zipkin 相关：持续抽样
    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }

}
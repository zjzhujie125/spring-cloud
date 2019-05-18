package com.whyuan.sc.config.server;

import com.whyuan.sc.utils.PortUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 〈配置中心服务端〉
 *
 * @author whyuan
 * @create 201/5/18
 * @since 1.0.0
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableConfigServer //激活配置中心服务
public class ConfigServerApplication {

    public static void main(String[] args) {
        //8888 这个端口是默认的，就不要修改了。
        int port = 8888;
        PortUtil.checkPort(port, "ConfigServer 配置中心服务", false);
        new SpringApplicationBuilder(ConfigServerApplication.class).properties("server.port=" + port).run(args);
    }

}
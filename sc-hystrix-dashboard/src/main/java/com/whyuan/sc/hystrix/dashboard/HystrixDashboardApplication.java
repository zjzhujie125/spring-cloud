package com.whyuan.sc.hystrix.dashboard;

import com.whyuan.sc.utils.PortUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * 〈断路器监控〉
 * http://localhost:5555/hystrix
 *
 * @author whyuan
 * @create 2019/5/18
 * @since 1.0.0
 */
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboardApplication {

    public static void main(String[] args) {
        //5555 这个端口是默认的，就不要修改了。
        int port = 5555;
        PortUtil.checkPort(port, "HystrixDashboard 监控服务", false);
        new SpringApplicationBuilder(HystrixDashboardApplication.class).properties("server.port=" + port).run(args);
    }

}
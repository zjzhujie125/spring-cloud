package com.whyuan.sc.turbine;

import com.whyuan.sc.utils.PortUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * 〈断路器 聚合监控〉
 *
 * @author whyuan
 * @create 2019/5/18
 * @since 1.0.0
 */

@SpringBootApplication
@EnableTurbine //激活聚合监控
public class TurbineApplication {

    public static void main(String[] args) {
        //6666 这个端口是默认的，就不要修改了。
        int port = 6666;
        PortUtil.checkPort(port, "Turbine 聚合监控服务", false);
        new SpringApplicationBuilder(TurbineApplication.class).properties("server.port=" + port).run(args);
    }

}
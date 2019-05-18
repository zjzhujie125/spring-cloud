package com.whyuan.sc.zipkin.server;

import com.whyuan.sc.utils.PortUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import zipkin.server.EnableZipkinServer;

/**
 * 〈Zipkin 服务器启动-Boot2.X版本不兼容，改用Jar启动〉
 *
 * @author whyuan
 * @create 2019/5/17
 * @since 1.0.0
 */
//UI界面:http://localhost:5555/zipkin/
@SpringBootApplication
@EnableZipkinServer //激活Zipkin服务端
public class ZipkinServerApplication {
    public static void main(String[] args) {
        //1111 这个端口是默认的，就不要修改了，后面的子项目，都会访问这个端口。
        int port = 5555;
        PortUtil.checkPort(port, "Zipkin 链路追踪服务", false);
        new SpringApplicationBuilder(ZipkinServerApplication.class).properties("server.port=" + port).run(args);
    }

}
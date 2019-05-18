package com.whyuan.sc.feign.util;

//用于持续访问产品服务，从Hystrix监控面板观察运行指标
public class AccessFeignService {

    public static void main(String[] args) {

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            access(4444);//Feign暴露的4444端口
            access(4445);//Feign暴露的4445端口
            access(4446);//Feign暴露的4446端口
        }

    }

    public static void access(int port) {
        try {
            String html = HTTP.get(String.format("http://127.0.0.1:%d/products", port));
            System.out.printf("%d 地址的产品服务访问成功，返回大小是 %d%n", port, html.length());
        } catch (Exception e) {
            System.err.printf("%d 地址的产品服务无法访问%n", port);
        }
    }
}

package com.whyuan.sc.feign.util;


//用于集成BUS后，通过端点动态刷新配置信息
public class FreshConfigUtil {

    public static void main(String[] args) {
       /* HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json; charset=utf-8");*/
        System.out.println("因为要去git获取，还要刷新config-server, 会比较卡，所以一般会要好几秒才能完成，请耐心等待");

        String result = HTTP.post("http://localhost:4444/actuator/bus-refresh");
        System.out.println("result:" + result);
        System.out.println("refresh 完成");
    }
}
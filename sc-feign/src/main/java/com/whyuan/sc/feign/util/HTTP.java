package com.whyuan.sc.feign.util;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Http请求
 * JAVA自带的URL,HttpURLConnection
 */
public class HTTP {
    static final Logger logger = LoggerFactory.getLogger(HTTP.class);

    //发送get请求，默认编码为UTF-8
    public static final String get(String url) throws IOException {
        return get(url, "UTF-8");
    }

    public static final String get(String url, String charset) throws IOException {
        URL oracle = new URL(url);
        URLConnection connection = oracle.openConnection();
        connection.setConnectTimeout(30000);
        connection.setReadTimeout(30000);

        logger.debug("Http request Url={}, Charset={}", url, charset);
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset != null ? Charset.forName(charset) : Charset.forName("UTF-8")));
        StringBuffer result = new StringBuffer();
        String inputLine = null;
        while ((inputLine = in.readLine()) != null) result.append(inputLine);
        in.close();
        return result.toString();
    }

    //发送postt请求，默认编码为UTF-8
    public static final String post(String url, String body) throws IOException {
        return post(url, body, "UTF-8");
    }

    //发送Post请求
    public static final String post(String url, String body, String charset) throws IOException {
        URL oracle = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) oracle.openConnection();
        connection.setDoOutput(true);
        connection.setUseCaches(false);
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Charset", charset);
        connection.setConnectTimeout(30000);
        connection.setReadTimeout(30000);
        connection.connect();

        BufferedReader ready = null;
        DataOutputStream dop = null;
        StringBuffer result = new StringBuffer();
        try {
            dop = new DataOutputStream(connection.getOutputStream());
            dop.writeBytes(body);
            dop.flush();

            logger.debug("Http request Url={}, Charset={}", url, charset);

            ready = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset != null ? Charset.forName(charset) : Charset.forName("UTF-8")));
            String inputLine = null;
            while ((inputLine = ready.readLine()) != null) result.append(inputLine);
        } finally {
            if (dop != null)
                dop.close();

            if (ready != null)
                ready.close();

        }
        return result.toString();
    }

    //不打印日志信息发送Post请求
    public static final String post(String url) {
        return post(url, false);
    }

    //打印日志信息发送Post请求
    public static final String post(String url, boolean print) {
        if (Strings.isNullOrEmpty(url)) return null;
        //URL参数解码操作
        Map<String, String> params = paramMap(url, "UTF-8");
        if (print) {
            //打印参数的日志信息
            for (String key : params.keySet()) {
                logger.info(key + "=" + params.get(key));
            }
        }
        return post(host(url), paramUrl(params, "UTF-8"), 0);
    }

    //3次重试策略
    public static final String post(String url, String body, int retry) {
        try {
            return post(url, body, "UTF-8");
        } catch (IOException e) {
            //连接失败尝试重新连接
            if (retry >= 3) {
                logger.error("requst retry= " + retry + " , body=" + body, e);
                return null;
            }
            logger.warn("requst retry=" + retry);
            return post(url, body, ++retry);
        }
    }

    //将URL中的?后面的参数转化为Map
    private static final Map<String, String> paramMap(String url, String charset) {
        HashMap<String, String> params = new HashMap<>();

        int flag = url.indexOf("?");
        if (flag != -1) {
            String $ = url.substring(flag + 1);
            String[] ps = $.split("&");
            for (String p : ps) {
                String[] keyval = p.split("=");
                if (keyval.length == 2 && !Strings.isNullOrEmpty(keyval[1])) {
                    try {
                        params.put(keyval[0], URLDecoder.decode(keyval[1], charset));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return params;
    }

    //URL参数编码操作
    private static final String paramUrl(Map<String, String> params, String charset) {
        StringBuffer url = new StringBuffer();
        for (String key : params.keySet())
            try {
                url.append(key).append("=").append(URLEncoder.encode(params.get(key), charset)).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        return url.toString();
    }

    //截取url体
    public static final String host(String url) {
        int s = url.indexOf("?");
        if (s == -1) return url;
        return url.substring(0, s);
    }
}

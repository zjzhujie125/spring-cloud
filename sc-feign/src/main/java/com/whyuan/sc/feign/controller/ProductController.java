package com.whyuan.sc.feign.controller;

import com.whyuan.sc.feign.client.ProductFeignClient;
import com.whyuan.sc.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RefreshScope
public class ProductController {
    @Autowired
    ProductFeignClient productFeignClient;

    //从配置中心服务器获取版本信息
    @Value("${version}")
    String version;


    @RequestMapping("/products")
    public Object products(Model m) {
        List<Product> ps = productFeignClient.listProducts();
        m.addAttribute("ps", ps);
        m.addAttribute("version", version);
        return "products";
    }
}
package com.whyuan.sc.feign.controller;

import com.whyuan.sc.feign.client.ProductFeignClient;
import com.whyuan.sc.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
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


    @RequestMapping("/products")
    public Object products(Model m) {
        List<Product> ps = productFeignClient.listProducts();
        m.addAttribute("ps", ps);
        return "products";
    }
}
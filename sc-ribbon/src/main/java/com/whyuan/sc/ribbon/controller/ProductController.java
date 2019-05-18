package com.whyuan.sc.ribbon.controller;

import com.whyuan.sc.pojo.Product;
import com.whyuan.sc.ribbon.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping("/products")
    public Object products(Model m) {
        Collection<Product> ps = productService.listProducts();
        m.addAttribute("ps", ps);
        //跳转产品展示页面
        return "products";
    }
}
package com.whyuan.sc.ribbon.service.impl;

import com.whyuan.sc.pojo.Product;
import com.whyuan.sc.ribbon.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

/**
 * 〈通过{@link RestTemplate},实现服务调用，负载均衡〉
 *
 * @author whyuan
 * @create 2019/5/17
 * @since 1.0.0
 */
@Service
public class ProductServiceImpl implements ProductService {

    private static final String PRODUCT_SERVER_URL_PREFIX = "http://sc-eureka-client";

    @Autowired
    RestTemplate restTemplate;


    @Override
    public Collection<Product> listProducts() {
        return restTemplate.getForObject(PRODUCT_SERVER_URL_PREFIX + "/products", Collection.class);
    }
}
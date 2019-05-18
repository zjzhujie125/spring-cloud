package com.whyuan.sc.ribbon.service;

import com.whyuan.sc.pojo.Product;

import java.util.Collection;

//产品服务案例
public interface ProductService {
    Collection<Product> listProducts();
}

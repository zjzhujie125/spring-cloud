package com.whyuan.sc.client.service;


import com.whyuan.sc.pojo.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Value("${server.port}")
    String port;

    public List<Product> listProducts() {
        List<Product> ps = new ArrayList<>();
        ps.add(new Product(1, "whyuan product a from port:" + port, 50));
        ps.add(new Product(2, "whyuan product b from port:" + port, 500));
        ps.add(new Product(3, "whyuan product c from port:" + port, 5000));
        return ps;
    }
}

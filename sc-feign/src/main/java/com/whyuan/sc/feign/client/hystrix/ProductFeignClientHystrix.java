package com.whyuan.sc.feign.client.hystrix;

import com.whyuan.sc.feign.client.ProductFeignClient;
import com.whyuan.sc.pojo.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//降级服务
@Service
public class ProductFeignClientHystrix implements ProductFeignClient {

    @Override
    public List<Product> listProducts() {
        List<Product> result = new ArrayList<>();
        result.add(new Product(0, "产品服务不可用", 0));
        return result;
    }

}

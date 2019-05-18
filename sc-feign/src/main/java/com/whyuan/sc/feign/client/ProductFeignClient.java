package com.whyuan.sc.feign.client;

import com.whyuan.sc.feign.client.hystrix.ProductFeignClientHystrix;
import com.whyuan.sc.pojo.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

//接口无具体实现，根据接口生成动态代理包装RestTemplate发起调用
@FeignClient(value = "sc-eureka-client", fallback = ProductFeignClientHystrix.class)
public interface ProductFeignClient {

    @GetMapping("/products")
    List<Product> listProducts();
}

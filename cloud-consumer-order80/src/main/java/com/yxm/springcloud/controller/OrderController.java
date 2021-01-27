package com.yxm.springcloud.controller;

import com.yxm.springcloud.entities.CommonResult;
import com.yxm.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderController {

    //单机版
    //public static final String PROVIDER_UREL = "http://localhost:8002/";
    //集群版
    public static final String PROVIDER_UREL = "http://CLOUD-PAYMENT-SERVICE/";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/provider/create")
    public CommonResult<Payment> create(Payment payment){
        log.info("*********正在操作消费者*********");
        return restTemplate.postForObject(PROVIDER_UREL+"payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/provider/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        log.info("**********正在操作消费者********");
        return restTemplate.getForObject(PROVIDER_UREL+"payment/get/"+id,CommonResult.class);
    }


}

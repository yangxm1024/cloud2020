package com.yxm.springcloud.controller;

import com.yxm.springcloud.entities.CommonResult;
import com.yxm.springcloud.entities.Payment;
import com.yxm.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("********数据插入状态"+result+",serial:"+payment.getSerial());

        if(result>0){
            return new CommonResult(200,"插入成功，serverPort："+serverPort,result);
        }else {
            return new CommonResult(501,"插入失败，serverPort："+serverPort,null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("*********查询数据结果"+payment);

        if(null != payment){
            return new CommonResult(200,"查询成功，serverPort："+serverPort,payment);
        }else {
            return new CommonResult(501,"查询失败，serverPort："+serverPort,null);
        }
    }
}

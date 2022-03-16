package ru.gelvides.currencyservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "${spring.application.name}", url = "${request.cbrf.domain}")
public interface CbrFeignClient {

    @GetMapping("${request.cbrf.all-currency}")
    String getAllCurrency();
}
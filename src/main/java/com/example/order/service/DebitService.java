package com.example.order.service;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.order.domain.debit.DebitRequest;
import com.example.order.domain.debit.DebitResponse;

/**
 * Feign client interface for integration of debit service.
 */
@FeignClient(value = "debit-service")
public interface DebitService {
  
  @RequestMapping(method = POST, path = "/debit")
  DebitResponse debit(DebitRequest request);

}
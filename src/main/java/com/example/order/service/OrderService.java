package com.example.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.order.domain.debit.DebitRequest;
import com.example.order.domain.debit.DebitResponse;
import com.example.order.domain.debit.DebitStatus;
import com.example.order.domain.order.OrderPlacementRequest;
import com.example.order.domain.order.OrderPlacementResult;
import com.example.order.domain.order.OrderStatus;

/**
 * Business logic of order placement, which in turn calls debitService.
 */
@Controller
public class OrderService {

  @Autowired
  private DebitService debitService;

  @PostMapping(value = "/order")
  public OrderPlacementResult placeOrder(OrderPlacementRequest orderPlacementRequest) {
    DebitRequest request = new DebitRequest(orderPlacementRequest.getCustomerId(),
            orderPlacementRequest.getCreditCardNumber(), orderPlacementRequest.getPrice());
    
    DebitResponse response = debitService.debit(request);
    
    return buildResponseFromDebitResult(response);
  }
  
  private OrderPlacementResult buildResponseFromDebitResult(DebitResponse response) {

    OrderStatus applicationStatus = null;
    if (DebitStatus.OK == response.getDebitStatus()) {
      applicationStatus = OrderStatus.SUCCESSFUL;
    } else if (DebitStatus.INVALID == response.getDebitStatus()) {
      applicationStatus = OrderStatus.FAILED;
    }
    
    return new OrderPlacementResult(applicationStatus);
  }
  
}

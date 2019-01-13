package com.example.order.domain.order;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Domain object of order placement requests.
 */
@Data
@AllArgsConstructor
public class OrderPlacementRequest {

    private String customerId;

    private String creditCardNumber;

    private float price;

}

package com.example.order.domain.order;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Domain object of the result of an order placement.
 */
@Data
@AllArgsConstructor
public class OrderPlacementResult {

    private OrderStatus orderStatus;

}

package com.example.order.domain.debit;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Domain object of debit requests.
 */
@Data
@AllArgsConstructor
public class DebitRequest {

    private String customerId;

    private String creditCardNumber;

    private float amount;

}

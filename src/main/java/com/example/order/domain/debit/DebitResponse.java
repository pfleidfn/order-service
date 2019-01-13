package com.example.order.domain.debit;

import lombok.Data;

/**
 * Domain object of the result of a debit placement.
 */
@Data
public class DebitResponse {

    private DebitStatus debitStatus;

    private String rejectionReason;

    private boolean onBlacklist;

}

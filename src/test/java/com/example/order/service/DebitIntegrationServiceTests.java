package com.example.order.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.order.domain.debit.DebitRequest;
import com.example.order.domain.debit.DebitResponse;
import com.example.order.domain.debit.DebitStatus;

/**
 * Test the service integration with external debit service
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureStubRunner
public class DebitIntegrationServiceTests {

    @Autowired
    private DebitService sut;

    @Test
    public void shouldBeOK() {

        // given:
        DebitRequest request = new DebitRequest(
                "florian.pfleiderer", "1234567890123456", 3.50f);

        // when:
        DebitResponse response = sut.debit(request);

        // then:
        assertThat(response.getDebitStatus()).isEqualTo(DebitStatus.OK);
        assertThat(response.getRejectionReason()).isNull();
    }

    @Test
    public void shouldBeInvalidDueToShortCreditCardNumber() {

        // given:
        DebitRequest request = new DebitRequest(
                "florian.pfleiderer", "123456", 3.50f);

        // when:
        DebitResponse response = sut.debit(request);

        // then:
        assertThat(response.getDebitStatus()).isEqualTo(DebitStatus.INVALID);
        assertThat(response.getRejectionReason()).isEqualTo("Credit card number is invalid");
    }

    @Test
    public void shouldBeInvalidDueUserOnBlacklist() {

        // Arrange
        DebitRequest request = new DebitRequest(
                "max.mustermann", "1234567890123456", 3.50f);

        // Act
        DebitResponse response = sut.debit(request);

        // Assert
        assertThat(response.getDebitStatus()).isEqualTo(DebitStatus.INVALID);
        assertThat(response.getRejectionReason()).isEqualTo("Customer is on Blacklist");
        assertThat(response.isOnBlacklist()).isTrue();
    }
}

package com.food.ordering.system.order.service.domain;


import com.food.ordering.system.order.service.domain.dto.message.*;
import com.food.ordering.system.order.service.domain.ports.input.message.listener.payment.PaymentResponseMessageListener;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


@Slf4j
@Validated
@Service
public class PaymentResponseMessageListenerImpl implements PaymentResponseMessageListener {
  @Override
  public void paymentCompleted(final PaymentResponse paymentResponse) {}

  @Override
  public void paymentCalcelled(final PaymentResponse paymentResponse) {}
}

package com.food.ordering.system.order.service.domain.ports.input.message.listener.payment;


import com.food.ordering.system.order.service.domain.dto.message.*;


public interface PaymentResponseMessageListener {
  void paymentCompleted(final PaymentResponse paymentResponse);
  void paymentCalcelled(final PaymentResponse paymentResponse);
}

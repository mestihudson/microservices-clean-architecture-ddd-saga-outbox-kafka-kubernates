package com.food.ordering.system.order.service.domain;


import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.food.ordering.system.order.service.domain.ports.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;

import java.util.*;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;


@Slf4j
@Component
@AllArgsConstructor
public class OrderCreatedEventApplicationListener {
  private final OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher;

  @TransactionalEventListener
  void process(final OrderCreatedEvent orderCreatedEvent) {}
}

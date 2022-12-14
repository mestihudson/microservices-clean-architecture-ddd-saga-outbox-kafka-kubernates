package com.food.ordering.system.order.service.domain;


import com.food.ordering.system.order.service.domain.dto.create.*;
import com.food.ordering.system.order.service.domain.entity.*;
import com.food.ordering.system.order.service.domain.exception.OrderDomainException;
import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.food.ordering.system.order.service.domain.mapper.OrderDataMapper;
import com.food.ordering.system.order.service.domain.ports.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import com.food.ordering.system.order.service.domain.ports.output.repository.*;

import java.util.*;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Component
@AllArgsConstructor
public class OrderCreateCommandHandler {
  private final OrderCreateHelper orderCreateHelper;
  private final OrderDataMapper orderDataMapper;
  private final OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher;

  @Transactional
  public CreateOrderResponse createOrder(final CreateOrderCommand createOrderCommand) {
    final OrderCreatedEvent orderCreatedEvent = orderCreateHelper.persistOrder(createOrderCommand);
    log.info("Order is created with id: {}", orderCreatedEvent.getOrder().getId().getValue());
    orderCreatedPaymentRequestMessagePublisher.publish(orderCreatedEvent);
    return orderDataMapper.orderToCreateOrderResponse(orderCreatedEvent.getOrder());
  }
}

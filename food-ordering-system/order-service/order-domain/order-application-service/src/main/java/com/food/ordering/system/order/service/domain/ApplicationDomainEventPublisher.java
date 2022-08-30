package com.food.ordering.system.order.service.domain;


import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.food.ordering.system.order.service.domain.dto.create.*;
import com.food.ordering.system.order.service.domain.entity.*;
import com.food.ordering.system.order.service.domain.exception.OrderDomainException;
import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.food.ordering.system.order.service.domain.mapper.OrderDataMapper;
import com.food.ordering.system.order.service.domain.ports.output.repository.*;

import java.util.*;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class ApplicationDomainEventPublisher implements
  ApplicationEventPublisherAware, DomainEventPublisher<OrderCreatedEvent> {
  private ApplicationEventPublisher applicationEventPublisher;

  @Override
  public void setApplicationEventPublisher(
    final ApplicationEventPublisher applicationEventPublisher) {
    this.applicationEventPublisher = applicationEventPublisher;
  }

  @Override
  public void publish(final OrderCreatedEvent domainEvent) {
    this.applicationEventPublisher.publishEvent(domainEvent);
    log.info(
      "OrderCreatedEvent is published for order id: {}",
      domainEvent.getOrder().getId().getValue()
    );
  }
}

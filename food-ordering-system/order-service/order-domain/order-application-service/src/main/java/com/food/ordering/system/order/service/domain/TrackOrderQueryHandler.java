package com.food.ordering.system.order.service.domain;


import com.food.ordering.system.order.service.domain.dto.track.*;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.exception.OrderNotFoundException;
import com.food.ordering.system.order.service.domain.mapper.OrderDataMapper;
import com.food.ordering.system.order.service.domain.ports.output.repository.OrderRepository;
import com.food.ordering.system.order.service.domain.valueobject.TrackingId;

import java.util.Optional;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Component
@AllArgsConstructor
public class TrackOrderQueryHandler {
  private final OrderDataMapper orderDataMapper;
  private final OrderRepository orderRepository;

  @Transactional(readOnly = true)
  public TrackOrderResponse trackOrder(final TrackOrderQuery trackOrderQuery) {
    Optional<Order> orderResult = orderRepository
      .findByTrackingId(new TrackingId(trackOrderQuery.getOrderTrackingId()));
    if (orderResult.isEmpty()) {
      log.warn(
        "Could not find order with tracking id: {}",
        trackOrderQuery.getOrderTrackingId()
      );
      throw new OrderNotFoundException(
        "Could not find order with tracking id: %s".formatted(
          trackOrderQuery.getOrderTrackingId()
        )
      );
    }
    return orderDataMapper.orderToTrackOrderResponse(orderResult.get());
  }
}

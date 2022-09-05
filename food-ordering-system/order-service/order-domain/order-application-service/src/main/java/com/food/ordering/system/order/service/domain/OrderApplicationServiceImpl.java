package com.food.ordering.system.order.service.domain;


import com.food.ordering.system.order.service.domain.dto.create.*;
import com.food.ordering.system.order.service.domain.dto.track.*;
import com.food.ordering.system.order.service.domain.ports.input.service.OrderApplicationService;

import javax.validation.Valid;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


@Slf4j
@Validated
@Service
@AllArgsConstructor
class OrderApplicationServiceImpl implements OrderApplicationService {
  private final OrderCreateCommandHandler orderCreateCommandHandler;
  private final OrderTrackQueryHandler orderTrackQueryHandler;

  @Override
  public CreateOrderResponse createOrder(final CreateOrderCommand createOrderCommand) {
    return orderCreateCommandHandler.createOrder(createOrderCommand);
  }

  @Override
  public TrackOrderResponse trackOrder(final TrackOrderQuery trackOrderQuery) {
    return orderTrackQueryHandler.trackOrder(trackOrderQuery);
  }
}

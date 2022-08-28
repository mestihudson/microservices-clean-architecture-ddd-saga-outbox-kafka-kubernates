package com.food.ordering.system.order.service.domain;


import com.food.ordering.system.order.service.domain.dto.create.*;
import com.food.ordering.system.order.service.domain.entity.*;
import com.food.ordering.system.order.service.domain.mapper.OrderDataMapper;
import com.food.ordering.system.order.service.domain.ports.output.repository.*;
import com.food.ordering.system.order.service.domain.exception.OrderDomainException;

import java.util.*;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Component
@AllArgsConstructor
public class CreateOrderCommandHandler {
  private final OrderDomainService orderDomainService;
  private final OrderRepository orderRepository;
  private final CustomerRepository customerRepository;
  private final RestaurantRepository restaurantRepository;
  private final OrderDataMapper orderDataMapper;

  @Transactional
  public CreateOrderResponse createOrder(final CreateOrderCommand createOrderCommand) {
    checkCustomer(createOrderCommand.getCustomerId());
    final Restaurant restaurant = checkRestaurant(createOrderCommand);
    return null;
  }

  private Restaurant checkRestaurant(final CreateOrderCommand createOrderCommand) {
    return null;
  }

  private void checkCustomer(final UUID customerId) {
    final Optional<Customer> customer = customerRepository.findCustomer(customerId);
    if (customer.isEmpty()) {
      log.warn("Could not find customer with customer id: {}", customerId);
      throw new OrderDomainException("Could not find customer with customer id: %s".formatted(customerId));
    }
  }
}

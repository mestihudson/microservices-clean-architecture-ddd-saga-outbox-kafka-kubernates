package com.food.ordering.system.order.service.domain;


import com.food.ordering.system.order.service.domain.entity.*;
import com.food.ordering.system.order.service.domain.event.*;

import java.util.List;


public interface OrderDomainService {
  OrderCreatedEvent validateAndInitiateOrder(final Order order, final Restaurant restaurant);
  OrderPaidEvent payOrder(final Order order);
  void approveOrder(final Order order);
  OrderCancelledEvent cancelOrderPayment(final Order order, final List<String> failureMessages);
  void cancelOrder(final Order order, final List<String> failureMessages);
}

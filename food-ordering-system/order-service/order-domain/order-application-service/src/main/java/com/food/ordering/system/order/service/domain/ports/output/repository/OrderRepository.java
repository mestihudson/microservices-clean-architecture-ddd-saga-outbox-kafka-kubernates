package com.food.ordering.system.order.service.domain.ports.output.repository;


import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.valueobject.TrackingId;

import java.util.Optional;


public interface OrderRepository {
  Order save(final Order order);
  Optional<Order> findByTrackingId(final TrackingId trackingId);
}

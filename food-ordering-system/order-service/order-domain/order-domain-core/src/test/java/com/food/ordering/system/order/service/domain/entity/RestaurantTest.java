package com.food.ordering.system.order.service.domain.entity;


import com.food.ordering.system.domain.valueobject.RestaurantId;

import java.util.UUID;

import org.junit.jupiter.api.Test;


class RestaurantTest {
  @Test void create() {
    Restaurant.builder()
      .active(true)
      .restaurantId(new RestaurantId(UUID.randomUUID()))
      .build();
  }
}

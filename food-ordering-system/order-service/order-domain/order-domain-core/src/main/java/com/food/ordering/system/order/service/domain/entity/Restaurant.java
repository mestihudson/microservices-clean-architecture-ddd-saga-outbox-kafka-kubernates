package com.food.ordering.system.order.service.domain.entity;


import com.food.ordering.system.domain.entity.AggregateRoot;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.order.service.domain.exception.OrderDomainException;
import com.food.ordering.system.order.service.domain.valueobject.*;

import java.util.*;

import lombok.*;
import lombok.experimental.Accessors;


@Getter
public class Restaurant extends AggregateRoot<RestaurantId> {
  private final List<Product> products;
  private boolean active;

  private Restaurant(final RestaurantBuilder builder) {
    super.setId(builder.restaurantId);
    products = builder.products;
    active = builder.active;
  }

  public static RestaurantBuilder builder() {
    return new RestaurantBuilder();
  }

  @Accessors(fluent = true)
  @Setter
  public static final class RestaurantBuilder {
    private RestaurantId restaurantId;
    private List<Product> products;
    private boolean active;

    public Restaurant build() {
      return new Restaurant(this);
    }
  }
}

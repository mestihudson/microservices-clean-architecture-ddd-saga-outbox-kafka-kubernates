package com.food.ordering.system.domain.valueobject;


import java.util.UUID;


public abstract class RestaurantId extends BaseId<UUID> {
  public RestaurantId(final UUID value) {
    super(value);
  }
}

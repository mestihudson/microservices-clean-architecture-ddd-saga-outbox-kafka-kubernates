package com.food.ordering.system.domain.valueobject;


import java.util.UUID;


public abstract class OrderId extends BaseId<UUID> {
  public OrderId(final UUID value) {
    super(value);
  }
}

package com.food.ordering.system.domain.valueobject;


import java.util.UUID;


public abstract class CustomerId extends BaseId<UUID> {
  public CustomerId(final UUID value) {
    super(value);
  }
}

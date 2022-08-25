package com.food.ordering.system.domain.valueobject;


import java.util.UUID;


public abstract class ProductId extends BaseId<UUID> {
  public ProductId(final UUID value) {
    super(value);
  }
}

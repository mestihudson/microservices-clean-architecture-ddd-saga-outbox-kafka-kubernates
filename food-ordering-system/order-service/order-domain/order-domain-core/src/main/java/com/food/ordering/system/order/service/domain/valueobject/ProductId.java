package com.food.ordering.system.order.service.domain.valueobject;


import com.food.ordering.system.domain.valueobject.BaseId;


public class ProductId extends BaseId<Long> {
  public ProductId(final Long value) {
    super(value);
  }
}

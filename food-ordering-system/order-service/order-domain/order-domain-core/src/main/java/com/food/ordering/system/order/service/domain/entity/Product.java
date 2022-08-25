package com.food.ordering.system.order.service.domain.entity;


import com.food.ordering.system.domain.entity.BaseEntity;
import com.food.ordering.system.domain.valueobject.*;
import com.food.ordering.system.order.service.domain.valueobject.ProductId;

import lombok.*;


@Getter
public class Product extends BaseEntity<ProductId> {
  private String name;
  private Money price;

  public Product(final ProductId productId, final String name, final Money price) {
    super.setId(productId);
    this.name = name;
    this.price = price;
  }
}

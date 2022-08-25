package com.food.ordering.system.order.service.domain.entity;


import com.food.ordering.system.domain.entity.BaseEntity;
import com.food.ordering.system.domain.valueobject.*;
import com.food.ordering.system.order.service.domain.valueobject.OrderItemId;

import lombok.*;


@Getter
public class OrderItem extends BaseEntity<OrderItemId> {
  private OrderId orderId;
  private final Product product;
  private final int quantity;
  private final Money price;
  private final Money subTotal;

  private OrderItem(final OrderItemBuilder builder) {
    super.setId(builder.orderItemId);
    product = builder.product;
    quantity = builder.quantity;
    price = builder.price;
    subTotal = builder.subTotal;
  }

  public static OrderItemBuilder builder() {
    return new OrderItemBuilder();
  }

  public static final class OrderItemBuilder {
    private OrderItemId orderItemId;
    private Product product;
    private int quantity;
    private Money price;
    private Money subTotal;

    private OrderItemBuilder() {}

    public OrderItemBuilder orderItemId(final OrderItemId val) {
      orderItemId = val;
      return this;
    }

    public OrderItemBuilder product(final Product val) {
      product = val;
      return this;
    }

    public OrderItemBuilder quantity(final int val) {
      quantity = val;
      return this;
    }

    public OrderItemBuilder price(final Money val) {
      price = val;
      return this;
    }

    public OrderItemBuilder subTotal(final Money val) {
      subTotal = val;
      return this;
    }

    public OrderItem build() {
      return new OrderItem(this);
    }
  }
}

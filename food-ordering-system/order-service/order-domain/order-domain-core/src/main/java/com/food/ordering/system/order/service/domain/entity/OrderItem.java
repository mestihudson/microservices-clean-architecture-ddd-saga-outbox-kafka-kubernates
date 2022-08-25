package com.food.ordering.system.order.service.domain.entity;


import com.food.ordering.system.domain.entity.BaseEntity;
import com.food.ordering.system.domain.valueobject.*;
import com.food.ordering.system.order.service.domain.valueobject.OrderItemId;

import lombok.*;
import lombok.experimental.Accessors;


@Getter
public class OrderItem extends BaseEntity<OrderItemId> {
  private OrderId orderId;
  private final Product product;
  private final int quantity;
  private final Money price;
  private final Money subTotal;

  void initializeOrderItem(final OrderId orderId, final OrderItemId orderItemId) {
    this.orderId = orderId;
    super.setId(orderItemId);
  }

  boolean isPriceValid() {
    return price.isGreaterThanZero() &&
      price.equals(product.getPrice()) &&
      price.multiply(quantity).equals(subTotal);
  }

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

  @NoArgsConstructor
  @Accessors(fluent = true)
  public static final class OrderItemBuilder {
    private OrderItemId orderItemId;
    private Product product;
    private int quantity;
    private Money price;
    private Money subTotal;

    public OrderItem build() {
      return new OrderItem(this);
    }
  }
}

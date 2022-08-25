package com.food.ordering.system.order.service.domain.entity;


import com.food.ordering.system.domain.entity.AggregateRoot;
import com.food.ordering.system.domain.valueobject.*;
import com.food.ordering.system.order.service.domain.exception.OrderDomainException;
import com.food.ordering.system.order.service.domain.valueobject.*;

import java.util.*;

import lombok.*;
import lombok.experimental.Accessors;


@Getter
public class Order extends AggregateRoot<OrderId> {
  private final CustomerId customerId;
  private final RestaurantId restaurantId;
  private final StreetAddress deliveryAddress;
  private final Money price;
  private final List<OrderItem> items;

  private TrackingId trackingId;
  private OrderStatus orderStatus;
  private List<String> failureMessages;

  public void initializeOrder() {
    setId(new OrderId(UUID.randomUUID()));
    trackingId = new TrackingId(UUID.randomUUID());
    orderStatus = OrderStatus.PENDING;
    initializeOrderItems();
  }

  public void validateOrder() {
    validateInitialOrder();
    validateTotalPrice();
    validateItemsPrice();
  }

  private void validateInitialOrder() {
    if (orderStatus != null || getId() != null) {
      throw new OrderDomainException("Order is not in correct state for initialization!");
    }
  }

  private void validateTotalPrice() {
    if (price == null || !price.isGreaterThanZero()) {
      throw new OrderDomainException("Total price must be greater than zero!");
    }
  }

  private void validateItemsPrice() {
    final Money orderItemsTotal = items.stream().map(orderItem -> {
      validateItemPrice(orderItem);
      return orderItem.getSubTotal();
    }).reduce(Money.ZERO, Money::add);

    if (!price.equals(orderItemsTotal)) {
      throw new OrderDomainException(
        "Total price: %s is not equal to Order items total: %s!".formatted(
          price.getAmount(),
          orderItemsTotal.getAmount()
        )
      );
    }
  }

  private void validateItemPrice(final OrderItem orderItem) {
    if (!orderItem.isPriceValid()) {
      throw new OrderDomainException(
        "Order item price: %s is not valid for product: %s!".formatted(
          orderItem.getPrice().getAmount(),
          orderItem.getProduct().getId().getValue()
        )
      );
    }
  }

  private void initializeOrderItems() {
    long itemId = 1;
    for (OrderItem orderItem: items) {
      orderItem.initializeOrderItem(super.getId(), new OrderItemId(itemId++));
    }
  }

  private Order(final OrderBuilder builder) {
    super.setId(builder.orderId);
    customerId = builder.customerId;
    restaurantId = builder.restaurantId;
    deliveryAddress = builder.deliveryAddress;
    price = builder.price;
    items = builder.items;
    trackingId = builder.trackingId;
    orderStatus = builder.orderStatus;
    failureMessages = builder.failureMessages;
  }

  public static OrderBuilder builder() {
    return new OrderBuilder();
  }

  @NoArgsConstructor
  @Accessors(fluent = true)
  public static final class OrderBuilder {
    private OrderId orderId;
    private CustomerId customerId;
    private RestaurantId restaurantId;
    private StreetAddress deliveryAddress;
    private Money price;
    private List<OrderItem> items;
    private TrackingId trackingId;
    private OrderStatus orderStatus;
    private List<String> failureMessages;

    public Order build() {
      return new Order(this);
    }

  }
}

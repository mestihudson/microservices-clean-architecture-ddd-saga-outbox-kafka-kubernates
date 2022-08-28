package com.food.ordering.system.order.service.domain.mapper;


import com.food.ordering.system.domain.valueobject.ProductId;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.entity.Product;
import com.food.ordering.system.order.service.domain.entity.Restaurant;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;


@Component
public class OrderDataMapper {
  public Restaurant createOrderCommandToRestaurant(final CreateOrderCommand createOrderCommand) {
    return Restaurant.builder()
      .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
      .products(
        createOrderCommand.getItems().stream()
          .map(orderItem -> new Product(new ProductId(orderItem.getProductId())))
          .collect(Collectors.toList())
      )
      .build();
  }
}

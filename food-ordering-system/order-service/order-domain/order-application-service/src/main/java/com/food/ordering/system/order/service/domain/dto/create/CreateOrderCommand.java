package com.food.ordering.system.order.service.domain.dto.create;


import java.math.BigDecimal;
import java.util.*;

import javax.validation.constraints.NotNull;

import lombok.*;


@Getter
@Builder
@AllArgsConstructor
public class CreateOrderCommand {
  @NotNull
  private final UUID custumerId;
  @NotNull
  private final UUID restaurantId;
  @NotNull
  private final BigDecimal price;
  @NotNull
  private final List<OrderItem> items;
  @NotNull
  private final OrderAddress address;
}

package com.food.ordering.system.order.service.domain.dto.create;


import com.food.ordering.system.domain.valueobject.OrderStatus;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import lombok.*;


@Getter
@Builder
@AllArgsConstructor
public class CreateOrderResponse {
  @NotNull
  private final UUID orderTrackingtId;
  @NotNull
  private final OrderStatus orderStatus;
  @NotNull
  private final String message;
}

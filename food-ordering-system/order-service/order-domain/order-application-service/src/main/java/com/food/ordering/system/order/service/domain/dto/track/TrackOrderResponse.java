package com.food.ordering.system.order.service.domain.dto.track;


import com.food.ordering.system.domain.valueobject.OrderStatus;

import java.util.*;

import javax.validation.constraints.NotNull;

import lombok.*;


@Getter
@Builder
@AllArgsConstructor
public class TrackOrderResponse {
  @NotNull
  private final UUID orderTrackingId;
  @NotNull
  private final OrderStatus orderStatus;
  private final List<String> failureMessages;
}

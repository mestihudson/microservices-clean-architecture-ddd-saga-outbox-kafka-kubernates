package com.food.ordering.system.order.service.domain.dto.message;


import com.food.ordering.system.domain.valueobject.OrderApprovalStatus;

import java.time.Instant;
import java.util.List;

import lombok.*;


@Getter
@Builder
@AllArgsConstructor
public class RestaurantApprovalResponse {
  private final String id;
  private final String sagaId;
  private final String orderId;
  private final String restaurantId;
  private final Instant createdAt;
  private final OrderApprovalStatus orderApprovalStatus;
  private final List<String> failureMessages;
}

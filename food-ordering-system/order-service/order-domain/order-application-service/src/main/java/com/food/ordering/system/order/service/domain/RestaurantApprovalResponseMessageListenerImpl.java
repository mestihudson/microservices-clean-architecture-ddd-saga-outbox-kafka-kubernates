package com.food.ordering.system.order.service.domain;


import com.food.ordering.system.order.service.domain.dto.message.*;
import com.food.ordering.system.order.service.domain.ports.input.message.listener.restaurantapproval.RestaurantApprovalResponseMessageListener;


public class RestaurantApprovalResponseMessageListenerImpl implements RestaurantApprovalResponseMessageListener {
  @Override
  public void orderApproved(final RestaurantApprovalResponse restaurantApprovalResponse) {}

  @Override
  public void orderRejected(final RestaurantApprovalResponse restaurantApprovalResponse) {}
}

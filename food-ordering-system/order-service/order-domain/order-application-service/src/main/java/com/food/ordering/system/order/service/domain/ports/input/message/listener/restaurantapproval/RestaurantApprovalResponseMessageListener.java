package com.food.ordering.system.order.service.domain.ports.input.message.listener.restaurantapproval;


import com.food.ordering.system.order.service.domain.dto.message.*;


public interface RestaurantApprovalResponseMessageListener {
  void orderApproved(final RestaurantApprovalResponse restaurantApprovalResponse);
  void orderRejected(final RestaurantApprovalResponse restaurantApprovalResponse);
}

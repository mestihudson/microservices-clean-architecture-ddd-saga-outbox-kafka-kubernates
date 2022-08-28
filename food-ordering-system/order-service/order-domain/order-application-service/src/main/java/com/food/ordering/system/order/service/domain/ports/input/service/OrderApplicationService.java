package com.food.ordering.system.order.service.domain.ports.input.service;


import com.food.ordering.system.order.service.domain.dto.create.*;
import com.food.ordering.system.order.service.domain.dto.track.*;

import javax.validation.Valid;


public interface OrderApplicationService {
  CreateOrderResponse createOrder(@Valid final CreateOrderCommand createOrderCommand);
  TrackOrderResponse trackOrder(@Valid final TrackOrderQuery trackOrderQuery);
}

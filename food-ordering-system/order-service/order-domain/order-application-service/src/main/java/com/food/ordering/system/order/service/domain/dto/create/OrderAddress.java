package com.food.ordering.system.order.service.domain.dto.create;


import javax.validation.constraints.*;

import lombok.*;


@Getter
@Builder
@AllArgsConstructor
public class OrderAddress {
  @NotNull
  @Max(value = 50)
  private final String street;
  @NotNull
  @Max(value = 10)
  private final String postalCode;
  @NotNull
  @Max(value = 50)
  private final String city;
}

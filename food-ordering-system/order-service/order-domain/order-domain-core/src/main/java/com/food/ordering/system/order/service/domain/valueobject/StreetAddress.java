package com.food.ordering.system.order.service.domain.valueobject;


import java.util.UUID;

import lombok.*;


@Getter
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class StreetAddress {
  @EqualsAndHashCode.Exclude
  private final UUID id;
  private final String street;
  private final String postalCode;
  private final String city;
}

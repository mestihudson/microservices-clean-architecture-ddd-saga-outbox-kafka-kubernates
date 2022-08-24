package com.food.ordering.system.domain.valueobject;


import lombok.*;

import java.math.BigDecimal;


@Getter
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Money {
  private final BigDecimal amount;
}

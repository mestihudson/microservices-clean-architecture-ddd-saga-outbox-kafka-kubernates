package com.food.ordering.system.domain.valueobject;


import lombok.*;


@Getter
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseId<T> {
  private final T value;
}

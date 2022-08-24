package com.food.ordering.system.domain.entity;


import lombok.*;


@Getter
@Setter
@EqualsAndHashCode
public abstract class BaseEntity<ID> {
  private ID id;
}

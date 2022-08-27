package com.food.ordering.system.order.service.domain.entity;


import com.food.ordering.system.domain.entity.AggregateRoot;
import com.food.ordering.system.domain.valueobject.*;
import com.food.ordering.system.order.service.domain.exception.OrderDomainException;
import com.food.ordering.system.order.service.domain.valueobject.*;

import java.util.*;

import lombok.*;
import lombok.experimental.Accessors;


@Getter
public class Customer extends AggregateRoot<CustomerId> {
}

package com.food.ordering.system.order.service.domain.exception;


import com.food.ordering.system.domain.exception.DomainException;


public class OrderNotFoundException extends DomainException {
  public OrderNotFoundException(final String message) {
    super(message);
  }

  public OrderNotFoundException(final String message, final Throwable cause) {
    super(message, cause);
  }
}

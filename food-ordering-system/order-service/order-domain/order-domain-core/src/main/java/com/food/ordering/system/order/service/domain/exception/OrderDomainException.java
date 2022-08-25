package com.food.ordering.system.order.service.domain.exception;


import com.food.ordering.system.domain.exception.DomainException;


public class OrderDomainException extends DomainException {
  public OrderDomainException(final String message) {
    super(message);
  }

  public OrderDomainException(final String message, final Throwable cause) {
    super(message, cause);
  }
}

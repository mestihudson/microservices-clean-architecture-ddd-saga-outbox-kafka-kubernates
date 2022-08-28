package com.food.ordering.system.order.service.domain.ports.output.repository;


import com.food.ordering.system.order.service.domain.entity.Customer;

import java.util.*;


public interface CustomerRepository {
  Optional<Customer> findCustomer(final UUID customerId);
}

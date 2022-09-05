package com.food.ordering.system.order.service.domain;


import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.domain.valueobject.OrderStatus;
import com.food.ordering.system.domain.valueobject.ProductId;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.dto.create.OrderAddress;
import com.food.ordering.system.order.service.domain.dto.create.OrderItem;
import com.food.ordering.system.order.service.domain.entity.Customer;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.entity.Product;
import com.food.ordering.system.order.service.domain.entity.Restaurant;
import com.food.ordering.system.order.service.domain.mapper.OrderDataMapper;
import com.food.ordering.system.order.service.domain.ports.input.service.OrderApplicationService;
import com.food.ordering.system.order.service.domain.ports.output.repository.CustomerRepository;
import com.food.ordering.system.order.service.domain.ports.output.repository.OrderRepository;
import com.food.ordering.system.order.service.domain.ports.output.repository.RestaurantRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = OrderTestConfiguration.class)
public class OrderApplicationServiceTest {
  @Autowired
  private OrderApplicationService orderApplicationService;
  @Autowired
  private OrderDataMapper orderDataMapper;
  @Autowired
  private OrderRepository orderRepository;
  @Autowired
  private CustomerRepository customerRepository;
  @Autowired
  private RestaurantRepository restaurantRepository;

  private CreateOrderCommand createOrderCommand;
  private CreateOrderCommand createOrderCommandWrongPrice;
  private CreateOrderCommand createOrderCommandWrongProductPrice;
  private final UUID CUSTOMER_ID = UUID.fromString("44ee754e-664c-49c5-80b3-f7dda98a2919");
  private final UUID RESTAURANT_ID = UUID.fromString("9bbc6904-45cc-4b4e-8a71-3b9094bf2da7");
  private final UUID PRODUCT_ID = UUID.fromString("f1c46ad0-6e26-484c-967f-951b44a924b6");
  private final UUID ORDER_ID = UUID.fromString("45092628-c497-449a-9ec6-cb40a7f0e98a");
  private final BigDecimal PRICE = new BigDecimal("200.00");

  @BeforeAll
  public void init() {
    createOrderCommand = CreateOrderCommand.builder()
      .customerId(CUSTOMER_ID)
      .restaurantId(RESTAURANT_ID)
      .address(
        OrderAddress.builder()
          .street("street_1")
          .postalCode("1000AB")
          .city("Paris")
          .build()
      )
      .price(PRICE)
      .items(
        List.of(
          OrderItem.builder()
            .productId(PRODUCT_ID)
            .quantity(1)
            .price(new BigDecimal("50.00"))
            .subTotal(new BigDecimal("50.00"))
            .build(),
          OrderItem.builder()
            .productId(PRODUCT_ID)
            .quantity(3)
            .price(new BigDecimal("50.00"))
            .subTotal(new BigDecimal("150.00"))
            .build()
        )
      )
      .build();
    createOrderCommandWrongPrice = CreateOrderCommand.builder()
      .customerId(CUSTOMER_ID)
      .restaurantId(RESTAURANT_ID)
      .address(
        OrderAddress.builder()
          .street("street_1")
          .postalCode("1000AB")
          .city("Paris")
          .build()
      )
      .price(new BigDecimal("250.00"))
      .items(
        List.of(
          OrderItem.builder()
            .productId(PRODUCT_ID)
            .quantity(1)
            .price(new BigDecimal("50.00"))
            .subTotal(new BigDecimal("50.00"))
            .build(),
          OrderItem.builder()
            .productId(PRODUCT_ID)
            .quantity(3)
            .price(new BigDecimal("50.00"))
            .subTotal(new BigDecimal("150.00"))
            .build()
        )
      )
      .build();
    createOrderCommandWrongProductPrice = CreateOrderCommand.builder()
      .customerId(CUSTOMER_ID)
      .restaurantId(RESTAURANT_ID)
      .address(
        OrderAddress.builder()
          .street("street_1")
          .postalCode("1000AB")
          .city("Paris")
          .build()
      )
      .price(new BigDecimal("210.00"))
      .items(
        List.of(
          OrderItem.builder()
            .productId(PRODUCT_ID)
            .quantity(1)
            .price(new BigDecimal("60.00"))
            .subTotal(new BigDecimal("60.00"))
            .build(),
          OrderItem.builder()
            .productId(PRODUCT_ID)
            .quantity(3)
            .price(new BigDecimal("50.00"))
            .subTotal(new BigDecimal("150.00"))
            .build()
        )
      )
      .build();

    final Customer customer = new Customer();
    customer.setId(new CustomerId(CUSTOMER_ID));

    final Restaurant restaurantResponse = Restaurant.builder()
      .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
      .products(
          List.of(
            new Product(new ProductId(PRODUCT_ID), "product-1", new Money(new BigDecimal("50.00"))),
            new Product(new ProductId(PRODUCT_ID), "product-2", new Money(new BigDecimal("50.00")))
          )
      )
      .active(true)
      .build();

    final Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand);
    order.setId(new OrderId(ORDER_ID));

    when(customerRepository.findCustomer(CUSTOMER_ID)).thenReturn(Optional.of(customer));
    when(restaurantRepository.findRestaurantInformation(orderDataMapper.createOrderCommandToRestaurant(createOrderCommand)))
      .thenReturn(Optional.of(restaurantResponse));
    when(orderRepository.save(any(Order.class))).thenReturn(order);
  }

  @Test
  public void testCreateOrder() {
    final CreateOrderResponse createOrderResponse = orderApplicationService.createOrder(createOrderCommand);
    assertThat(createOrderResponse.getOrderStatus()).isEqualTo(OrderStatus.PENDING);
    assertThat(createOrderResponse.getMessage()).isEqualTo("Order created successfully");
    assertThat(createOrderResponse.getOrderTrackingId()).isNotNull();
  }
}

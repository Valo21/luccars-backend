package com.valentinfaciano.luccars.features.order;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.valentinfaciano.luccars.features.order.dto.CreateOrderDTO;
import com.valentinfaciano.luccars.features.order.dto.ItemDTO;
import com.valentinfaciano.luccars.features.order.entity.Order;
import com.valentinfaciano.luccars.features.order.entity.OrderItem;
import com.valentinfaciano.luccars.features.order.exceptions.NotEnoughStockException;
import com.valentinfaciano.luccars.features.product.ProductRepository;
import com.valentinfaciano.luccars.features.product.entity.Product;
import com.valentinfaciano.luccars.features.product.exceptions.ProductNotFoundException;
import com.valentinfaciano.luccars.features.user.UserProfileService;
import com.valentinfaciano.luccars.features.user.UserService;
import com.valentinfaciano.luccars.features.user.entity.User;
import com.valentinfaciano.luccars.features.user.entity.UserProfile;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
  private final OrderRepository orderRepository;
  private final UserService userService;
  private final UserProfileService userProfileService;
  private final ProductRepository productRepository;

  public Order create(UUID userId, CreateOrderDTO dto) {

    User user = userService.findById(userId);
    UserProfile profile = userProfileService.findByUser(user);

    Order order = Order.builder()
        .user(user)
        .shippingAddress(profile.getAddress())
        .shippingCity(profile.getCity())
        .shippingCountry(profile.getCountry())
        .shippingPostalCode(profile.getPostalCode())
        .shippingCost(calculateShippingCost(profile.getAddress()))
        .build();

    dto.getItems().forEach(itemDTO -> {
      Product product = productRepository.findById(itemDTO.getProductId())
          .orElseThrow(() -> new ProductNotFoundException("Product not found"));

      if (itemDTO.getQuantity() > product.getStock()) {
        throw new NotEnoughStockException("Not enough stock");
      }

      product.setStock(product.getStock() - itemDTO.getQuantity());

      OrderItem item = OrderItem.builder()
          .product(product)
          .unitPrice(product.getPrice())
          .quantity(itemDTO.getQuantity())
          .build();
      item.setTotalPrice(calculateItemTotal(item));
      order.getItems().add(item);
    });

    order.setTotal(calculateOrderTotal(order.getItems()));

    Order savedOrder = orderRepository.save(order);

    productRepository.saveAll(
        order.getItems().stream()
            .map(OrderItem::getProduct)
            .toList());

    return savedOrder;
  }

  private BigDecimal calculateItemTotal(OrderItem item) {
    return item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
  }

  private BigDecimal calculateOrderTotal(List<OrderItem> items) {
    return items.stream()
        .map(OrderItem::getTotalPrice)
        .filter(Objects::nonNull)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  private BigDecimal calculateShippingCost(String address) {
    return new BigDecimal(0); // TODO: calcualte shipping cost
  }

}

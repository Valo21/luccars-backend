package com.valentinfaciano.luccars.features.order;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.valentinfaciano.luccars.features.order.dto.CreateOrderDTO;
import com.valentinfaciano.luccars.features.order.dto.OrderItemResponseDTO;
import com.valentinfaciano.luccars.features.order.dto.OrderResponseDTO;
import com.valentinfaciano.luccars.features.order.entity.Order;
import com.valentinfaciano.luccars.features.order.entity.OrderItem;
import com.valentinfaciano.luccars.features.user.entity.User;
import com.valentinfaciano.luccars.shared.dto.AppResponseDTO;
import com.valentinfaciano.luccars.shared.dto.ResponseHelper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
  private final OrderService orderService;

  @PostMapping
  public ResponseEntity<AppResponseDTO<OrderResponseDTO>> createOrder(@AuthenticationPrincipal User user,
      @Valid @RequestBody CreateOrderDTO createOrderDTO) {
    Order order = orderService.create(user.getId(), createOrderDTO);
    return ResponseHelper.success(toResponse(order), "Order created successfully");
  }

  public OrderResponseDTO toResponse(Order order) {
    OrderResponseDTO response = OrderResponseDTO.builder()
        .id(order.getId())
        .total(order.getTotal())
        .shippingCost(order.getShippingCost())
        .items(order.getItems().stream()
            .map(this::mapToItemResponse)
            .toList())
        .createdAt(order.getCreatedAt())
        .build();
    return response;
  }

  private OrderItemResponseDTO mapToItemResponse(OrderItem item) {
    return OrderItemResponseDTO.builder()
        .productId(item.getProduct().getId())
        .productName(item.getProduct().getName())
        .quantity(item.getQuantity())
        .unitPrice(item.getUnitPrice())
        .totalPrice(item.getTotalPrice())
        .build();
  }

}

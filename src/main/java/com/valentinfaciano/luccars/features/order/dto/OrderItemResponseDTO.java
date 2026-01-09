package com.valentinfaciano.luccars.features.order.dto;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponseDTO {
  private UUID productId;
  private String productName;
  private Integer quantity;
  private BigDecimal unitPrice;
  private BigDecimal totalPrice;
}

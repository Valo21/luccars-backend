package com.valentinfaciano.luccars.features.order.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {
    private UUID id;
    private BigDecimal total;
    private BigDecimal shippingCost;
    private List<OrderItemResponseDTO> items;
    private Date createdAt;
}
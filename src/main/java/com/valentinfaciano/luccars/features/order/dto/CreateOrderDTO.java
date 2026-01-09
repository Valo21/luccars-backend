package com.valentinfaciano.luccars.features.order.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateOrderDTO {
  @NotNull
  private List<ItemDTO> items;
}

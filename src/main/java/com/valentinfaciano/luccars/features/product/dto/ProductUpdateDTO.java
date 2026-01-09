package com.valentinfaciano.luccars.features.product.dto;

import com.valentinfaciano.luccars.features.product.enums.ProductCategory;
import com.valentinfaciano.luccars.features.vehicle.enums.VehicleType;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class ProductUpdateDTO {

  private String name;

  @Min(value = 0, message = "Price must be greater than or equal to 0")
  private Float price;

  @Min(value = 0, message = "Stock must be greater than or equal to 0")
  private Integer stock;

  private String description;

  private String brand;

  private ProductCategory category;

  private VehicleType vehicleType;

  @Min(value = 0, message = "Volume must be positive")
  private Float volume;

  private Boolean isActive;

  private String imageUrl;
}
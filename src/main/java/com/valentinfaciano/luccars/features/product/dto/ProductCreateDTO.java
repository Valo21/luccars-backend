package com.valentinfaciano.luccars.features.product.dto;

import com.valentinfaciano.luccars.features.product.enums.ProductCategory;
import com.valentinfaciano.luccars.features.vehicle.enums.VehicleType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductCreateDTO {

  @NotBlank(message = "Product name is required")
  private String name;

  @NotNull(message = "Price is required")
  @Min(value = 0, message = "Price must be greater than or equal to 0")
  private Float price;

  @NotNull(message = "Stock is required")
  @Min(value = 0, message = "Stock must be greater than or equal to 0")
  private Integer stock;

  private String description;

  private String brand;

  private ProductCategory category;

  private VehicleType vehicleType;

  @Min(value = 0, message = "Volume must be positive")
  private Float volume;

  @NotBlank(message = "Image URL is required")
  private String imageUrl;
}

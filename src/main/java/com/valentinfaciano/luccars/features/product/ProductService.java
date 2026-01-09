package com.valentinfaciano.luccars.features.product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

import org.springframework.stereotype.Service;

import com.valentinfaciano.luccars.features.product.dto.ProductCreateDTO;
import com.valentinfaciano.luccars.features.product.dto.ProductUpdateDTO;
import com.valentinfaciano.luccars.features.product.entity.Product;
import com.valentinfaciano.luccars.features.product.exceptions.ProductNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
  private final ProductRepository productRepository;

  public List<Product> findAll() {
    return productRepository.findAll();
  }

  public Product findById(UUID productId) {
    Optional<Product> product = productRepository.findById(productId);
    if (product.isEmpty()) {
      throw new ProductNotFoundException("Product with ID " + productId + " not found");
    }
    return product.get();
  }

  public Product create(ProductCreateDTO productCreateDTO) {
    Product product = Product.builder()
        .name(productCreateDTO.getName())
        .price(productCreateDTO.getPrice())
        .stock(productCreateDTO.getStock())
        .brand(productCreateDTO.getBrand())
        .description(productCreateDTO.getDescription())
        .category(productCreateDTO.getCategory())
        .volume(productCreateDTO.getVolume())
        .imageUrl(productCreateDTO.getImageUrl())
        .build();
    return productRepository.save(product);
  }

  public Product update(UUID productId, ProductUpdateDTO productUpdateDTO) {
    Product product = findById(productId);

    updateIfNotNull(productUpdateDTO.getName(), product::setName);
    updateIfNotNull(productUpdateDTO.getPrice(), product::setPrice);
    updateIfNotNull(productUpdateDTO.getStock(), product::setStock);
    updateIfNotNull(productUpdateDTO.getDescription(), product::setDescription);
    updateIfNotNull(productUpdateDTO.getBrand(), product::setBrand);
    updateIfNotNull(productUpdateDTO.getCategory(), product::setCategory);
    updateIfNotNull(productUpdateDTO.getVehicleType(), product::setVehicleType);
    updateIfNotNull(productUpdateDTO.getVolume(), product::setVolume);
    updateIfNotNull(productUpdateDTO.getImageUrl(), product::setImageUrl);
    updateIfNotNull(productUpdateDTO.getIsActive(), product::setIsActive);

    return productRepository.save(product);
  }

  public Boolean delete(UUID productId) {
    Product product = findById(productId);
    productRepository.delete(product);
    return true;
  }

  private <T> void updateIfNotNull(T value, Consumer<T> setter) {
    if (value != null) {
      setter.accept(value);
    }
  }
}

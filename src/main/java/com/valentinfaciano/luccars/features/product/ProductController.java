package com.valentinfaciano.luccars.features.product;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.valentinfaciano.luccars.features.product.dto.ProductCreateDTO;
import com.valentinfaciano.luccars.features.product.dto.ProductUpdateDTO;
import com.valentinfaciano.luccars.features.product.entity.Product;
import com.valentinfaciano.luccars.shared.dto.AppResponseDTO;

import com.valentinfaciano.luccars.shared.dto.ResponseHelper;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
  private final ProductService productService;

  @GetMapping()
  public ResponseEntity<AppResponseDTO<List<Product>>> getProducts() {
    List<Product> products = productService.findAll();
    return ResponseHelper.success(products, "Found products");
  }

  @PostMapping()
  public ResponseEntity<AppResponseDTO<Product>> createProduct(@Valid @RequestBody ProductCreateDTO productCreateDTO,
      HttpServletResponse response) {
    Product product = productService.create(productCreateDTO);
    return ResponseHelper.success(product, "Product created successfully");
  }

  @DeleteMapping
  public ResponseEntity<AppResponseDTO<Boolean>> deleteMany(@RequestBody List<UUID> productIds) {
    Boolean deleted = productService.deleteMany(productIds);
    return ResponseHelper.success(deleted, "Products deleted successfully");
  }

  @GetMapping("/{id}")
  public ResponseEntity<AppResponseDTO<Product>> deleteProduct(@PathVariable UUID id) {
    Product product = productService.findById(id);
    return ResponseHelper.success(product, "Found product");
  }

  @PutMapping("/{id}")
  public ResponseEntity<AppResponseDTO<Product>> updateProduct(@PathVariable UUID id,
      @RequestBody ProductUpdateDTO entity) {
    Product product = productService.update(id, entity);
    return ResponseHelper.success(product, "Product updated successfully");
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<AppResponseDTO<Boolean>> deleteProduct(@PathVariable UUID id,
      @RequestBody ProductUpdateDTO entity) {
    Boolean deleted = productService.delete(id);
    return ResponseHelper.success(deleted, "Product deleted successfully");
  }
}
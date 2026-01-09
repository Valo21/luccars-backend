package com.valentinfaciano.luccars.features.product;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.valentinfaciano.luccars.features.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, UUID> {

}

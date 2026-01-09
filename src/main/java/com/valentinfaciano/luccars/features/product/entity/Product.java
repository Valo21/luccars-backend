package com.valentinfaciano.luccars.features.product.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.valentinfaciano.luccars.features.product.enums.ProductCategory;
import com.valentinfaciano.luccars.features.vehicle.enums.VehicleType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "products")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    @Builder.Default
    private Integer stock = 0;

    @Column(length = 1000)
    private String description;

    @Column(length = 100)
    private String brand;

    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    private Float volume;

    private Float rating;

    @Builder.Default
    private Boolean isActive = true;

    @Column(length = 500)
    private String imageUrl;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;
}

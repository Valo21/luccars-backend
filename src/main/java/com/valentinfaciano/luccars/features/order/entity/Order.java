package com.valentinfaciano.luccars.features.order.entity;

import java.util.UUID;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.valentinfaciano.luccars.features.order.enums.OrderStatus;
import com.valentinfaciano.luccars.features.order.enums.PaymentMethod;
import com.valentinfaciano.luccars.features.order.enums.PaymentStatus;
import com.valentinfaciano.luccars.features.user.entity.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
  @Builder.Default
  private List<OrderItem> items = new ArrayList<>();

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 30)
  @Builder.Default
  private OrderStatus status = OrderStatus.PENDING;

  @Column(nullable = false)
  private BigDecimal total;

  @Column(nullable = false)
  private BigDecimal shippingCost;

  @Column(nullable = false)
  private String shippingAddress;
  @Column(nullable = false)
  private String shippingCity;
  @Column(nullable = false)
  private String shippingPostalCode;
  @Column(nullable = false)
  private String shippingCountry;

  @Enumerated(EnumType.STRING)
  private PaymentMethod paymentMethod;

  @Enumerated(EnumType.STRING)
  @Builder.Default
  private PaymentStatus paymentStatus = PaymentStatus.PENDING;

  private String paymentTransactionId;

  @CreationTimestamp
  private Date createdAt;

  @UpdateTimestamp
  private Date updatedAt;
}
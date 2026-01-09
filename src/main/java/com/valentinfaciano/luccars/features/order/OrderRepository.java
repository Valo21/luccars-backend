package com.valentinfaciano.luccars.features.order;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.valentinfaciano.luccars.features.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, UUID> {

}

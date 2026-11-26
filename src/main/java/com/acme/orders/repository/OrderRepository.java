package com.acme.orders.repository;

import com.acme.orders.domain.Order;
import com.acme.orders.domain.OrderItem;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomerIdOrderByCreatedAtDesc(Long customerId);

    /** Loads all items of the order together with their products in one query (avoids N+1). */
    @Query("select i from OrderItem i join fetch i.product where i.order = :order")
    List<OrderItem> findItemsWithProductByOrder(@Param("order") Order order);

    /** Loads the order with a row lock, so concurrent cancellations are serialized. */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Order> findWithLockById(Long id);
}

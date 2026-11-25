package com.acme.orders.web.dto;

import com.acme.orders.domain.Order;
import com.acme.orders.domain.OrderStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record OrderResponse(
        Long id,
        Long customerId,
        OrderStatus status,
        Instant createdAt,
        List<Item> items,
        String couponCode,
        BigDecimal subtotal,
        BigDecimal discount,
        BigDecimal total
) {

    public record Item(Long productId, String sku, int quantity, BigDecimal unitPrice, BigDecimal lineTotal) {
    }

    public static OrderResponse from(Order order) {
        List<Item> items = order.getItems().stream()
                .map(i -> new Item(
                        i.getProduct().getId(),
                        i.getProduct().getSku(),
                        i.getQuantity(),
                        i.getUnitPrice(),
                        i.lineTotal()))
                .toList();

        BigDecimal subtotal = order.subtotal();
        BigDecimal total = subtotal.subtract(order.getDiscount());

        return new OrderResponse(
                order.getId(),
                order.getCustomerId(),
                order.getStatus(),
                order.getCreatedAt(),
                items,
                order.getCoupon() != null ? order.getCoupon().getCode() : null,
                subtotal,
                order.getDiscount(),
                total);
    }
}

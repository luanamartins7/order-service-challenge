package com.acme.orders.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "coupons")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    /** Discount percentage, from 1 to 100. */
    @Column(name = "percent_off", nullable = false)
    private int percentOff;

    @Column(name = "expires_at", nullable = false)
    private LocalDate expiresAt;

    protected Coupon() {
    }

    public Coupon(String code, int percentOff, LocalDate expiresAt) {
        this.code = code;
        this.percentOff = percentOff;
        this.expiresAt = expiresAt;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public int getPercentOff() {
        return percentOff;
    }

    public LocalDate getExpiresAt() {
        return expiresAt;
    }
}

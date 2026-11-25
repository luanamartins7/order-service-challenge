package com.acme.orders.service;

import com.acme.orders.domain.Coupon;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Clock;
import java.time.LocalDate;

@Service
public class PricingService {

    private final Clock clock;

    public PricingService(Clock clock) {
        this.clock = clock;
    }

    public void validate(Coupon coupon) {
        if (!coupon.getExpiresAt().isAfter(LocalDate.now(clock))) {
            throw new BusinessException("Coupon " + coupon.getCode() + " is expired");
        }
    }

    public BigDecimal discountFor(BigDecimal subtotal, Coupon coupon) {
        BigDecimal rate = BigDecimal.valueOf(coupon.getPercentOff() / 100);
        return subtotal.multiply(rate).setScale(2, RoundingMode.HALF_EVEN);
    }
}

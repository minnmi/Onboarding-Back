package br.com.totemti.onboardingback.controllers.dtos.sales;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class SaleProduct {
    private Long idProduct;
    private Integer quantity;
    private BigDecimal value;
    private BigDecimal discountValue;
}

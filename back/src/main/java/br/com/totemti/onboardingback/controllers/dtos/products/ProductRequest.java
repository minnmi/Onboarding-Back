package br.com.totemti.onboardingback.controllers.dtos.products;

import br.com.totemti.onboardingback.enums.ProductStatus;
import br.com.totemti.onboardingback.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ProductRequest {
    private String name;

    private Long idCategory;

    private ProductStatus status;

    private BigDecimal value;

    private BigDecimal discountValue;

    private Integer quantity;

    private ProductType type;
}

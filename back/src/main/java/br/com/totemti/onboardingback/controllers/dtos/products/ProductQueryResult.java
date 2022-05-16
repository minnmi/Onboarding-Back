package br.com.totemti.onboardingback.controllers.dtos.products;

import br.com.totemti.onboardingback.enums.ProductStatus;
import br.com.totemti.onboardingback.enums.ProductType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ProductQueryResult {
    private Long id;
    private String name;
    private String category;
    private BigDecimal value;
    private BigDecimal discountValue;
    private Integer quantity;
    private ProductType type;
    private ProductStatus status;
    private Long idCategory;

}

package br.com.totemti.onboardingback.controllers.dtos.reports;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class ProductReportResult {

    private String productName;
    private String categoryName;
    private BigDecimal value;

    public ProductReportResult(String productName, String categoryName, BigDecimal value) {
        this.productName = productName;
        this.categoryName = categoryName;
        this.value = value;
    }
}

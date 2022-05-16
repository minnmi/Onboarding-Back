package br.com.totemti.onboardingback.controllers.dtos.reports;

import br.com.totemti.onboardingback.models.ItemSale;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
public class SaleReportResult {

    private LocalDate saleDate;
    private String product;
    private Integer quantity;
    private BigDecimal value;

    public SaleReportResult(ItemSale itemSale) {
        this.saleDate = itemSale.getSale().getSaleDate();
        this.product = itemSale.getProduct().getName();
        this.quantity = itemSale.getQuantity();
        this.value = itemSale.getValue();
    }

}

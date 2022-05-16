package br.com.totemti.onboardingback.controllers.dtos.sales;

import br.com.totemti.onboardingback.enums.SaleStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class SaleRequest {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate saleDate;

    private String userSale;

    private BigDecimal totalValue;

    private BigDecimal valueDiscount;

    private SaleStatus saleStatus;

    private String comment;

    private Long idPerson;

    private List<SaleProduct> saleProductList;


}

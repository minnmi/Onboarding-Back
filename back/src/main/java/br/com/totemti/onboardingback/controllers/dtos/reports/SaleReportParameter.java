package br.com.totemti.onboardingback.controllers.dtos.reports;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class SaleReportParameter {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private BigDecimal startValue;
    private BigDecimal endValue;
    private Long idPerson;
    private Long idProduct;
}

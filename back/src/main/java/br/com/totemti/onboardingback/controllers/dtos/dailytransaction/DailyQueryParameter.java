package br.com.totemti.onboardingback.controllers.dtos.dailytransaction;

import br.com.totemti.onboardingback.enums.SaleStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class DailyQueryParameter {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private BigDecimal startValue;
    private BigDecimal endValue;
    private String userSale;
    private SaleStatus status;
    private Long idPerson;
    private Long idProduct;
    private Pageable pageable;
}

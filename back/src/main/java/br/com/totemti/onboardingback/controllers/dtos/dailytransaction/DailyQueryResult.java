package br.com.totemti.onboardingback.controllers.dtos.dailytransaction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
public class DailyQueryResult {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate saleDate;
    private BigDecimal value;
    private String userSale;
    private Long saleIdentity;
    private String person;
    private String product;
    private Integer quantity;

    public DailyQueryResult(LocalDate saleDate, BigDecimal value, String userSale, Long saleIdentity, String person, String product, Integer quantity) {
        this.saleDate = saleDate;
        this.value = value;
        this.userSale = userSale;
        this.saleIdentity = saleIdentity;
        this.person = person;
        this.product = product;
        this.quantity = quantity;
    }
}

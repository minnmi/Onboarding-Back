package br.com.totemti.onboardingback.adapters;

import br.com.totemti.onboardingback.controllers.dtos.dailytransaction.DailyQueryResult;
import br.com.totemti.onboardingback.models.Sale;
import br.com.totemti.onboardingback.repositories.ISaleRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@AllArgsConstructor
public class SaleRepositoryAdapter {

    private final ISaleRepository saleRepository;

    public void save(Sale sale) {
        this.saleRepository.saveAndFlush(sale);
    }

    public Page<DailyQueryResult> findAllWithFilters(
            LocalDate startDate,
            LocalDate endDate,
            BigDecimal startValue,
            BigDecimal endValue,
            String userSale,
            Integer status,
            Long idPerson,
            Long idProduct,
            Pageable pageable
    ) {
        return this.saleRepository.findAllWithFilters(
                startDate,
                endDate,
                startValue,
                endValue,
                userSale,
                status,
                idPerson,
                idProduct,
                pageable
        );
    }
}

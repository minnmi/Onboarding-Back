package br.com.totemti.onboardingback.services;

import br.com.totemti.onboardingback.adapters.SaleRepositoryAdapter;
import br.com.totemti.onboardingback.controllers.dtos.dailytransaction.DailyQueryParameter;
import br.com.totemti.onboardingback.controllers.dtos.dailytransaction.DailyQueryResult;
import br.com.totemti.onboardingback.enums.SaleStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DailyTransactionService {

    private final SaleRepositoryAdapter saleRepositoryAdapter;


    @Autowired
    public DailyTransactionService(SaleRepositoryAdapter saleRepositoryAdapter) {
        this.saleRepositoryAdapter = saleRepositoryAdapter;
    }

    public Page<DailyQueryResult> findAllWithFilters(DailyQueryParameter dailyQueryParameter) {
        return this.saleRepositoryAdapter.findAllWithFilters(
                dailyQueryParameter.getStartDate(),
                dailyQueryParameter.getEndDate(),
                dailyQueryParameter.getStartValue(),
                dailyQueryParameter.getEndValue(),
                dailyQueryParameter.getUserSale(),
                Optional.of(dailyQueryParameter).map(DailyQueryParameter::getStatus).map(SaleStatus::getValue).orElse(null),
                dailyQueryParameter.getIdPerson(),
                dailyQueryParameter.getIdProduct(),
                dailyQueryParameter.getPageable()
        );
    }

}

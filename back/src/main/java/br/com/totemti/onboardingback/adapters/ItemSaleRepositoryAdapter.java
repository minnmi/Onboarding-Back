package br.com.totemti.onboardingback.adapters;

import br.com.totemti.onboardingback.models.ItemSale;
import br.com.totemti.onboardingback.repositories.IItemSaleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ItemSaleRepositoryAdapter {

    private final IItemSaleRepository itemSaleRepository;


    public void save(ItemSale itemSale) {
        this.itemSaleRepository.saveAndFlush(itemSale);
    }
}

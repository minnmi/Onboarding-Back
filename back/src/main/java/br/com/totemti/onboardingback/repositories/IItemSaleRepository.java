package br.com.totemti.onboardingback.repositories;

import br.com.totemti.onboardingback.models.ItemSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IItemSaleRepository extends JpaRepository<ItemSale, Long> {


}

package br.com.totemti.onboardingback.repositories;

import br.com.totemti.onboardingback.controllers.dtos.dailytransaction.DailyQueryResult;
import br.com.totemti.onboardingback.models.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ISaleRepository extends JpaRepository<Sale, Long> {

    @Query("select " +
            "    new br.com.totemti.onboardingback.controllers.dtos.dailytransaction.DailyQueryResult( " +
            "        sale.saleDate, " +
            "        itemSale.value, " +
            "        sale.userSale, " +
            "        sale.id, " +
            "        person.name, " +
            "        product.name, " +
            "        itemSale.quantity " +
            "    ) " +
            " " +
            "from ItemSale itemSale " +
            "join itemSale.product product " +
            "join itemSale.sale sale " +
            "join sale.person person " +
            " " +
            "where  " +
            "    ((:startDate is null or :startDate <= sale.saleDate) and (:endDate is null or :endDate >= sale.saleDate)) " +
            "and " +
            "    ((:startValue is null or :startValue <= itemSale.value) and (:endValue is null or :endValue >= itemSale.value)) " +
            "and " +
            "    (:userSale is null or sale.userSale like concat('%', :userSale, '%')) " +
            "and  " +
            "    (:status is null or sale.saleStatusValue = :status) " +
            "and " +
            "    (:idPerson is null or person.id = :idPerson) " +
            "and " +
            "    (:idProduct is null or product.id = :idProduct) ")
    Page<DailyQueryResult> findAllWithFilters(
            LocalDate startDate,
            LocalDate endDate,
            BigDecimal startValue,
            BigDecimal endValue,
            String userSale,
            Integer status,
            Long idPerson,
            Long idProduct,
            Pageable pageable
    );


    @Query("select " +
            "    new br.com.totemti.onboardingback.controllers.dtos.dailytransaction.DailyQueryResult( " +
            "        sale.saleDate, " +
            "        itemSale.value, " +
            "        sale.userSale, " +
            "        sale.id, " +
            "        person.name, " +
            "        product.name, " +
            "        itemSale.quantity " +
            "    ) " +
            " " +
            "from ItemSale itemSale " +
            "join itemSale.product product " +
            "join itemSale.sale sale " +
            "join sale.person person " +
            " " +
            "where  " +
            "    ((:startDate is null or :startDate <= sale.saleDate) and (:endDate is null or :endDate >= sale.saleDate)) " +
            "and " +
            "    ((:startValue is null or :startValue <= itemSale.value) and (:endValue is null or :endValue >= itemSale.value)) " +
            "and " +
            "    (:userSale is null or sale.userSale like concat('%', :userSale, '%')) " +
            "and " +
            "    (:idPerson is null or person.id = :idPerson) " +
            "and " +
            "    (:idProduct is null or product.id = :idProduct) ")
    List<DailyQueryResult> findAllReports(
            LocalDate startDate,
            LocalDate endDate,
            BigDecimal startValue,
            BigDecimal endValue,
            String userSale,
            Long idPerson,
            Long idProduct

    );

    @Query("select sale " +
            "from Sale sale " +
            "join sale.itemSales itemSale " +
            "join itemSale.product product " +
            "join sale.person person " +
            " " +
            "where  " +
            "    ((:startDate is null or :startDate <= sale.saleDate) and (:endDate is null or :endDate >= sale.saleDate)) " +
            "and " +
            "    ((:startValue is null or :startValue <= itemSale.value) and (:endValue is null or :endValue >= itemSale.value)) " +
            "and " +
            "    (:idPerson is null or person.id = :idPerson) " +
            "and " +
            "    (:idProduct is null or product.id = :idProduct) ")
    List<Sale> findSaleBy(
            LocalDate startDate,
            LocalDate endDate,
            BigDecimal startValue,
            BigDecimal endValue,
            Long idPerson,
            Long idProduct
    );
}

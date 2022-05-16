package br.com.totemti.onboardingback.repositories;

import br.com.totemti.onboardingback.controllers.dtos.reports.ProductReportResult;
import br.com.totemti.onboardingback.enums.CategoryStatus;
import br.com.totemti.onboardingback.enums.ProductStatus;
import br.com.totemti.onboardingback.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {

    @Query("select product " +
            "from Product product " +
            "where (:name is null or lower(product.name) like concat('%', lower(:name), '%')) " +
            "and (:idCategory is null or product.category.id = :idCategory) " +
            "and (:status is null or product.status = :status)")
    Page<Product> findAll(String name, Long idCategory, ProductStatus status, Pageable pageable);

    @Query("select " +
            "      new br.com.totemti.onboardingback.controllers.dtos.reports.ProductReportResult( " +
            " product.name, " +
            " category.name, " +
            " product.value  " +
            " ) " +
            "from Category category " +
            "join category.products product " +
            "where (:idProduct is null or product.id = :idProduct) " +
            "and (:idCategory is null or category.id = :idCategory) " +
            "and (:status is null or category.status = :status)")
    List<ProductReportResult> findProductBy(
            Long idProduct,
            Long idCategory,
            CategoryStatus status
    );

}

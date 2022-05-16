package br.com.totemti.onboardingback.repositories;

import br.com.totemti.onboardingback.enums.CategoryStatus;
import br.com.totemti.onboardingback.models.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {

    @Query("select category " +
            "from Category category " +
            "where (:name is null or lower(category.name) like concat('%', lower(:name), '%')) " +
            "and (:status is null or category.status = :status)")
    Page<Category> findAll(String name, CategoryStatus status, Pageable pageable);

}

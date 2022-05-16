package br.com.totemti.onboardingback.adapters;

import br.com.totemti.onboardingback.enums.CategoryStatus;
import br.com.totemti.onboardingback.models.Category;
import br.com.totemti.onboardingback.repositories.ICategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class CategoryRepositoryAdapter {

    private final ICategoryRepository categoryRepository;

    public Page<Category> findAll(String name, CategoryStatus status, Pageable pageable) {
        return this.categoryRepository.findAll(name, status, pageable);
    }

    public void save(Category category) {
        this.categoryRepository.save(category);
    }

    public Optional<Category> maybeFindById(Long idCategory) {
        return this.categoryRepository.findById(idCategory);
    }

    public void deleteById(Long idCategory) {
        this.categoryRepository.deleteById(idCategory);
    }

}

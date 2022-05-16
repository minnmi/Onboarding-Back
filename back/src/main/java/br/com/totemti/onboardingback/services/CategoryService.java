package br.com.totemti.onboardingback.services;

import br.com.totemti.onboardingback.adapters.CategoryRepositoryAdapter;
import br.com.totemti.onboardingback.controllers.dtos.categories.CategoryQueryParameter;
import br.com.totemti.onboardingback.controllers.dtos.categories.CategoryQueryResult;
import br.com.totemti.onboardingback.controllers.dtos.categories.CategoryRequest;
import br.com.totemti.onboardingback.exceptions.EntityNotFoundException;
import br.com.totemti.onboardingback.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepositoryAdapter categoryRepository;

    @Autowired
    public CategoryService(CategoryRepositoryAdapter categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void create(CategoryRequest categoryRequest) {
        final var category = Category.mapsToCategory(categoryRequest);
        this.categoryRepository.save(category);
    }

    public Page<CategoryQueryResult> findAll(CategoryQueryParameter categoryQueryParameter) {
        final var name = categoryQueryParameter.getName();
        final var status = categoryQueryParameter.getStatus();
        final var pageable = categoryQueryParameter.getPageable();

        final var categories = this.categoryRepository.findAll(name, status, pageable);

        return categories.map(category -> Category.mapsToQueryResult(category));

    }

    public CategoryQueryResult findById(Long idCategory) {
        return this.categoryRepository.maybeFindById(idCategory)
                .map(Category::mapsToQueryResult)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));
    }

    public void deleteById(Long idCategory) {
        this.categoryRepository.deleteById(idCategory);
    }

    public void update(CategoryRequest categoryRequest, Long idCategory) {
        Category category = getCategory(idCategory);
        updateFields(categoryRequest, category);
        save(category);
    }

    private void updateFields(CategoryRequest categoryRequest, Category category) {
        category.setName(categoryRequest.getName());
        category.setStatus(categoryRequest.getStatus());
    }

    private void save(Category category) {
        this.categoryRepository.save(category);
    }

    public Category getCategory(Long idCategory) {
        return this.categoryRepository.maybeFindById(idCategory)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));
    }
}

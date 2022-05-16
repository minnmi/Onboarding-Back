package br.com.totemti.onboardingback.adapters;

import br.com.totemti.onboardingback.enums.ProductStatus;
import br.com.totemti.onboardingback.models.Product;
import br.com.totemti.onboardingback.repositories.IProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class ProductRepositoryAdapter {

    private final IProductRepository productRepository;

    public Page<Product> findAll(String name, Long idCategory, ProductStatus status, Pageable pageable) {
        return this.productRepository.findAll(name, idCategory, status, pageable);
    }

    public void save(Product product) {
        this.productRepository.save(product);
    }

    public Optional<Product> maybeFindById(Long idProduct) {
        return this.productRepository.findById(idProduct);
    }

    public void deleteById(Long idProduct) {
        this.productRepository.deleteById(idProduct);
    }

}

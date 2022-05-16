package br.com.totemti.onboardingback.services;

import br.com.totemti.onboardingback.adapters.CategoryRepositoryAdapter;
import br.com.totemti.onboardingback.adapters.ProductRepositoryAdapter;
import br.com.totemti.onboardingback.controllers.dtos.products.ProductQueryParameter;
import br.com.totemti.onboardingback.controllers.dtos.products.ProductQueryResult;
import br.com.totemti.onboardingback.controllers.dtos.products.ProductRequest;
import br.com.totemti.onboardingback.exceptions.EntityNotFoundException;
import br.com.totemti.onboardingback.models.Category;
import br.com.totemti.onboardingback.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepositoryAdapter productRepositoryAdapter;
    private final CategoryRepositoryAdapter categoryRepositoryAdapter;
    private final CategoryService categoryService;

    @Autowired
    public ProductService(ProductRepositoryAdapter productRepositoryAdapter,
                          CategoryRepositoryAdapter categoryRepositoryAdapter, CategoryService categoryService) {
        this.productRepositoryAdapter = productRepositoryAdapter;
        this.categoryRepositoryAdapter = categoryRepositoryAdapter;
        this.categoryService = categoryService;
    }


    public void create(ProductRequest productRequest) {
        final var product = new Product();
        product.setName(productRequest.getName());
        product.setCategory(getCategory(productRequest));
        product.setStatus(productRequest.getStatus());
        product.setValue(productRequest.getValue());
        product.setDiscountValue(productRequest.getDiscountValue());
        product.setQuantity(productRequest.getQuantity());
        product.setType(productRequest.getType());
        this.productRepositoryAdapter.save(product);
    }

    private Category getCategory(ProductRequest productRequest) {
        return this.categoryService.getCategory(productRequest.getIdCategory());
    }


    public Page<ProductQueryResult> findAll(ProductQueryParameter productQueryParameter) {
        final var name = productQueryParameter.getName();
        final var idCategory = productQueryParameter.getIdCategory();
        final var status = productQueryParameter.getStatus();
        final var pageable = productQueryParameter.getPageable();

        final var products = productRepositoryAdapter.findAll(name, idCategory, status, pageable);

        return products.map(Product::mapsToQueryResult);
    }

    public ProductQueryResult findById(Long idProduct) {
        return this.productRepositoryAdapter.maybeFindById(idProduct)
                .map(Product::mapsToQueryResult)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

    }

    public void deleteById(Long idProduct) {
        this.productRepositoryAdapter.deleteById(idProduct);
    }

    public void update(ProductRequest productRequest, Long idProduct) {
        Product product = getProduct(idProduct);
        updateFields(productRequest, product);
        save(product);
    }

    void save(Product product) {
        this.productRepositoryAdapter.save(product);
    }

    private void updateFields(ProductRequest productRequest, Product product) {
        product.setName(productRequest.getName());
        product.setStatus(productRequest.getStatus());
        product.setCategory(getCategory(productRequest));
        product.setValue(productRequest.getValue());
        product.setDiscountValue(productRequest.getDiscountValue());
        product.setQuantity(productRequest.getQuantity());
        product.setType(productRequest.getType());
    }

    public Product getProduct(Long idProduct) {
        return this.productRepositoryAdapter.maybeFindById(idProduct)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
    }
}

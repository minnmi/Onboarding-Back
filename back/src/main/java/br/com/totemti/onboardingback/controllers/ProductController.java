package br.com.totemti.onboardingback.controllers;

import br.com.totemti.onboardingback.commons.PageableResponseBase;
import br.com.totemti.onboardingback.commons.ResponseBase;
import br.com.totemti.onboardingback.controllers.dtos.products.ProductQueryParameter;
import br.com.totemti.onboardingback.controllers.dtos.products.ProductQueryResult;
import br.com.totemti.onboardingback.controllers.dtos.products.ProductRequest;
import br.com.totemti.onboardingback.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
public class ProductController {
    private final ProductService productService;


    @PostMapping
    public ResponseEntity<ResponseBase<Void>> create(@RequestBody ProductRequest productRequest) {
        this.productService.create(productRequest);
        return ResponseEntity.ok(ResponseBase.of());
    }

    @GetMapping
    public ResponseEntity<PageableResponseBase<ProductQueryResult>> findAll(ProductQueryParameter productQueryParameter) {
        final var products = this.productService.findAll(productQueryParameter);
        return ResponseEntity.ok(PageableResponseBase.of(products));
    }

    @GetMapping("/{idProduct}")
    public ResponseEntity<ResponseBase<ProductQueryResult>> findById(@PathVariable Long idProduct) {
        final var product = this.productService.findById(idProduct);
        return ResponseEntity.ok(ResponseBase.of(product));
    }

    @DeleteMapping("/{idProduct}")
    public ResponseEntity<ResponseBase<Void>> deleteById(@PathVariable Long idProduct) {
        this.productService.deleteById(idProduct);
        return ResponseEntity.ok(ResponseBase.of());
    }

    @PutMapping("/{idProduct}")
    public ResponseEntity<ResponseBase<Void>> update(@RequestBody ProductRequest productRequest, @PathVariable Long idProduct) {
        this.productService.update(productRequest, idProduct);
        return ResponseEntity.ok(ResponseBase.of());
    }

}

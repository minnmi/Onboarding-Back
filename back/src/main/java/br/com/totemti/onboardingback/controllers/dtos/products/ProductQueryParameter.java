package br.com.totemti.onboardingback.controllers.dtos.products;

import br.com.totemti.onboardingback.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
@AllArgsConstructor
public class ProductQueryParameter {
    private String name;
    private ProductStatus status;
    private Long idCategory;
    private Pageable pageable;
}

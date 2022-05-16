package br.com.totemti.onboardingback.controllers.dtos.categories;


import br.com.totemti.onboardingback.enums.CategoryStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
@AllArgsConstructor
public class CategoryQueryParameter {

    private String name;
    private CategoryStatus status;
    private Pageable pageable;

}

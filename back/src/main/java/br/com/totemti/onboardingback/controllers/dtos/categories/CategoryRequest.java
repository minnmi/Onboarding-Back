package br.com.totemti.onboardingback.controllers.dtos.categories;


import br.com.totemti.onboardingback.enums.CategoryStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoryRequest {
    private String name;

    private CategoryStatus status;

}

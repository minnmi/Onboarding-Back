package br.com.totemti.onboardingback.controllers.dtos.categories;


import br.com.totemti.onboardingback.enums.CategoryStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryQueryResult {

    private String name;
    private CategoryStatus status;
    private Long idCategory;

}

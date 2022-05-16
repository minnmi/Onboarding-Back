package br.com.totemti.onboardingback.controllers.dtos.reports;

import br.com.totemti.onboardingback.enums.CategoryStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class ProductReportParameter {

    private Long idCategory;
    private Long idProduct;
    private CategoryStatus status;

}

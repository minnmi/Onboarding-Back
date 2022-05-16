package br.com.totemti.onboardingback.controllers.dtos.reports;

import br.com.totemti.onboardingback.enums.PersonType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PersonReportParameter {

    private Long idPerson;
    private PersonType personType;

}

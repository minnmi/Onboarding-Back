package br.com.totemti.onboardingback.controllers.dtos.reports;

import br.com.totemti.onboardingback.enums.PersonType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonReportResult {

    private String name;
    private PersonType type;
    private String inscricaoEstadual;
    private String phone;
    private String email;

}

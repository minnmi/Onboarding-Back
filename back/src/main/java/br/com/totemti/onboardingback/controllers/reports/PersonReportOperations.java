package br.com.totemti.onboardingback.controllers.reports;

import br.com.totemti.onboardingback.controllers.dtos.reports.PersonReportParameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface PersonReportOperations {

    @GetMapping
    ResponseEntity<byte[]> build(PersonReportParameter personReportParameter);


}

package br.com.totemti.onboardingback.controllers.reports;


import br.com.totemti.onboardingback.controllers.dtos.reports.PersonReportParameter;
import br.com.totemti.onboardingback.services.PersonReportService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
@RequestMapping("/reports/persons")
@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
public class PersonReportController implements PersonReportOperations {

    private PersonReportService personReportService;

    @Override
    public ResponseEntity<byte[]> build(PersonReportParameter personReportParameter) {
        final var bytes = personReportService.build(personReportParameter);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
                .body(bytes);
    }
}

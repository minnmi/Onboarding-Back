package br.com.totemti.onboardingback.controllers.reports;


import br.com.totemti.onboardingback.controllers.dtos.reports.SaleReportParameter;
import br.com.totemti.onboardingback.services.SaleReportService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/reports/sales")
@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
public class SaleReportController {

    private SaleReportService saleReportService;

    @GetMapping
    public ResponseEntity<byte[]> build(SaleReportParameter saleReportParameter) {
        final var bytes = saleReportService.build(saleReportParameter);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
                .body(bytes);
    }


}

package br.com.totemti.onboardingback.controllers.reports;


import br.com.totemti.onboardingback.controllers.dtos.reports.ProductReportParameter;
import br.com.totemti.onboardingback.services.ProductReportService;
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
@RequestMapping("/reports/products")
@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
public class ProductReportController {

    private ProductReportService productReportService;

    @GetMapping
    public ResponseEntity<byte[]> build(ProductReportParameter productReportParameter) {
        final var bytes = productReportService.build(productReportParameter);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
                .body(bytes);
    }


}

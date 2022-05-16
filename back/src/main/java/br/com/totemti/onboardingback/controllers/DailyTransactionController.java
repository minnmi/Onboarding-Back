package br.com.totemti.onboardingback.controllers;


import br.com.totemti.onboardingback.commons.PageableResponseBase;
import br.com.totemti.onboardingback.controllers.dtos.dailytransaction.DailyQueryParameter;
import br.com.totemti.onboardingback.controllers.dtos.dailytransaction.DailyQueryResult;
import br.com.totemti.onboardingback.repositories.IProductRepository;
import br.com.totemti.onboardingback.repositories.ISaleRepository;
import br.com.totemti.onboardingback.services.DailyTransactionReportService;
import br.com.totemti.onboardingback.services.DailyTransactionService;
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
@RequestMapping("/transactions")
@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
public class DailyTransactionController {

    private DailyTransactionService dailyTransactionService;
    private DailyTransactionReportService dailyTransactionReportService;
    private IProductRepository productRepository;
    private ISaleRepository saleRepository;

    @GetMapping
    public ResponseEntity<PageableResponseBase<DailyQueryResult>> findAllWithFilters(DailyQueryParameter dailyQueryParameter) {
        final var transactions = this.dailyTransactionService.findAllWithFilters(dailyQueryParameter);
        return ResponseEntity.ok(PageableResponseBase.of(transactions));
    }


    @GetMapping("/pdf")
    public ResponseEntity<byte[]> reportBuilder(DailyQueryParameter dailyQueryParameter) {
        try {
            final var queryReports = saleRepository.findAllReports(
                    dailyQueryParameter.getStartDate(),
                    dailyQueryParameter.getEndDate(),
                    dailyQueryParameter.getStartValue(),
                    dailyQueryParameter.getEndValue(),
                    dailyQueryParameter.getUserSale(),
                    dailyQueryParameter.getIdPerson(),
                    dailyQueryParameter.getIdProduct()

            );

            final var bytes = dailyTransactionReportService.reportBuilder(queryReports);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
                    .body(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

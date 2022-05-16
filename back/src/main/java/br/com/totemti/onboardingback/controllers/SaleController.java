package br.com.totemti.onboardingback.controllers;

import br.com.totemti.onboardingback.commons.ResponseBase;
import br.com.totemti.onboardingback.controllers.dtos.sales.SaleRequest;
import br.com.totemti.onboardingback.services.SaleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//O controller mapeia as requisições que vão para o front

@AllArgsConstructor
@RestController
@RequestMapping(value = "/sales")
@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
public class SaleController {

    private final SaleService saleService;


    @PostMapping
    public ResponseEntity<ResponseBase<Void>> create(@RequestBody SaleRequest saleRequest) {
        this.saleService.create(saleRequest);
        return ResponseEntity.ok(ResponseBase.of());
    }


}

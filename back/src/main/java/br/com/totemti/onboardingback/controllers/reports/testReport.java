package br.com.totemti.onboardingback.controllers.reports;

import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;

public class testReport {


    @GetMapping("/users/export")
    public void exportToPDF(HttpServletResponse response) {
        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users.pdf";

        response.setHeader(headerKey, headerValue);
    }

}

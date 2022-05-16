package br.com.totemti.onboardingback.controllers.dtos.reports;

import br.com.totemti.onboardingback.models.Sale;
import lombok.Getter;
import lombok.Setter;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ClientReportResult {

    private String nameClient;
    private String userSale;
    private Long saleIdentity;
    private String document;
    private List<SaleReportResult> products;
    private BigDecimal totalValue;

    public ClientReportResult() {
    }

    public ClientReportResult(Sale sale) {
        this.nameClient = sale.getPerson().getName();
        this.userSale = sale.getUserSale();
        this.saleIdentity = sale.getId();
        this.document = sale.getPerson().getDocument();
        this.products = sale.getItemSales()
                .stream()
                .map(SaleReportResult::new)
                .collect(Collectors.toList());
        this.totalValue = sale.getTotalValue();
    }

    public JRBeanCollectionDataSource getProducts() {
        return new JRBeanCollectionDataSource(products);
    }
}

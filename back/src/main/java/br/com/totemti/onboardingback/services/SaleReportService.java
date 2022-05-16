package br.com.totemti.onboardingback.services;

import br.com.totemti.onboardingback.controllers.dtos.reports.ClientReportResult;
import br.com.totemti.onboardingback.controllers.dtos.reports.SaleReportParameter;
import br.com.totemti.onboardingback.repositories.IPersonRepository;
import br.com.totemti.onboardingback.repositories.ISaleRepository;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class SaleReportService {

    private ISaleRepository saleRepository;
    private IPersonRepository personRepository;

    public byte[] reportBuilder(List<ClientReportResult> clientReportResult) throws Exception {
        Map<String, Object> params = paramsBuilder(clientReportResult);
        final var inputStream = getClass().getResourceAsStream("/reports/SaleReport.jasper");
        final var jasperPrint = JasperFillManager.fillReport(inputStream, params, new JREmptyDataSource());
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    private BufferedImage getLogoTitle() throws Exception {
        return ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/reports/images/u1448.png")));
    }

    private BufferedImage getLogoFooter() throws Exception {
        return ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/reports/images/u1449.png")));
    }

    private Map<String, Object> paramsBuilder(List<ClientReportResult> clientReportResult) throws Exception {
        Map<String, Object> params = new HashMap<>();

        final var totalValue = clientReportResult.stream()
                .map(ClientReportResult::getTotalValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        params.put("LOGO_TOTEM_TITULO", getLogoTitle());
        params.put("LOGO_TOTEM_RODAPE", getLogoFooter());
        params.put("USUARIO_IMPRESSAO", "Admin");
        params.put("SALES", new JRBeanCollectionDataSource(clientReportResult));
        params.put("TOTAL_VALUE", totalValue);

        return params;
    }

    public byte[] build(SaleReportParameter saleReportParameter) {
        final var sales = saleRepository.findSaleBy(
                saleReportParameter.getStartDate(),
                saleReportParameter.getEndDate(),
                saleReportParameter.getStartValue(),
                saleReportParameter.getEndValue(),
                saleReportParameter.getIdPerson(),
                saleReportParameter.getIdProduct()
        );

        try {
            final var collect = sales.stream()
                    .map(ClientReportResult::new)
                    .collect(Collectors.toList());
            return reportBuilder(collect);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

package br.com.totemti.onboardingback.services;

import br.com.totemti.onboardingback.controllers.dtos.reports.ProductReportParameter;
import br.com.totemti.onboardingback.controllers.dtos.reports.ProductReportResult;
import br.com.totemti.onboardingback.repositories.IProductRepository;
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

@AllArgsConstructor
@Service
public class ProductReportService {

    private IProductRepository productRepository;

    public byte[] reportBuilder(List<ProductReportResult> collect) throws Exception {
        Map<String, Object> params = paramsBuilder(collect);
        final var inputStream = getClass().getResourceAsStream("/reports/ProductReport.jasper");
        final var jasperPrint = JasperFillManager.fillReport(inputStream, params, new JREmptyDataSource());
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    private BufferedImage getLogoTitle() throws Exception {
        return ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/reports/images/u1448.png")));
    }

    private BufferedImage getLogoFooter() throws Exception {
        return ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/reports/images/u1449.png")));
    }

    private Map<String, Object> paramsBuilder(List<ProductReportResult> collect) throws Exception {
        Map<String, Object> params = new HashMap<>();

        final var totalValue = collect.stream()
                .map(ProductReportResult::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        params.put("LOGO_TOTEM_TITULO", getLogoTitle());
        params.put("LOGO_TOTEM_RODAPE", getLogoFooter());
        params.put("USUARIO_IMPRESSAO", "Admin");
        params.put("PRODUCTS", new JRBeanCollectionDataSource(collect));
        params.put("TOTAL_VALUE", totalValue);

        return params;
    }


    public byte[] build(ProductReportParameter productReportParameter) {
        final var productReports = productRepository.findProductBy(
                productReportParameter.getIdProduct(),
                productReportParameter.getIdCategory(),
                productReportParameter.getStatus()
        );

        try {
            return reportBuilder(productReports);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

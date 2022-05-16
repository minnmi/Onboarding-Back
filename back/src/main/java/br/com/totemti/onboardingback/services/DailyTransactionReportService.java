package br.com.totemti.onboardingback.services;

import br.com.totemti.onboardingback.controllers.dtos.dailytransaction.DailyQueryResult;
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

@Service
public class DailyTransactionReportService {

    public byte[] reportBuilder(List<DailyQueryResult> collect) throws Exception {
        Map<String, Object> params = paramsBuilder(collect);
        final var inputStream = getClass().getResourceAsStream("/reports/DailyTransactionReport.jasper");
        final var jasperPrint = JasperFillManager.fillReport(inputStream, params, new JREmptyDataSource());
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    private BufferedImage getLogoTitle() throws Exception {
        return ImageIO.read(getClass().getResourceAsStream("/reports/images/u1448.png"));
    }

    private BufferedImage getLogoFooter() throws Exception {
        return ImageIO.read(getClass().getResourceAsStream("/reports/images/u1449.png"));
    }

    private Map<String, Object> paramsBuilder(List<DailyQueryResult> collect) throws Exception {
        Map<String, Object> params = new HashMap<>();

        //Somatório de valores do DailyQueryResult
        final var totalValue = collect.stream()
                .map(DailyQueryResult::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        params.put("LOGO_TOTEM_TITULO", getLogoTitle());
        params.put("LOGO_TOTEM_RODAPE", getLogoFooter());
        params.put("USUARIO_IMPRESSAO", "Admin");
        params.put("SALES", new JRBeanCollectionDataSource(collect));
        params.put("TOTAL_VALUE", totalValue);

        return params;
    }


}

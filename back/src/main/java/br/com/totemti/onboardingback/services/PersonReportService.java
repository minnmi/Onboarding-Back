package br.com.totemti.onboardingback.services;

import br.com.totemti.onboardingback.controllers.dtos.reports.PersonReportParameter;
import br.com.totemti.onboardingback.controllers.dtos.reports.PersonReportResult;
import br.com.totemti.onboardingback.repositories.IPersonRepository;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@AllArgsConstructor
public class PersonReportService {

    private IPersonRepository personRepository;


    public byte[] reportBuilder(List<PersonReportResult> collect) throws Exception {
        Map<String, Object> params = paramsBuilder(collect);
        final var inputStream = getClass().getResourceAsStream("/reports/ClientReport.jasper");
        final var jasperPrint = JasperFillManager.fillReport(inputStream, params, new JREmptyDataSource());
        return JasperExportManager.exportReportToPdf(jasperPrint);

    }

    private BufferedImage getLogoTitle() throws Exception {
        return ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/reports/images/u1448.png")));
    }

    private BufferedImage getLogoFooter() throws Exception {
        return ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/reports/images/u1449.png")));
    }

    private Map<String, Object> paramsBuilder(List<PersonReportResult> collect) throws Exception {
        Map<String, Object> params = new HashMap<>();

        params.put("LOGO_TOTEM_TITULO", getLogoTitle());
        params.put("LOGO_TOTEM_RODAPE", getLogoFooter());
        params.put("USUARIO_IMPRESSAO", "Admin");
        params.put("CLIENTS", new JRBeanCollectionDataSource(collect));

        return params;
    }

    public byte[] build(PersonReportParameter personReportParameter) {
        final var persons = personRepository.findPersonBy(
                personReportParameter.getIdPerson(),
                personReportParameter.getPersonType()
        );

        try {
            return reportBuilder(persons);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

package br.com.web.pesquisas.configuration;


import java.util.Properties;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

@Configuration
public class ReportConfiguration {


//    @Bean
//    @Qualifier("ordemServicoReport")
//    public JasperReportsPdfView getOrdemServicoReport() {
//        JasperReportsPdfView view = new JasperReportsPdfView();
//        view.setUrl("classpath:reports/testePDF.jasper");
//        view.setReportDataKey("datasource");
//        Properties headers = new Properties();
//        headers.setProperty("Content-Disposition", "attachment; filename=OrdemServico.pdf");
//        view.setHeaders(headers);
//        return view;
//    }
}

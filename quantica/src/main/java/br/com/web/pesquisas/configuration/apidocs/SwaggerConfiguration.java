package br.com.web.pesquisas.configuration.apidocs;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Date;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;

import br.com.web.pesquisas.configuration.ApplicationProperties;
import br.com.web.pesquisas.configuration.Constants;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Profile("!" + Constants.SPRING_PROFILE_PRODUCTION)
@Slf4j
public class SwaggerConfiguration {

    public static final String DEFAULT_INCLUDE_PATTERN = "/api/.*";

    /**
     * Swagger Springfox configuration.
     */
    @Bean
    public Docket swaggerSpringfoxDocket(ApplicationProperties applicationProperties) {
        log.debug("Inicializando Swagger");
        StopWatch watch = new StopWatch();
        watch.start();
        ApiInfo apiInfo = new ApiInfo(
        		applicationProperties.getSwagger().getTitle(),
        		applicationProperties.getSwagger().getDescription(),
        		applicationProperties.getSwagger().getVersion(),
        		applicationProperties.getSwagger().getTermsOfServiceUrl(),
        		applicationProperties.getSwagger().getContact(),
        		applicationProperties.getSwagger().getLicense(),
        		applicationProperties.getSwagger().getLicenseUrl());

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo)
            .genericModelSubstitutes(ResponseEntity.class)
            .forCodeGeneration(true)
            .genericModelSubstitutes(ResponseEntity.class)
            .directModelSubstitute(java.time.LocalDate.class, String.class)
            .directModelSubstitute(java.time.ZonedDateTime.class, Date.class)
            .directModelSubstitute(java.time.LocalDateTime.class, Date.class)
            .select()
            .paths(regex(DEFAULT_INCLUDE_PATTERN))
            .build();
        watch.stop();
        log.debug("Swagger inicializado em {} ms", watch.getTotalTimeMillis());
        return docket;
    }
}

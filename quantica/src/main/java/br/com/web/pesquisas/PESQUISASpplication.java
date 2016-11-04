package br.com.web.pesquisas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ma.glasnost.orika.OrikaSystemProperties;
import ma.glasnost.orika.impl.generator.EclipseJdtCompilerStrategy;

@SpringBootApplication
public class PESQUISASpplication {

    public static void main(String[] args) {
        
        SpringApplication.run(PESQUISASpplication.class, args);
    }
}

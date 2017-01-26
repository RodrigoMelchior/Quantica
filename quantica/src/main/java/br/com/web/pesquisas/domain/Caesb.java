package br.com.web.pesquisas.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CAESB")
public class Caesb implements Serializable {

    /** 
     * 
     */
    private static final long serialVersionUID = -3751103684327967529L;
    
    @Getter
    @Setter
    @Id
    @Column(name = "ID")
    private Long id;

    @Getter
    @Setter
    @Column(name = "EMPRESA")
    private String empresa;

    @Getter
    @Setter
    @Column(name = "TIPO")
    private String tipo;

    @Getter
    @Setter
    @Column(name = "COD")
    private String cod;

    @Getter
    @Setter
    @Column(name = "NOME")
    private String nome;
    
    @Getter
    @Setter
    @Column(name = "FREQUENCIA")
    private String frequencia;

    @Getter
    @Setter
    @Column(name = "SAL1")
    private BigDecimal sal1;
    
    @Getter
    @Setter
    @Column(name = "SAL2")
    private BigDecimal sal2;
    
    @Getter
    @Setter
    @Column(name = "SAL3")
    private BigDecimal sal3;
    
}

package br.com.web.pesquisas.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_INPC")
public class Inpc implements Serializable {

    /** 
     * 
     */
    private static final long serialVersionUID = -3751103684327967529L;

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_INPC")
    @SequenceGenerator(name = "SQ_INPC", sequenceName = "SQ_INPC_COSEQINPC")
    @Column(name = "CO_SEQ_INPC")
    private Long id;

    @Getter
    @Setter
    @Column(name = "DT_MES_ANO")
    private Date mesAno;

    @Getter
    @Setter
    @Column(name = "VL_INDICE")
    private BigDecimal indice;

}

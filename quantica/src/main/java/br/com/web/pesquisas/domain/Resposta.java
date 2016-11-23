package br.com.web.pesquisas.domain;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_RESPOSTA")
public class Resposta implements Serializable {

    /** 
     * 
     */
    private static final long serialVersionUID = -3751103684327967529L;

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_RESPOSTA")
    @SequenceGenerator(name = "SQ_RESPOSTA", sequenceName = "SQ_RESPOSTA_COSEQRESPOSTA")
    @Column(name = "CO_SEQ_RESPOSTA")
    private Long id;

    @Getter
    @Setter
    @NotNull
    @Column(name = "TX_RESPOSTA")
    private String texto;

    @Getter
    @Setter
    @Column(name = "DT_RESPOSTA")
    private Date data;

    @Getter @Setter
	@ManyToOne
	@JoinColumn(name="CO_EMPRESA", updatable = false, insertable = false)
	@JsonIgnore
	private Empresa empresa;

    @Getter @Setter
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="CO_PERGUNTA_PESQUISA",updatable = false, insertable = false)
	@JsonIgnore
	private PerguntaPesquisa pergunta;
    
}

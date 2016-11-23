package br.com.web.pesquisas.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.web.pesquisas.domain.utils.TipoRelacionamentoConverter;
import br.com.web.pesquisas.enuns.TipoRelacionamento;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "RL_EMPRESA_PESQUISA")
public class EmpresaPesquisa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4685519607025108415L;

	@Getter @Setter
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SQ_EMPRESA_PESQUISA")
	@SequenceGenerator(name="SQ_EMPRESA_PESQUISA", sequenceName="SQ_EMPRESAPESQUISA_COSEQEMPRESAPESQUISA")
	@Column(name="CO_SEQ_EMPRESAPESQUISA")
	private Long id;

	@Getter @Setter
	@ManyToOne
	@JoinColumn(name="CO_EMPRESA", referencedColumnName="CO_SEQ_EMPRESA")
	private Empresa empresa;

	@Getter @Setter
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="CO_PESQUISA", referencedColumnName="CO_SEQ_PESQUISA")
	@JsonIgnore
	private Pesquisa pesquisa;
	
	@Getter @Setter
	@Column(name="TP_RELACIONAMENTO")
	@Convert(converter=TipoRelacionamentoConverter.class)
	private TipoRelacionamento relacionamento;

}

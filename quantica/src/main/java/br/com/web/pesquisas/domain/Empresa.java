package br.com.web.pesquisas.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.web.pesquisas.domain.utils.TipoContratacaoToStringConverter;
import br.com.web.pesquisas.domain.utils.TipoFaturamentoToStringConverter;
import br.com.web.pesquisas.domain.utils.TipoPropriedadeToStringConverter;
import br.com.web.pesquisas.enuns.TipoContratacao;
import br.com.web.pesquisas.enuns.TipoFaturamento;
import br.com.web.pesquisas.enuns.TipoPropriedade;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_EMPRESA")
public class Empresa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4685519607025108415L;

	@Getter @Setter
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SQ_EMPRESA")
	@SequenceGenerator(name="SQ_EMPRESA", sequenceName="SQ_EMPRESA_COSEQEMPRESA")
	@Column(name="CO_SEQ_EMPRESA")
	private Long id;

	@Getter @Setter
	@Column(name="NO_EMPRESA", length=200, nullable = false)
	private String nome;
	
	@Getter @Setter
	@Column(name="TP_FATURAMENTO")
	@Convert(converter = TipoFaturamentoToStringConverter.class)
	private TipoFaturamento faturamento;

	@Getter @Setter
	@Column(name="QT_FUNCIONARIO")
	private Long qtdFuncionario;

	@Getter @Setter
	@Column(name="TP_CONTRATACAO")
	@Convert(converter = TipoContratacaoToStringConverter.class)
	private TipoContratacao contratacao;

	@Getter @Setter
	@Column(name="DT_BASE")
	private String dataBase;

	@Getter @Setter
	@Column(name="DT_ULTIMO_REAJUSTE")
	private Date dataUltimoReajuste;

	@Getter @Setter
	@Column(name="TP_PROPRIEDADE")
	@Convert(converter = TipoPropriedadeToStringConverter.class)
	private TipoPropriedade propriedade;
    
	@Getter 
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="empresa", orphanRemoval = true)
	private List<UfEmpresa> ufs;

	public void setUfs(List<UfEmpresa> ufs) {
		
		for (UfEmpresa ufEmpresa : ufs) {
			ufEmpresa.setEmpresa(this);
		}
		this.ufs = ufs;
	}
	
    
	public Empresa(Long idEmpresa) {
		this.id = idEmpresa;
	}

	public Empresa() {
		super();
	}
	
	
    
}

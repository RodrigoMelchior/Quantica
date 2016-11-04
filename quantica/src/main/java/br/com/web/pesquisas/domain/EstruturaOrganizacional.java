package br.com.web.pesquisas.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.web.pesquisas.domain.utils.NivelEstruturaConverter;
import br.com.web.pesquisas.enuns.NivelEstrutura;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_ESTRUTURA_ORGANIZACIONAL")
public class EstruturaOrganizacional implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4685519607025108415L;

	@Getter @Setter
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SQ_ESTRUTURA_ORGANIZACIONAL")
	@SequenceGenerator(name="SQ_ESTRUTURA_ORGANIZACIONAL", sequenceName="SQ_ESTRUTURAORGANIZACIONAL_COSEQESTRUTURAORGANIZACIONAL")
	@Column(name="CO_SEQ_ESTRUTURA_ORGANIZACIONAL")
	private Long id;

	@Getter @Setter
	@Column(name="NO_ESTRUTURA_ORGANIZACIONAL", length=200, nullable = false)
	private String nome;
	
	@Getter @Setter
	@Column(name="nu_nivel")
	@Convert(converter = NivelEstruturaConverter.class)
	private NivelEstrutura nivel;
	
	@Getter @Setter
	@ManyToOne
	@JoinColumn(name="CO_EMPRESA", referencedColumnName="CO_SEQ_EMPRESA")
	private Empresa empresa;
	
	@Getter @Setter
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="estruturaOrganizacional")
	@JsonIgnore
	private List<Cargo> cargos;
    
}

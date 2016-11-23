package br.com.web.pesquisas.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_PAINEL")
public class Painel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4685519607025108415L;

	@Getter @Setter
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SQ_PAINEL")
	@SequenceGenerator(name="SQ_PAINEL", sequenceName="SQ_PAINEL_COSEQPAINEL")
	@Column(name="CO_SEQ_PAINEL")
	private Long id;

	@Getter @Setter
	@Column(name="NO_PAINEL", length=100, nullable = false)
	private String nome;

	@Getter @Setter
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="CO_PESQUISA", referencedColumnName="CO_SEQ_PESQUISA")
	@JsonIgnore
	private Pesquisa pesquisa;
	
	@Getter
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="painel", orphanRemoval=true)
	private Set<PainelEmpresa> painelEmpresa;

	public void setPainelEmpresa(Set<PainelEmpresa> painelEmpresa) {
		for (PainelEmpresa painelEmpresa2 : painelEmpresa) {
			painelEmpresa2.setPainel(this);
		}
		this.painelEmpresa = painelEmpresa;
	}
	
	
    
}

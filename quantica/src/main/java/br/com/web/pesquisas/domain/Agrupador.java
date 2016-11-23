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
@Table(name = "TB_AGRUPADOR")
public class Agrupador implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4685519607025108415L;

	@Getter @Setter
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SQ_AGRUPADOR")
	@SequenceGenerator(name="SQ_AGRUPADOR", sequenceName="SQ_AGRUPADOR_COSEQAGRUPADOR")
	@Column(name="CO_SEQ_AGRUPADOR")
	private Long id;

	@Getter @Setter
	@Column(name="NO_AGRUPADOR", length=100, nullable = false)
	private String nome;

	@Getter @Setter
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="CO_PESQUISA", referencedColumnName="CO_SEQ_PESQUISA")
	@JsonIgnore
	private Pesquisa pesquisa;
    
	@Getter 
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="agrupador")
	//@JsonManagedReference
	private Set<FormulaAgrupador> formulas;

	public void setFormulas(Set<FormulaAgrupador> formulas) {
		for (FormulaAgrupador formulaAgrupador : formulas) {
			formulaAgrupador.setAgrupador(this);
		}
		this.formulas = formulas;
	}
	
	
	
}

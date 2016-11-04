package br.com.web.pesquisas.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "RL_UF_EMPRESA")
public class UfEmpresa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4685519607025108415L;

	@Getter @Setter
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SQ_UF_EMPRESA")
	@SequenceGenerator(name="SQ_UF_EMPRESA", sequenceName="SQ_UFEMPRESA_COSEQUFEMPRESA")
	@Column(name="CO_SEQ_UF_EMPRESA")
	private Long id;

	@Getter @Setter
	@ManyToOne
	@JoinColumn(name="CO_EMPRESA", referencedColumnName="CO_SEQ_EMPRESA")
	@JsonIgnore
	private Empresa empresa;

	@Getter @Setter
	@ManyToOne
	@JoinColumn(name="CO_UF", referencedColumnName="CO_SEQ_UF")
	private Uf uf;
	
}

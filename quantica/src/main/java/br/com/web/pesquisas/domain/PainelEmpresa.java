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
@Table(name = "RL_PAINEL_EMPRESA")
public class PainelEmpresa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4685519607025108415L;

	@Getter @Setter
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SQ_PAINEL_EMPRESA")
	@SequenceGenerator(name="SQ_PAINEL_EMPRESA", sequenceName="SQ_PAINELEMPRESA_COSEQPAINELEMPRESA")
	@Column(name="CO_SEQ_PAINELEMPRESA")
	private Long id;

	@Getter @Setter
	@ManyToOne
	@JoinColumn(name="CO_EMPRESA", referencedColumnName="CO_SEQ_EMPRESA")
	private Empresa empresa;

	@Getter @Setter
	@ManyToOne
	@JoinColumn(name="CO_PAINEL")
	@JsonIgnore
	private Painel painel;
	

}

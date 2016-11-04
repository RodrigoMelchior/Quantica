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

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_CONTATO")
public class Contato implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4685519607025108415L;

	@Getter @Setter
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SQ_CONTATO")
	@SequenceGenerator(name="SQ_CONTATO", sequenceName="SQ_CONTATO_COSEQCONTATO")
	@Column(name="CO_SEQ_CONTATO")
	private Long id;

	@Getter @Setter
	@Column(name="NO_CONTATO", length=100, nullable = false)
	private String nome;

	@Getter @Setter
	@Column(name="DS_CARGO", length=100, nullable = false)
	private String descricaoCargo;

	@Getter @Setter
	@Column(name="DS_EMAIL", length=100, nullable = false)
	private String email;

	@Getter @Setter
	@Column(name="NU_TELEFONE", length=100, nullable = false)
	private String telefone;

	
	@Getter @Setter
	@ManyToOne
	@JoinColumn(name="CO_EMPRESA", referencedColumnName="CO_SEQ_EMPRESA")
	private Empresa empresa;
    
}

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
@Table(name = "TB_CARGO")
public class Cargo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4685519607025108415L;

	@Getter @Setter
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SQ_CARGO")
	@SequenceGenerator(name="SQ_CARGO", sequenceName="SQ_CARGO_COSEQCARGO")
	@Column(name="CO_SEQ_CARGO")
	private Long id;

	@Getter @Setter
	@Column(name="NO_CARGO", length=200, nullable = false)
	private String nome;

	@Getter @Setter
	@Column(name="DS_CARGO", length=1000)
	private String descricao;

	@Getter @Setter
	@Column(name="NU_CODIGO_CARGO", length=20)
	private String codigo;

	@Getter @Setter
	@Column(name="NU_PONTO")
	private Long ponto;
	
	@Getter @Setter
	@ManyToOne
	@JoinColumn(name="CO_EMPRESA", referencedColumnName="CO_SEQ_EMPRESA")
	private Empresa empresa;
	
	@Getter @Setter
	@ManyToOne
	@JoinColumn(name="CO_ESTRUTURA_ORGANIZACIONAL", referencedColumnName="CO_SEQ_ESTRUTURA_ORGANIZACIONAL")
	private EstruturaOrganizacional estruturaOrganizacional;
	

	public Cargo(String[] arrLinha) {
		
		
	}

	public Cargo(Long id, String nome, String descricao, String codigo, Long ponto, Empresa empresa,
			EstruturaOrganizacional estruturaOrganizacional) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.codigo = codigo;
		this.ponto = ponto;
		this.empresa = empresa;
		this.estruturaOrganizacional = estruturaOrganizacional;
	}
	public Cargo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
    
}

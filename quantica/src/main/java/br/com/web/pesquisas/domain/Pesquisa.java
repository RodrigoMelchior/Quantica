package br.com.web.pesquisas.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_PESQUISA")
public class Pesquisa implements Serializable{

	public Pesquisa(Long id) {
		super();
		this.id = id;
	}

	public Pesquisa() {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4685519607025108415L;

	@Getter @Setter
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SQ_PESQUISA")
	@SequenceGenerator(name="SQ_PESQUISA", sequenceName="SQ_PESQUISA_COSEQPESQUISA")
	@Column(name="CO_SEQ_PESQUISA")
	private Long id;

	@Getter @Setter
	@Column(name="NO_PESQUISA", length=200, nullable = false)
	private String nome;
	
	@Getter @Setter
	@Column(name="ST_HABILITAR_RELATORIO")
	private String habilitarRelatorio;

	@Getter @Setter
	@Column(name="DT_INICIO")
	private Date inicio;

	@Getter @Setter
	@Column(name="DT_TERMINO")
	private Date termino;

	@Getter @Setter
	@Column(name="DT_BASE")
	private Date dataBase;
	
	@Getter
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="pesquisa", orphanRemoval=true)
	private Set<Agrupador> agrupadores;
    
	@Getter
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="pesquisa", orphanRemoval=true)
	private Set<Painel> paineis;
    
	@Getter 
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="pesquisa", orphanRemoval=true)
	private Set<EmpresaPesquisa> empresas;
	
	@Getter 
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="pesquisa", orphanRemoval=true)
	private Set<ItemPesquisa> itens;

	@Getter 
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="pesquisa", orphanRemoval=true)
	private Set<PerguntaPesquisa> perguntas;
	
	@Getter 
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="pesquisa", orphanRemoval=true)
	private Set<Arquivo> arquivos;
	
	
	public void setAgrupadores(Set<Agrupador> agrupadores) {
		for (Agrupador agrupador : agrupadores) {
			agrupador.setPesquisa(this);
		}
		this.agrupadores = agrupadores;
	}

	public void setPaineis(Set<Painel> paineis) {
		for (Painel painel : paineis) {
			painel.setPesquisa(this);
		}
		this.paineis = paineis;
	}

	public void setEmpresas(Set<EmpresaPesquisa> empresas) {
		for (EmpresaPesquisa empresaPesquisa : empresas) {
			empresaPesquisa.setPesquisa(this);
		}
		this.empresas = empresas;
	}

	public void setItens(Set<ItemPesquisa> itens) {
		for (ItemPesquisa itemPesquisa : itens) {
		itemPesquisa.setPesquisa(this);	
		}
		this.itens = itens;
	}
	
	public void setPerguntas(Set<PerguntaPesquisa> perguntas) {
		for (PerguntaPesquisa PerguntaPesquisa : perguntas ) {
			PerguntaPesquisa.setPesquisa(this);	
		}
		this.perguntas = perguntas;
	}
	
}

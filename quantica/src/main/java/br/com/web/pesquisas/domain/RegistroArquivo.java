package br.com.web.pesquisas.domain;

import java.sql.Date;
import java.util.List;

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
@Table(name = "TB_REGISTRO_ARQUIVO")
@SequenceGenerator(name="SQ_REGIARQ_COSEQREGISTROARQUIV", sequenceName="SQ_REGIARQ_COSEQREGISTROARQUIV")
public class RegistroArquivo extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4685519607025108415L;

	@Getter @Setter
	@Id
	@Column(name="CO_SEQ_REGISTRO_ARQUIVO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SQ_REGIARQ_COSEQREGISTROARQUIV")
	private Long id;

	@Getter @Setter
	@ManyToOne
	@JoinColumn(name="CO_ARQUIVO", referencedColumnName="CO_SEQ_ARQUIVO")
	@JsonIgnore
	private Arquivo arquivo;
	
	@Getter @Setter
	@Column(name="NU_CODIGO_CARGO", length=20, nullable = false)
	private String codigoCargo;
	
	@Getter @Setter
	@Column(name="DS_TITULO_CARGO", length=200, nullable = false)
	private String tituloCargo;

	@Getter @Setter
	@Column(name="id_empregado", length=200, nullable = false)
	private String identificacaoEmpregado;
	
	@Getter @Setter
	@Column(name="DS_CARGA_HORARIA", length=200, nullable = false)
	private String cargaHoraria;
	
	@Getter @Setter
	@Column(name="DS_TITULO_FUNCAO", length=200, nullable = false)
	private String tituloFuncao;
	
	@Getter @Setter
	@Column(name="DS_MENOR_UNIDADE", length=200, nullable = false)
	private String menorUnidade;
	
	@Getter @Setter
	@Column(name="DT_ADMISSAO")
	private Date admissao;
	
	@Getter @Setter
	@Column(name="NU_POSICAO_SALARIAL", length=200, nullable = false)
	private String posicacaoSalarial;
	
	@Getter @Setter
	@Column(name="DS_LOCALIZACAO", length=200, nullable = false)
	private String localizacao;
	
	@Getter  
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="registroArquivo", orphanRemoval = true)
	private List<RegistroArquivoItem> registroArquivoItens;
	
	
	public RegistroArquivo(String[] arrLinha) {
		this.codigoCargo = arrLinha[0].toString();
		this.tituloCargo = arrLinha[1].toString();
		this.identificacaoEmpregado = arrLinha[2].toString();
		this.cargaHoraria = arrLinha[3].toString();
		this.tituloFuncao = arrLinha[4].toString();
		this.menorUnidade = arrLinha[5].toString();
		//this.admissao = arrLinha[6].toString();
		this.posicacaoSalarial = arrLinha[7].toString();
		this.localizacao = arrLinha[8].toString();
	}

	public RegistroArquivo() {
		super();
	}

	public RegistroArquivo(Arquivo arquivo, String codigoCargo, String tituloCargo, String identificacaoEmpregado,
			String cargaHoraria, String tituloFuncao, String menorUnidade, Date admissao, String posicacaoSalarial,
			String localizacao) {
		super();
		this.arquivo = arquivo;
		this.codigoCargo = codigoCargo;
		this.tituloCargo = tituloCargo;
		this.identificacaoEmpregado = identificacaoEmpregado;
		this.cargaHoraria = cargaHoraria;
		this.tituloFuncao = tituloFuncao;
		this.menorUnidade = menorUnidade;
		this.admissao = admissao;
		this.posicacaoSalarial = posicacaoSalarial;
		this.localizacao = localizacao;
	}

	public void setRegistroArquivoItens(List<RegistroArquivoItem> registroArquivoItens) {
		for (RegistroArquivoItem registroArquivoItem : registroArquivoItens) {
			registroArquivoItem.setRegistroArquivo(this);
		}
		this.registroArquivoItens = registroArquivoItens;
	}
	
	
	    
}

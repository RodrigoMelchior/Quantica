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

import br.com.web.pesquisas.domain.utils.TipoArquivoConverter;
import br.com.web.pesquisas.enuns.TipoArquivo;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_ARQUIVO")
public class Arquivo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4685519607025108415L;

	@Id
	@Getter @Setter
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SQ_ARQUIVO")
	@SequenceGenerator(name="SQ_ARQUIVO", sequenceName="SQ_AGRUPADOR_COSEQARQUIVO")
	@Column(name="CO_SEQ_ARQUIVO")
	private Long id;

	@Getter @Setter
	@Column(name="NO_ARQUIVO", length=200, nullable = false)
	private String nome;

	@Getter @Setter
	@ManyToOne
	@JoinColumn(name="CO_USUARIO", referencedColumnName="CO_SEQ_USUARIO")
	private Usuario usuario;

	
	@Getter @Setter
	@ManyToOne
	@JoinColumn(name="CO_PESQUISA", referencedColumnName="CO_SEQ_PESQUISA")
	private Pesquisa pesquisa;
	
	@Getter @Setter
	@Column(name="TP_ARQUIVO")
	@Convert(converter=TipoArquivoConverter.class)
	private TipoArquivo tipo;

	public Arquivo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Arquivo(TipoArquivo tipo) {
		super();
		this.tipo = tipo;
	}
	
	
	
}

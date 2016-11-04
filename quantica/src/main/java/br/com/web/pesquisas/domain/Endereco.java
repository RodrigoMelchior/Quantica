package br.com.web.pesquisas.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.web.pesquisas.domain.utils.TipoEnderecoConverter;
import br.com.web.pesquisas.enuns.TipoEndereco;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_ENDERECO")
public class Endereco implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -366385670248516372L;

	@Getter @Setter
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SQ_ENDERECO")
	@SequenceGenerator(name="SQ_ENDERECO", sequenceName="SQ_ENDERECO_COSEQENDERECO")
	@Column(name="CO_SEQ_ENDERECO")
	private Long id;

	@Getter @Setter
	@NotNull
	@JoinColumn(name="CO_MUNICIPIO",  referencedColumnName="CO_SEQ_MUNICIPIO")
	@ManyToOne
    private Municipio municipio;		
	
	@Getter @Setter
	@Size(min = 8, max = 8)
	@NotNull
	@Column(name="NU_CEP", length=8, nullable = false)
	private String cep;
	
	@Getter @Setter
	@Size(min = 1, max = 7)
	@NotNull
	@Column(name="NU_LOGRADOURO", length=7, nullable = true)
	private String numero;
	
	
    @Getter @Setter
    @Size(min = 1, max = 300)
    @NotNull
    @Column(name="DS_LOGRADOURO", length=300, nullable = false)
    private String logradouro;
	
	
	@Getter @Setter
	@Size(min = 1, max = 15)
	@NotNull
	@Column(name="DS_COMPLEMENTO", length=15, nullable = true)
	private String complemento;
	
	@Getter @Setter
	@NotNull
	@Column(name="TP_ENDERECO", nullable = false)
	@Convert(converter=TipoEnderecoConverter.class)
	private TipoEndereco tipoEndereco;
	
	@Getter @Setter
	@ManyToOne
	@NotNull
	@JoinColumn(name="CO_PESSOA", referencedColumnName="CO_SEQ_PESSOA")
	@JsonBackReference
	private Pessoa pessoa;
	
}

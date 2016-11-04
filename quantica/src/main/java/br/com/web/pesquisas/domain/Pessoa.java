package br.com.web.pesquisas.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_PESSOA")
@DiscriminatorColumn(name="TP_PESSOA")
@Inheritance(strategy =InheritanceType.JOINED)
public class Pessoa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4685519607025108415L;

	@Getter @Setter
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SQ_PESSOA")
	@SequenceGenerator(name="SQ_PESSOA", sequenceName="SQ_PESSOA_COSEQPESSOA")
	@Column(name="CO_SEQ_PESSOA")
	private Long id;

	@Getter @Setter
	@Size(min = 1, max = 100)
	@NotNull
	@Column(name="NO_PESSOA", length=100, nullable = false)
	private String nome;
	
	@Getter @Setter
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="pessoa")
	@JsonManagedReference
	private List<Telefone> telefones;
	
	@Getter @Setter
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="pessoa")
	@JsonManagedReference
	private List<Endereco> enderecos;

/*    @Getter(AccessLevel.NONE)
    @Setter
    @Column(name = "ST_ATIVO")
    @Convert(converter = BooleanToStringConverter.class)
    private Boolean ativo;*/
    
}

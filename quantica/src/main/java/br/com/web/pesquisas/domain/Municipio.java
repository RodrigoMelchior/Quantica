package br.com.web.pesquisas.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_MUNICIPIO")
public class Municipio implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1565551226510635748L;

	@Getter @Setter
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SQ_MUNICIPIO")
	@SequenceGenerator(name="SQ_MUNICIPIO", sequenceName="SQ_MUNICIPIO_COSEQMUNICIPIO")
	@Column(name="CO_SEQ_MUNICIPIO")
	private Long id;
	
	@Getter @Setter
	@Size(min = 1, max = 100)
	@NotNull
	@Column(name="NO_MUNICIPIO", length=100, nullable = false)
	private String nome;

    @Getter @Setter
    @NotNull
    @Column(name="CO_IBGE", nullable = false)
    private Integer codigoIBGE;	
	
	@Getter @Setter
	@ManyToOne(fetch = FetchType.EAGER)
	@NotNull
	@JoinColumn(name="CO_UF", referencedColumnName="CO_SEQ_UF")
	@JsonBackReference
	private Uf uf;		

}

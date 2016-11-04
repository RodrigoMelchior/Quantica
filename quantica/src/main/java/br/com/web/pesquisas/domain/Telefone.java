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

import br.com.web.pesquisas.domain.utils.TipoTelefoneConverter;
import br.com.web.pesquisas.enuns.TipoTelefone;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_TELEFONE")
public class Telefone implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3560059511752303456L;

	@Getter @Setter
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SQ_TELEFONE")
	@SequenceGenerator(name="SQ_TELEFONE", sequenceName="SQ_TELEFONE_COSEQTELEFONE")
	@Column(name="CO_SEQ_TELEFONE")
	private Long id;
	
	@Getter @Setter
	@Size(min = 2, max = 2)
	@Column(name="NU_DDD", length=2, nullable = false)
	private String ddd;

	@Getter @Setter
	@Size(min = 8, max = 9)
	@NotNull
	@Column(name="NU_TELEFONE", length=9, nullable = false)
	private String numero;
	
	@Getter @Setter
	@NotNull
	@Column(name="TP_TELEFONE", nullable = false)
	@Convert(converter=TipoTelefoneConverter.class)
	private TipoTelefone tipoTelefone;
	
	@Getter @Setter
	@NotNull
	@ManyToOne
	@JoinColumn(name="CO_PESSOA", referencedColumnName="CO_SEQ_PESSOA")
	@JsonBackReference
	private Pessoa pessoa;
}

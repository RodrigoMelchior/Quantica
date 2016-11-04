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
@Table(name = "TB_REGISTRO_ARQUIVO_ITEM")
@SequenceGenerator(name="sq_regarqitem_coseqregiarqitem", sequenceName="sq_regarqitem_coseqregiarqitem")
public class RegistroArquivoItem implements Serializable{

	private static final long serialVersionUID = 4685519607025108415L;
	
	@Id
	@Getter @Setter
	@Column(name="CO_SEQ_REGISTRO_ITEM")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sq_regarqitem_coseqregiarqitem")
	private Long id;

	@Getter @Setter
	@ManyToOne
	@JoinColumn(name="CO_REGISTRO_ARQUIVO", referencedColumnName="CO_SEQ_REGISTRO_ARQUIVO")
	private RegistroArquivo registroArquivo;
	
	@Getter @Setter
	@ManyToOne
	@JoinColumn(name="CO_ITEM", referencedColumnName="CO_SEQ_ITEM")
	private Item item;

	@Getter @Setter
	@Column(name="VL_REGISTRO")
	private String vlrRegistro;
	

	@Getter @Setter
	@Column(name="VL_VALOR")
	private String vlrValor;
	
}

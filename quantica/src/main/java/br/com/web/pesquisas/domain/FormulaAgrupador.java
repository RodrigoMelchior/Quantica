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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_FORMULA_AGRUPADOR")
public class FormulaAgrupador implements Serializable{

	private static final long serialVersionUID = 4685519607025108415L;

	@Getter @Setter
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SQ_FORMULA_AGRUPADOR")
	@SequenceGenerator(name="SQ_FORMULA_AGRUPADOR", sequenceName="SQ_FORMULAAGRUPADOR_COSEQFORMULAAGRUPADOR")
	@Column(name="CO_SEQ_FORMULA_AGRUPADOR")
	private Long id;

	@Getter @Setter
	@ManyToOne
	@JoinColumn(name="CO_AGRUPADOR")
	@JsonIgnore
	private Agrupador agrupador;

	@Getter @Setter
	@ManyToOne
	@JoinColumn(name="CO_ITEM", referencedColumnName="CO_SEQ_ITEM")
	private Item item;

}

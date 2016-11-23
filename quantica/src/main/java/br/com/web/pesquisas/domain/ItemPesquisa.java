package br.com.web.pesquisas.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
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
@Table(name = "RL_ITEM_PESQUISA")
public class ItemPesquisa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4685519607025108415L;

	@Getter @Setter
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SQ_ITEM_PESQUISA")
	@SequenceGenerator(name="SQ_ITEM_PESQUISA", sequenceName="SQ_ITEMPESQUISA_COSEQITEMPESQUISA")
	@Column(name="CO_SEQ_ITEMPESQUISA")
	private Long id;

	@Getter @Setter
	@Column(name="NU_ORDEM")
	private Long ordem;
	
	@Getter @Setter
	@ManyToOne
	@JoinColumn(name="CO_ITEM", referencedColumnName="CO_SEQ_ITEM")
	private Item item;

	@Getter @Setter
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="CO_PESQUISA", referencedColumnName="CO_SEQ_PESQUISA")
	@JsonIgnore
	private Pesquisa pesquisa;
	

}

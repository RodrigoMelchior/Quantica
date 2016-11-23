package br.com.web.pesquisas.domain;

import java.io.Serializable;
import java.util.Set;

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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "RL_PERGUNTA_PESQUISA")
public class PerguntaPesquisa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4685519607025108415L;

	@Getter @Setter
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SQ_PERGUNTA_PESQUISA")
	@SequenceGenerator(name="SQ_PERGUNTA_PESQUISA", sequenceName="SQ_PERGUNTAPESQUISA_COSEQPERGUNTAPESQUISA")
	@Column(name="CO_SEQ_PERGUNTAPESQUISA")
	private Long id;

	@Getter @Setter
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="CO_PERGUNTA", referencedColumnName="CO_SEQ_PERGUNTA")
	private Pergunta pergunta;

	@Getter @Setter
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="CO_PESQUISA", referencedColumnName="CO_SEQ_PESQUISA")
	@JsonIgnore
	private Pesquisa pesquisa;
	
	@Getter @Setter
	@Column(name="NU_ORDEM")
	private Long ordem;

	@Getter @Setter
	@Transient
	@JsonProperty
	private Resposta resposta;
	
	@Getter 
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="pergunta", orphanRemoval=true)
	private Set<Resposta> respostas;
	
}

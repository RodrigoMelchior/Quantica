package br.com.web.pesquisas.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_PERGUNTA")
public class Pergunta implements Serializable {

    /** 
     * 
     */
    private static final long serialVersionUID = -3751103684327967529L;

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PERGUNTA")
    @SequenceGenerator(name = "SQ_PERGUNTA", sequenceName = "SQ_PERGUNTA_COSEQPERGUNTA")
    @Column(name = "CO_SEQ_PERGUNTA")
    private Long id;

    @Getter
    @Setter
    @Size(min = 1, max = 500)
    @NotNull
    @Column(name = "DS_ENUNCIADO", length = 100, nullable = false)
    private String enunciado;
    
    @Getter @Setter
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="pergunta")
    @JsonIgnore
	private List<PerguntaPesquisa> pesquisas;

}

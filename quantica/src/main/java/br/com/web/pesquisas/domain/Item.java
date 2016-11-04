package br.com.web.pesquisas.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_ITEM")
public class Item implements Serializable {

    /** 
     * 
     */
    private static final long serialVersionUID = -3751103684327967529L;

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ITEM")
    @SequenceGenerator(name = "SQ_ITEM", sequenceName = "SQ_ITEM_COSEQITEM")
    @Column(name = "CO_SEQ_ITEM")
    private Long id;

    @Getter
    @Setter
    @Size(min = 1, max = 100)
    @NotNull
    @Column(name = "NO_ITEM", length = 100, nullable = false)
    private String nome;

    @Getter
    @Setter
    @Size(min = 1, max = 500)
    @NotNull
    @Column(name = "DS_ITEM", length = 500, nullable = false)
    private String descricao;

}

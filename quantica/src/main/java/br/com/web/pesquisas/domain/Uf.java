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

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_UF")
public class Uf implements Serializable {

    /** 
     * 
     */
    private static final long serialVersionUID = -3751103684327967529L;

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_UF")
    @SequenceGenerator(name = "SQ_UF", sequenceName = "SQ_UF_COSEQUF")
    @Column(name = "CO_SEQ_UF")
    private Long id;

    @Getter
    @Setter
    @Size(min = 2, max = 2)
    @NotNull
    @Column(name = "SG_UF", length = 2, unique = true, nullable = false)
    private String sigla;

    @Getter
    @Setter
    @Size(min = 1, max = 100)
    @NotNull
    @Column(name = "NO_UF", length = 100, nullable = false)
    private String nome;

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "uf")
    @JsonManagedReference
    private List<Municipio> municipios;
}

package br.com.web.pesquisas.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_PERMISSAO")
public class Permissao {
    
   
    public Permissao(){
        
    }
    
    public Permissao(Long id, String alias){
        this.id =  id;
        this.alias = alias;
    }
    
	
	public Permissao(Long id, String nome, String alias, List<Perfil> perfis, Funcionalidade funcionalidade) {
        super();
        this.id = id;
        this.nome = nome;
        this.alias = alias;
        this.perfis = perfis;
        this.funcionalidade = funcionalidade;
    }

    @Getter @Setter
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SQ_PERMISSAO")
	@SequenceGenerator(name="SQ_PERMISSAO", sequenceName="SQ_PERMISSAO_COSEQPERMISSAO")
	@Column(name="CO_SEQ_PERMISSAO")
	private Long id;

	@Getter @Setter
	@NotNull
	@Size(min = 5, max = 100)
	@Column(name="NO_PERMISSAO", length=100, unique = true, nullable = false)
	private String nome;

	@Getter @Setter
	@NotNull
	@Size(min = 5, max = 100)
	@Column(name="NO_ALIAS_PERMISSAO", length=100, unique = true, nullable = false)
	private String alias;
	
	@Getter @Setter
    @ManyToMany(mappedBy="permissoes")
    private List<Perfil> perfis;
	
	@Getter @Setter
	@ManyToOne
	@JoinColumn(name="CO_FUNCIONALIDADE", referencedColumnName="CO_SEQ_FUNCIONALIDADE")
	private Funcionalidade funcionalidade;
	
}

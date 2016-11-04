package br.com.web.pesquisas.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.web.pesquisas.domain.utils.BooleanToStringConverter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "TB_PERFIL")
public class Perfil {
	
	@Getter @Setter
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SQ_PERFIL")
	@SequenceGenerator(name="SQ_PERFIL", sequenceName="SQ_PERFIL_COSEQPERFIL")
	@Column(name="CO_SEQ_PERFIL")
	private Long id;	
	
	@Getter @Setter
	@NotNull
	@Size(min = 5, max = 100)
	@Column(name="NO_PERFIL", length=50, unique = true, nullable = false)
	private String nome;	
	
	@Getter(AccessLevel.NONE) @Setter
	@Column(name="ST_ATIVO")
	@Convert(converter=BooleanToStringConverter.class)
	private Boolean ativo;
	
	public Boolean isAtivo(){
		return ativo;
	}	
	
	@Getter @Setter
    @ManyToMany(mappedBy="perfis")
    private List<Usuario> usuarios;
	
	@Getter @Setter
	@ManyToMany
	@JoinTable(name="RL_PERFIL_PERMISSAO", joinColumns={@JoinColumn(name="CO_PERFIL")}, inverseJoinColumns={@JoinColumn(name="CO_PERMISSAO")})

	private List<Permissao> permissoes;
	
}

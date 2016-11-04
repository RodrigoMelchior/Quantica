package br.com.web.pesquisas.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_USUARIO")
public class Usuario implements Serializable{

	private static final long serialVersionUID = 2538756692401259399L;
	
	@Getter @Setter
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SQ_USUARIO")
	@SequenceGenerator(name="SQ_USUARIO", sequenceName="SQ_USUARIO_COSEQUSUARIO")
	@Column(name="CO_SEQ_USUARIO")
	private Long id;
	
	@Getter @Setter
	@NotNull
	@Pattern(regexp="^[a-z0-9]*$")
	@Column(name="NO_LOGIN", length=50, unique = true, nullable = false)
	private String login;
	
	@Getter @Setter
	@Column(name="DS_SENHA_HASH", length=60, nullable = false)
	private String senha;
	
	@Getter @Setter
	@Size(max=20)
	@Column(name="DS_CHAVE_ATIVACAO", length=20)
	private String chaveAtivacao;
	
	@Getter @Setter
	@Size(max=20)
	@Column(name="DS_CHAVE_TROCA_SENHA", length=20)
	private String chaveTrocaSenha;
	
	@Getter @Setter
	@Column(name="DT_TROCA_SENHA")
	private LocalDateTime dataHoraTrocaSenha;
	
	@Getter @Setter
	@Column(name="ST_ATIVO")
	private String ativo;
	
	@Getter @Setter
	@Column(name="ST_TROCA_SENHA")
	private String trocaSenha;
	
	@Getter @Setter
	@ManyToOne
	@JoinColumn(name="CO_EMPRESA", referencedColumnName="CO_SEQ_EMPRESA")
	private Empresa empresa;
	
	@Getter @Setter
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="RL_USUARIO_PERFIL", joinColumns={@JoinColumn(name="CO_USUARIO")}, inverseJoinColumns={@JoinColumn(name="CO_PERFIL")})
	private List<Perfil> perfis;
	
/*	@Getter @Setter
	@OneToOne
	private PessoaFisica pessoaFisica;*/
}

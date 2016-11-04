package br.com.web.pesquisas.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.dom4j.tree.AbstractEntity;

import br.com.web.pesquisas.domain.utils.BooleanToStringConverter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_FUNCIONALIDADE")
public class Funcionalidade extends AbstractEntity{

	private static final long serialVersionUID = 4407971881539571041L;

	@Getter @Setter
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SQ_FUNCIONALIDADE")
	@SequenceGenerator(name="SQ_FUNCIONALIDADE",schema="proserv",  sequenceName="SQ_PERFIL_COSEQ")
	@Column(name="CO_SEQ_FUNCIONALIDADE")
	private Long id;	

	@Getter @Setter
	@NotNull
	@Size(min = 5, max = 100)
	@Column(name="NO_FUNCIONALIDADE", length=100, unique = true, nullable = false)
	private String nome;
	
	@Getter(AccessLevel.NONE) @Setter
	@Column(name="ST_ATIVO")
	@Convert(converter=BooleanToStringConverter.class)
	private Boolean ativo;
	
	public Boolean isAtivo(){
		return ativo;
	}
	
	@Getter @Setter
	@OneToMany(mappedBy = "funcionalidade")
	private List<Permissao> permissoes;
}

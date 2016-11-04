package br.com.web.pesquisas.domain;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.dom4j.tree.AbstractEntity;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import br.com.web.pesquisas.security.listener.RevisaoListener;
import lombok.Getter;
import lombok.Setter;

@Entity
@RevisionEntity(RevisaoListener.class)
@Table(name = "TB_REVISAO")
public class Revisao extends AbstractEntity{

	@Getter	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_REVISAO")
	@SequenceGenerator(name = "SQ_REVISAO", schema="proserv", sequenceName = "SQ_REVISAO_COSEQREVISAO", allocationSize = 1)
	@Column(name = "CO_SEQ_REVISAO")
	@RevisionNumber
	private Long id;

	@Getter	@Setter
	@RevisionTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_REVISAO")
	private Date timestamp;

	@Getter	@Setter
	@Column(name = "CO_USUARIO")
	private Long idUsuario;

}
package br.com.web.pesquisas.domain;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

	private static final long serialVersionUID = 7867371867842238214L;
	

	
	public abstract Serializable getId();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof AbstractEntity)){
			return false;
		}
		AbstractEntity entity = (AbstractEntity) obj;
		if (this == entity)
			return true;
		if (entity == null)
			return false;

		if (getId() == null) {
			if (entity.getId() != null)
				return false;
		} else if (!getId().equals(entity.getId()))
			return false;
		return true;
	}
	
}

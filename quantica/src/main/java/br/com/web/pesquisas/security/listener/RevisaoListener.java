package br.com.web.pesquisas.security.listener;

import java.util.Date;

import br.com.web.pesquisas.domain.Revisao;
import br.com.web.pesquisas.security.SecurityUtils;

public class RevisaoListener implements org.hibernate.envers.RevisionListener {
	@Override
	public void newRevision(Object objetoRevisao) {
		Revisao revisao = (Revisao) objetoRevisao;
		
		if (SecurityUtils.isAuthenticated()){
			//Usuario  usuario = SecurityUtils.getUsuarioLogado();
			//revisao.setIdUsuario(usuario.getId());
		}
		revisao.setTimestamp(new Date());
	}
}
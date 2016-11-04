package br.com.web.pesquisas.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.com.web.pesquisas.domain.Usuario;
import br.com.web.pesquisas.web.rest.dto.FiltroUsuarioDTO;
import br.com.web.pesquisas.web.rest.dto.UsuarioDTO;

public interface UsuarioService extends CrudService<Usuario, Long>{

	public Usuario recuperarDadosUsuarioAutenticado();
	
	public Usuario logar(Usuario usuario);

	public Page<UsuarioDTO> pesquisarComFiltro(PageRequest pageRequest, FiltroUsuarioDTO filtro);

}
package br.com.web.pesquisas.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.web.pesquisas.domain.Usuario;
import br.com.web.pesquisas.repository.UsuarioRepository;
import br.com.web.pesquisas.security.SecurityUtils;
import br.com.web.pesquisas.service.UsuarioService;
import br.com.web.pesquisas.web.rest.dto.FiltroUsuarioDTO;
import br.com.web.pesquisas.web.rest.dto.UsuarioDTO;

@Service
public class UsuarioServiceImpl extends CrudServiceImpl<Usuario, Long, UsuarioRepository> implements UsuarioService{


    @Override
	public Usuario logar(Usuario usuario){
        return repository.findOneByLoginAndSenha(usuario.getLogin(), usuario.getSenha());
    }
	
    @Override
    @Transactional(readOnly = true)
    public Usuario recuperarDadosUsuarioAutenticado(){
        User user = SecurityUtils.getUsuarioLogado();
        return repository.recuperarUsuarioAutenticado(user.getUsername());
    }

	@Override
	public Page<UsuarioDTO> pesquisarComFiltro(PageRequest pageRequest, FiltroUsuarioDTO filtro) {
		return repository.consultarColaboradorPorFiltro(pageRequest, filtro);
	}

}

package br.com.web.pesquisas.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.NoRepositoryBean;

import br.com.web.pesquisas.web.rest.dto.FiltroUsuarioDTO;
import br.com.web.pesquisas.web.rest.dto.UsuarioDTO;

@NoRepositoryBean
public interface UsuarioRepositoryCustom {
    Page<UsuarioDTO> consultarColaboradorPorFiltro(PageRequest pageRequest, FiltroUsuarioDTO filtro);
}

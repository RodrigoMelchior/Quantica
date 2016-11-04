package br.com.web.pesquisas.repository;
import java.util.List;

import br.com.web.pesquisas.domain.Permissao;


public interface PermissaoRepository extends CustomRepository<Permissao, Long> {

    List<Permissao> recuperarPermissoesAtivasUsuario(Long idUsuario);
    
    
}

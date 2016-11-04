package br.com.web.pesquisas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.web.pesquisas.domain.Usuario;
import br.com.web.pesquisas.repository.custom.UsuarioRepositoryCustom;

public interface UsuarioRepository extends CustomRepository<Usuario, Long>, UsuarioRepositoryCustom{

    Optional<Usuario> findOneByLogin(String login);
    
    Usuario findOneByLoginAndSenha(String login, String senha); 

    @Query("select usuario from Usuario usuario where login = :login")
    Usuario recuperarUsuarioAutenticado(@Param("login") String login);
    
}

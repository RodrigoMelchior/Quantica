package br.com.web.pesquisas.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.web.pesquisas.domain.Usuario;
import br.com.web.pesquisas.enuns.SimNao;
import br.com.web.pesquisas.repository.PermissaoRepository;
import br.com.web.pesquisas.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
@Slf4j
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {


    
    private UsuarioRepository usuarioRepository;
    
    private PermissaoRepository permissaoRepository;
    
    @Autowired
    public UserDetailsService(UsuarioRepository usuarioRepository, PermissaoRepository permissaoRepository){
    	this.usuarioRepository = usuarioRepository;
    	this.permissaoRepository = permissaoRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
        log.debug("Authenticating {}", login);
        String lowercaseLogin = login.toLowerCase();
        Optional<Usuario> userFromDatabase = usuarioRepository.findOneByLogin(lowercaseLogin);
        return userFromDatabase.map(usuario -> {
            if (!usuario.getAtivo().equalsIgnoreCase(SimNao.SIM.getCode())) {
                throw new UserNotActivatedException("User " + lowercaseLogin + " was not activated");
            }
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();/*permissaoRepository.recuperarPermissoesAtivasUsuario(usuario.getId()).stream()
                    .map(permissao -> new SimpleGrantedAuthority(permissao.getAlias()))
                .collect(Collectors.toList());*/
            
            // Todo Incluir os perfis entre as authorities
    
            return new org.springframework.security.core.userdetails.User(lowercaseLogin,
                usuario.getSenha(),
                grantedAuthorities);
        }).orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the " +
        "database"));
    }
}
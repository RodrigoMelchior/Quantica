package br.com.web.pesquisas.domain.specification;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import br.com.web.pesquisas.domain.Usuario;


public final class UsuarioSpecification {
    
    private UsuarioSpecification(){}
    
    public static Specification<Usuario> likeLogin(String login) {
        return (root, query, cb) -> {
            return cb.like(
                    cb.lower(root.<String>get("login")),
                    "%" + login.toLowerCase() + "%");    
        };
    }
    
    public static Specification<Usuario> perfil(Integer perfil) {
        return (root, query, cb) -> {
            return cb.equal(root.get("perfil"), perfil);    
        };
    }
    
    public static Specification<Usuario> empresa(Integer empresa) {
        return (root, query, cb) -> {
            return cb.equal(root.get("empresa"),empresa);    
        };
    }
    
    public static Specification<Usuario> ativo(Boolean ativo) {
        return (root, query, cb) -> {
            return (Predicate) cb.equal(root.<Boolean>get("ativo"), ativo);            
        };
    }
    
}

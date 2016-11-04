package br.com.web.pesquisas.domain.specification;

import org.springframework.data.jpa.domain.Specification;

import br.com.web.pesquisas.domain.Empresa;


public final class EmpresaSpecification {
    
    private EmpresaSpecification(){}
    
    public static Specification<Empresa> likeNome(String nome) {
        return (root, query, cb) -> {
            return cb.like(cb.lower(root.<String>get("nome")),nome.toLowerCase());    
        };
    }
}

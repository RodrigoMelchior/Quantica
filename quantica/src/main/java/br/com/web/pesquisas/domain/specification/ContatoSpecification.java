package br.com.web.pesquisas.domain.specification;

import org.springframework.data.jpa.domain.Specification;

import br.com.web.pesquisas.domain.Contato;


public final class ContatoSpecification {
    
    private ContatoSpecification(){}
    
    public static Specification<Contato> igualEmpresa(String empresa) {
    	return (root, query, cb) -> {
            return cb.equal(root.<String>get("empresa"), new Long(empresa));    
        };
    }
}

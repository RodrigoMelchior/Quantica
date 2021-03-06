package br.com.web.pesquisas.domain.specification;

import org.springframework.data.jpa.domain.Specification;

import br.com.web.pesquisas.domain.Cargo;


public final class CargoSpecification {
    
    private CargoSpecification(){}
    
    public static Specification<Cargo> igualEmpresa(String empresa) {
    	return (root, query, cb) -> {
            return cb.equal(root.<String>get("empresa"), new Long(empresa));    
        };
    }
}

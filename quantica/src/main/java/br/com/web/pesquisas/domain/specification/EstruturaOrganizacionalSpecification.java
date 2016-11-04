package br.com.web.pesquisas.domain.specification;

import org.springframework.data.jpa.domain.Specification;

import br.com.web.pesquisas.domain.EstruturaOrganizacional;


public final class EstruturaOrganizacionalSpecification {
    
    private EstruturaOrganizacionalSpecification(){}
    
    public static Specification<EstruturaOrganizacional> igualEmpresa(String empresa) {
    	return (root, query, cb) -> {
            return cb.equal(root.<String>get("empresa"), new Long(empresa));    
        };
    }
}

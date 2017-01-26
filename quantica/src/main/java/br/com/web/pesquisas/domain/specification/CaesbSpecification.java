package br.com.web.pesquisas.domain.specification;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.criteria.Selection;

import org.springframework.data.jpa.domain.Specification;

import br.com.web.pesquisas.domain.Caesb;


public final class CaesbSpecification {
    
    private CaesbSpecification(){}
    
    public static Specification<Caesb> max() {
    	return (root, query, cb) -> {
            query.multiselect((Selection<?>) cb.max(root.<BigDecimal>get("sal1")));
            return null;
        };
    }
    
    public static Specification<Caesb> inicar() {
    	return (root, query, cb) -> {
            return cb.equal(
                    cb.lower(root.<String>get("tipo")), cb.lower(root.<String>get("tipo")));
        };
    }
    
    public static Specification<Caesb> tipo(String tipo) {
    	return (root, query, cb) -> {
            return cb.equal(
                    cb.lower(root.<String>get("tipo")), tipo.toLowerCase());
        };
    }

    public static Specification<Caesb> empresa(List<String> empresa) {
        return (root, query, cb) -> {
            return root.<String>get("empresa").in(empresa);    
        };
    }
    
    public static Specification<Caesb> cod(String cod) {
        return (root, query, cb) -> {
            return cb.equal(root.<String>get("nome"), cod);    
        };
    }
    
}

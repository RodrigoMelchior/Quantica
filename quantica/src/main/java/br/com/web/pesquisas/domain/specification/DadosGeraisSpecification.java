package br.com.web.pesquisas.domain.specification;

import java.math.BigDecimal;

import javax.persistence.criteria.Selection;

import org.springframework.data.jpa.domain.Specification;

import br.com.web.pesquisas.domain.DadosGerais;


public final class DadosGeraisSpecification {
    
    private DadosGeraisSpecification(){}
    
    public static Specification<DadosGerais> max() {
    	return (root, query, cb) -> {
            query.multiselect((Selection<?>) cb.max(root.<BigDecimal>get("sal1")));
            return null;
        };
    }
    
    public static Specification<DadosGerais> inicar() {
    	return (root, query, cb) -> {
            return cb.equal(
                    cb.lower(root.<String>get("setor")), cb.lower(root.<String>get("setor")));
        };
    }
    
    public static Specification<DadosGerais> setor(String setor) {
    	return (root, query, cb) -> {
            return cb.equal(
                    cb.lower(root.<String>get("setor")), setor.toLowerCase());
        };
    }

    public static Specification<DadosGerais> empresa(String empresa) {
        return (root, query, cb) -> {
            return cb.equal(root.<String>get("empresa"), empresa);    
        };
    }
    
    public static Specification<DadosGerais> nivel(String nivel) {
        return (root, query, cb) -> {
            return cb.equal(root.<String>get("nivel"), nivel);    
        };
    }
    
    public static Specification<DadosGerais> cargo(String cargo) {
        return (root, query, cb) -> {
        	return cb.equal(root.<String>get("cargo"), cargo); 
        };
    }
}

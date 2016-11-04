package br.com.web.pesquisas.domain.specification;

import java.math.BigDecimal;
import java.sql.Date;

import org.springframework.data.jpa.domain.Specification;

import br.com.web.pesquisas.domain.Inpc;


public final class InpcSpecification {
    
    private InpcSpecification(){}
    
    public static Specification<Inpc> dataInicioFim(Date dataInicio, Date dataFim) {
        return (root, query, cb) -> {
            return cb.between(root.<Date>get("mesAno"), dataInicio, dataFim);    
        };
    }
    
    public static Specification<Inpc> dataInicio(Date dataInicio) {
        return (root, query, cb) -> {
            return cb.greaterThanOrEqualTo(root.<Date>get("mesAno"), dataInicio);    
        };
    }
    
    public static Specification<Inpc> dataFim(Date dataFim) {
        return (root, query, cb) -> {
        	return cb.lessThanOrEqualTo(root.<Date>get("mesAno"), dataFim); 
        };
    }
    
    public static Specification<Inpc> valor() {
        return (root, query, cb) -> {
        	return cb.greaterThanOrEqualTo(root.<BigDecimal>get("indice"), new BigDecimal(0)); 
        };
    }
}

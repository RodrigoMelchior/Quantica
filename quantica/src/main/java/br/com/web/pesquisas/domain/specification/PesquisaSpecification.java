package br.com.web.pesquisas.domain.specification;

import java.sql.Date;

import org.springframework.data.jpa.domain.Specification;

import br.com.web.pesquisas.domain.Pesquisa;


public final class PesquisaSpecification {
    
    private PesquisaSpecification(){}
    
    public static Specification<Pesquisa> nome(String nome) {
    	return (root, query, cb) -> {
            return cb.like(
                    cb.lower(root.<String>get("nome")), nome.toLowerCase());
        };
    }

    public static Specification<Pesquisa> maiorDataInicio(Date dataInicio) {
        return (root, query, cb) -> {
            return cb.greaterThanOrEqualTo(root.<Date>get("inicio"), dataInicio);    
        };
    }
    
    public static Specification<Pesquisa> maiorDataFim(Date dataFim) {
        return (root, query, cb) -> {
            return cb.greaterThanOrEqualTo(root.<Date>get("termino"), dataFim);    
        };
    }
    
    public static Specification<Pesquisa> menorDataInicio(Date dataInicio) {
        return (root, query, cb) -> {
        	return cb.lessThanOrEqualTo(root.<Date>get("inicio"), dataInicio); 
        };
    }
    
    public static Specification<Pesquisa> menorDataFim(Date dataFim) {
        return (root, query, cb) -> {
        	return cb.lessThanOrEqualTo(root.<Date>get("termino"), dataFim); 
        };
    }
}

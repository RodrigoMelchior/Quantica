package br.com.web.pesquisas.domain.specification;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import br.com.web.pesquisas.domain.Item;


public final class ItemSpecification {
    
    private ItemSpecification(){}
    
    public static Specification<Item> likeNome(String nome) {
        return (root, query, cb) -> {
            return cb.like(
                    cb.lower(root.<String>get("nome")), nome.toLowerCase());    
        };
    }

    public static Specification<Item> descricao(String descricao) {
        return (root, query, cb) -> {
            return (Predicate) cb.equal(root.<String>get("descricao"), descricao);            
        };
    }
}

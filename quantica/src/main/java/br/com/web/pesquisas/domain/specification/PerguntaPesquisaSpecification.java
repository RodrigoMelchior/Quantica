package br.com.web.pesquisas.domain.specification;

import org.springframework.data.jpa.domain.Specification;

import br.com.web.pesquisas.domain.PerguntaPesquisa;


public final class PerguntaPesquisaSpecification {
    
    private PerguntaPesquisaSpecification(){}
    
    public static Specification<PerguntaPesquisa> equalPesquisa(Long id) {
        return (root, query, cb) -> {
 //       	query.orderBy(cb.asc(root.get("ordem")));
            return cb.equal(root.<String>get("pesquisa"), id);    
        };
    }
}

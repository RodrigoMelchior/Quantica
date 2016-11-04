package br.com.web.pesquisas.domain.specification;

import org.springframework.data.jpa.domain.Specification;

import br.com.web.pesquisas.domain.Pergunta;


public final class PerguntaSpecification {
    
    private PerguntaSpecification(){}
    
    public static Specification<Pergunta> likeNome(String enunciado) {
        return (root, query, cb) -> {
            return cb.like(
                    cb.lower(root.<String>get("enunciado")), enunciado.toLowerCase());    
        };
    }
}

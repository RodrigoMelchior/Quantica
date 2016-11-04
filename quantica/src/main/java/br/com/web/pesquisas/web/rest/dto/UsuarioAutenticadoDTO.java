package br.com.web.pesquisas.web.rest.dto;


import java.io.Serializable;
import java.util.List;

import lombok.Builder;
import lombok.Getter;


@Builder
public class UsuarioAutenticadoDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    @Getter
    private Long id;

    @Getter
    private String login;

    @Getter
    private String nome;

}

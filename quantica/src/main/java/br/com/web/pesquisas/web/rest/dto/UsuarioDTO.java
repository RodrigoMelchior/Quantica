package br.com.web.pesquisas.web.rest.dto;

import br.com.web.pesquisas.domain.Empresa;
import lombok.Getter;
import lombok.Setter;

public class UsuarioDTO extends PaginacaoDTO{

    private static final long serialVersionUID = 2765980336298047301L;
    
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String login;
    
    @Getter
    @Setter
    private String perfil;
    
    @Getter
    @Setter
    private String empresa;
    
    @Getter
    @Setter
    private String status;

	public UsuarioDTO() {
		super();
	}
	public UsuarioDTO(Long id, String login, String perfil, String empresa, String status) {
		super();
		this.id = id;
		this.login = login;
		this.perfil = perfil;
		this.empresa = empresa;
		this.status = status;
	}
	public UsuarioDTO(Object[] usu) {
		this.id = new Long(usu[0].toString());
		this.login = usu[1].toString();
		this.perfil = usu[2].toString();
		this.empresa = usu[3].toString();
		this.status = usu[4].toString();
	}
        
}

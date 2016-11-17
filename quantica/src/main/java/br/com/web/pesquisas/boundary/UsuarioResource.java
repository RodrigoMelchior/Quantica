package br.com.web.pesquisas.boundary;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.web.pesquisas.domain.Usuario;
import br.com.web.pesquisas.service.UsuarioService;
import br.com.web.pesquisas.util.MensagemUtil;
import br.com.web.pesquisas.web.rest.dto.FiltroUsuarioDTO;
import br.com.web.pesquisas.web.rest.dto.UsuarioDTO;
import br.com.web.pesquisas.web.rest.util.HeadersUtil;

@RestController
@CrossOrigin()
@RequestMapping(path = "/api/usuarios")
public class UsuarioResource extends EntityServiceBasedRestController<Usuario, Long, UsuarioService>{

    @Autowired
    private MensagemUtil mensagemUtil;

    @RequestMapping(path = "/autenticado", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> recuperarDadosUsuarioAutenticado(){
        Usuario usuarioAutenticado = service.recuperarDadosUsuarioAutenticado();
        return new ResponseEntity<Usuario>(usuarioAutenticado, HttpStatus.OK);
    }

    @RequestMapping(path = "/logar", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Usuario logar(@RequestBody Usuario usuario){
    	Usuario user = service.logar(usuario);
		return user;
    }
    
    @RequestMapping(path = "/alteracaosenha", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void alterarSenha(){
        
    }
    
    @RequestMapping(path = "/perdasenha/inicio", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void solicitarRedefinicaoSenha(){
        
    }
    
    @RequestMapping(path = "/perdasenha/finalizacao", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void redefinirSenha(){
        
    }
    
    @RequestMapping(path = "/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UsuarioDTO>> pesquisar(@RequestBody FiltroUsuarioDTO filtro) {
    	
    	 Assert.isTrue(filtro.getPage() > 0, PAGE_INDEX_MUST_BE_GREATER_THAN_0);
    	 String nome = filtro.getUsuario();
         if (StringUtils.isEmpty(filtro.getUsuario())){        	 
        	 nome = "";
         }
         filtro.setUsuario(nome + "%");
//    	 if (
//         		StringUtils.isEmpty(filtro.getUsuario()) && 
//         		filtro.getPerfil() == null &&
//         		filtro.getEmpresa() == null &&
//         		filtro.getAtivo() == null
//         	) {
//             throw new BusinessException(mensagemUtil.getMessage(Mensagem.PARAMETRO_PESQUISA_OBRIGATORIO.getKey()));
//         }
         
         Page<UsuarioDTO> pageResult= this.service.pesquisarComFiltro(new PageRequest(filtro.getPage() - 1, filtro.getLimit()), filtro );;
         
         return new ResponseEntity<List<UsuarioDTO>>(pageResult.getContent(),
        		 HeadersUtil.generatePaginationHttpHeaders(pageResult.getTotalElements()), HttpStatus.OK);
        
    } 
    
   /* @RequestMapping(path = "/perfil-usuario",method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<List<TipoColaborador>>  buscaTipoColaborador(){
        
        List<TipoColaborador> tipoColaborador = tipoColaboradorService.buscaTipoColaborador();
        if (tipoColaborador != null) {
            return new ResponseEntity(tipoColaborador, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }*/

}

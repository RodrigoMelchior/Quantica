package br.com.web.pesquisas.boundary;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.web.pesquisas.domain.Arquivo;
import br.com.web.pesquisas.service.ArquivoService;
import br.com.web.pesquisas.util.MensagemUtil;

@RestController
@CrossOrigin()
@RequestMapping(path = "/api/arquivos")
public class ArquivoResource extends EntityServiceBasedRestController<Arquivo, Long, ArquivoService>{

	
	@Autowired
	ArquivoService arquivoService;
	
    @Autowired
    private MensagemUtil mensagemUtil;
    
    @RequestMapping(path = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String salvar(
    								   @RequestPart("idUsuario")  String idUsuario,
    								   @RequestPart("idPesquisa") String idPesquisa,
    								   @RequestPart("tipoArquivo") String tipoArquivo,
    								   @RequestPart("file") MultipartFile file) throws IOException {   	
    	
    	return arquivoService. uploadArquivo(file, idUsuario, idPesquisa, tipoArquivo);
    }
        
}





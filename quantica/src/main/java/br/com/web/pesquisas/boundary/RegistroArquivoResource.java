package br.com.web.pesquisas.boundary;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.web.pesquisas.domain.ItemPesquisa;
import br.com.web.pesquisas.domain.RegistroArquivo;
import br.com.web.pesquisas.service.ArquivoService;
import br.com.web.pesquisas.service.ItemPesquisaService;
import br.com.web.pesquisas.service.RegistroArquivoService;

@RestController
@CrossOrigin(origins = "http://localhost:3100")
@RequestMapping(path = "/api/registro-arquivos")
public class RegistroArquivoResource extends EntityServiceBasedRestController<RegistroArquivo, Long, RegistroArquivoService>{


	@Autowired
	ArquivoService arquivoService;
	
	@Autowired
	ItemPesquisaService itemPesquisaService;
	
    @RequestMapping(path = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public  @ResponseBody String salvar(
    								   @RequestPart("idUsuario")  String idUsuario,
    								   @RequestPart("idPesquisa") String idPesquisa,
    								   @RequestPart("tipoArquivo") String tipoArquivo,
    								   @RequestPart("file") MultipartFile file) throws IOException {   	
    	
    	try {
    		service.uploadArquivo(file, idUsuario, idPesquisa, tipoArquivo);
    		return "{'retorno':'OK'}"; //new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			return "{'retorno':'Not Found'}"; // new ResponseEntity(HttpStatus.NOT_FOUND);
		}
    	
    }
    
    @RequestMapping(path = "/arquivo/{idArquivo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<RegistroArquivo> buscaRegistroArquivoPorArquivo(@PathVariable Long idArquivo) throws IOException { 
    	return service.findByArquivo(idArquivo);
    }
    
    @RequestMapping(path = "/item-pesquisa/{idPesquisa}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<ItemPesquisa> buscaItemPesquisaPorPesquisa(@PathVariable Long idPesquisa) throws IOException { 
    	return itemPesquisaService.consultarPorPesquisa(idPesquisa);
    }
}





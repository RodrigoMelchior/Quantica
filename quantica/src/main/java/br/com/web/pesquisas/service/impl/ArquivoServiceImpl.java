package br.com.web.pesquisas.service.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.web.pesquisas.domain.Arquivo;
import br.com.web.pesquisas.domain.Cargo;
import br.com.web.pesquisas.domain.Empresa;
import br.com.web.pesquisas.domain.ItemPesquisa;
import br.com.web.pesquisas.domain.RegistroArquivo;
import br.com.web.pesquisas.domain.RegistroArquivoItem;
import br.com.web.pesquisas.enuns.TipoArquivo;
import br.com.web.pesquisas.repository.ArquivoRepository;
import br.com.web.pesquisas.service.ArquivoService;
import br.com.web.pesquisas.service.CargoService;
import br.com.web.pesquisas.service.ItemPesquisaService;
import br.com.web.pesquisas.service.ItemService;
import br.com.web.pesquisas.service.PesquisaService;
import br.com.web.pesquisas.service.RegistroArquivoService;
import br.com.web.pesquisas.service.UsuarioService;

@Service
public class ArquivoServiceImpl extends CrudServiceImpl<Arquivo, Long, ArquivoRepository> implements ArquivoService{
	
	@Autowired
	private CargoService cargoServico;
	
	@Autowired
	private ItemService itemServico;
	
	@Autowired
	private ItemPesquisaService itemPesquisaService;
	
	@Autowired
	private RegistroArquivoService registroArquivoService;
	
	@Autowired
	private PesquisaService pesquisaService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public String uploadArquivo(MultipartFile file, String idUsuario, String idPesquisa, String tipoArquivo) {
		
		String retorno = "";
		InputStream inputStream = null;
	    BufferedReader br = null;
	    String linha = "";
	    String csvDivisor = ";";
	    boolean isCabecalho = true;
	    Arquivo arquivo = new Arquivo();
	    arquivo.setTipo(TipoArquivo.forValue(tipoArquivo));
	    arquivo.setPesquisa(pesquisaService.findById(new Long(idPesquisa)));
	    arquivo.setUsuario(usuarioService.findById(new Long(idUsuario)));
	    arquivo.setNome(file.getName());
	    
	    try {
	    	inputStream = file.getInputStream();
			br = new BufferedReader(new InputStreamReader(inputStream));
			Empresa emp = new Empresa(new Long(100));
		    while ((linha = br.readLine()) != null) {
		    	if (!isCabecalho){
		            String[] arrLinha = linha.split(csvDivisor);		            
		            if (arquivo.getTipo().compareTo(TipoArquivo.CARGA_ESTRUTURA_DE_CARGOS) == 1){
		            	salvarCargos(arrLinha, arquivo);
		            }
		            if (arquivo.getTipo().compareTo(TipoArquivo.CARGA_DADOS_DA_EMPRESA) == 1){
		            	salvarDadosEmpresa(arrLinha,arquivo);
//		            	
		            }
		    	}
		    	isCabecalho = false;
	        }
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        if (br != null) {
	            try {
	                br.close();
	                inputStream.close();
	                retorno = "Arquivo carregado com sucesso!";
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
		return retorno;
		
    }

	private void salvarDadosEmpresa(String[] arrLinha, Arquivo arquivo) {
		List<ItemPesquisa> itens = itemPesquisaService.consultarPorPesquisa(new Long(arquivo.getPesquisa().getId()));
		RegistroArquivo registroArquivo = new RegistroArquivo(arrLinha);
		List<RegistroArquivoItem> listaItens = new ArrayList<>();
		registroArquivo.setArquivo(arquivo);
		
		Integer posicao = 8;
		
		for (ItemPesquisa item : itens) {
			posicao++;
	    	RegistroArquivoItem registroItem = new RegistroArquivoItem();
	    	registroItem.setItem(item.getItem());
	    	//registroItem.setRegistroArquivo(registroArquivo);
	    	registroItem.setVlrValor(arrLinha[posicao].toString());
	    	listaItens.add(registroItem);
		}		
		registroArquivo.setRegistroArquivoItens(listaItens);
		//registroArquivoService.salvarRegistroArquivo(registroArquivo);
	}

	private void salvarCargos(String[] arrLinha, Arquivo arquivo) {
		Cargo cargo = new Cargo();
        cargo.setCodigo(arrLinha[0]);
        cargo.setNome(arrLinha[1]);
        cargo.setPonto(new Long(arrLinha[2]));
        cargo.setEmpresa(arquivo.getUsuario().getEmpresa());
        
        cargoServico.create(cargo);
	}

	
}

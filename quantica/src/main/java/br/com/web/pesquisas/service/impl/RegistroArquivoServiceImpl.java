package br.com.web.pesquisas.service.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import br.com.web.pesquisas.domain.Arquivo;
import br.com.web.pesquisas.domain.Cargo;
import br.com.web.pesquisas.domain.Empresa;
import br.com.web.pesquisas.domain.ItemPesquisa;
import br.com.web.pesquisas.domain.RegistroArquivo;
import br.com.web.pesquisas.domain.RegistroArquivoItem;
import br.com.web.pesquisas.enuns.TipoArquivo;
import br.com.web.pesquisas.repository.RegistroArquivoRepository;
import br.com.web.pesquisas.service.ArquivoService;
import br.com.web.pesquisas.service.CargoService;
import br.com.web.pesquisas.service.EmpresaService;
import br.com.web.pesquisas.service.InpcService;
import br.com.web.pesquisas.service.ItemPesquisaService;
import br.com.web.pesquisas.service.PesquisaService;
import br.com.web.pesquisas.service.RegistroArquivoService;
import br.com.web.pesquisas.service.UsuarioService;

@Service
@Transactional
public class RegistroArquivoServiceImpl extends CrudServiceImpl<RegistroArquivo, Long, RegistroArquivoRepository> implements RegistroArquivoService{


	@Autowired
	private CargoService cargoServico;
	
	@Autowired
	private ItemPesquisaService itemPesquisaService;
	
	@Autowired
	private PesquisaService pesquisaService;
	
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ArquivoService arquivoService;
	
	@Autowired
	private InpcService inpcService;

	@Autowired
	private EmpresaService empresaService;
	
	private Arquivo arquivoSalvo;
	
	private List<ItemPesquisa> listaItens;
	
	@Override
	public void uploadArquivo(MultipartFile file, String idUsuario, String idPesquisa, String tipoArquivo) throws IOException {
		
	    String linha = "";
	    boolean isCabecalho = true;
	    Empresa empresa = empresaService.buscaEmpresaPorUsuario(new Long(idUsuario));
	    BigDecimal indReajuste = inpcService.calculaIndiceReajuste(empresa.getId(), new Long(idPesquisa));
	    
	    salvarArquivo(idUsuario, idPesquisa, file, tipoArquivo);
	    
	    try {
	    	BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
		    while ((linha = br.readLine()) != null) {
		    	if (!isCabecalho){
		            String[] arrLinha = linha.split(";");		            
		            if (arquivoSalvo.getTipo().compareTo(TipoArquivo.CARGA_ESTRUTURA_DE_CARGOS) == 1){
		            	salvarCargos(arrLinha);
		            }
		            if (arquivoSalvo.getTipo().compareTo(TipoArquivo.CARGA_DADOS_DA_EMPRESA) == 1){
		            	if(this.listaItens  == null){
		            		setListaItens(itemPesquisaService.consultarPorPesquisa(new Long(idPesquisa))); 
		            	}
		            	salvarRegistroArquivo(arrLinha, indReajuste);
		            }
		    	}
		    	isCabecalho = false;
	        }
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
    }

	private void salvarArquivo(String idUsuario, String idPesquisa, MultipartFile file, String tipoArquivo) {
	    Arquivo arquivo = new Arquivo();
	    arquivo.setTipo(TipoArquivo.forValue(tipoArquivo));
	    arquivo.setPesquisa(pesquisaService.findById(new Long(idPesquisa)));
	    arquivo.setUsuario(usuarioService.findById(new Long(idUsuario)));
	    arquivo.setNome(file.getName());
	    
	    setArquivoSalvo(arquivoService.create(arquivo));
	}

	@Transactional(readOnly = true)
	private void salvarRegistroArquivo(String[] arrLinha, BigDecimal indReajuste) {
		RegistroArquivo registroArquivo = new RegistroArquivo(arrLinha);
		List<RegistroArquivoItem> listaItens = new ArrayList<>();
		registroArquivo.setArquivo(getArquivoSalvo());
		
		Integer posicao = 8;
		
		for (ItemPesquisa item : getListaItens()) {
			posicao++;
	    	RegistroArquivoItem registroItem = new RegistroArquivoItem();
	    	registroItem.setItem(item.getItem());
	    	registroItem.setVlrRegistro(arrLinha[posicao].toString());
	    	registroItem.setVlrValor(indReajuste.multiply(new BigDecimal(arrLinha[posicao].toString())).toString());
	    	listaItens.add(registroItem);
		}		
		registroArquivo.setRegistroArquivoItens(listaItens);
		create(registroArquivo);
	}

	private void salvarCargos(String[] arrLinha) {
		Cargo cargo = new Cargo();
        cargo.setCodigo(arrLinha[0]);
        cargo.setNome(arrLinha[1]);
        cargo.setPonto(new Long(arrLinha[2]));
        cargo.setEmpresa(getArquivoSalvo().getUsuario().getEmpresa());
        
        cargoServico.create(cargo);
	}

	public Arquivo getArquivoSalvo() {
		if(this.arquivoSalvo == null){
			this.arquivoSalvo = new Arquivo();
		}
		return arquivoSalvo;
	}

	public void setArquivoSalvo(Arquivo arquivoSalvo) {
		this.arquivoSalvo = arquivoSalvo;
	}

	public List<ItemPesquisa> getListaItens() {
		if(this.listaItens == null){
			this.listaItens = new ArrayList<>();
		}
		return listaItens;
	}

	public void setListaItens(List<ItemPesquisa> listaItens) {
		this.listaItens = listaItens;
	}
	
	


}

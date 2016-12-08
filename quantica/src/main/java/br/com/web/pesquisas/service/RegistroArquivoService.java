package br.com.web.pesquisas.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import br.com.web.pesquisas.domain.RegistroArquivo;

public interface RegistroArquivoService extends CrudService<RegistroArquivo, Long>{

	void uploadArquivo(MultipartFile file, String idUsuario, String idPesquisa, String tipoArquivo) throws IOException;

	List<RegistroArquivo> findByArquivo(Long idArquivo);

	
}
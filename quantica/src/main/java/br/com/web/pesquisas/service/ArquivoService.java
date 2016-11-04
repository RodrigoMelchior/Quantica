package br.com.web.pesquisas.service;

import org.springframework.web.multipart.MultipartFile;

import br.com.web.pesquisas.domain.Arquivo;

public interface ArquivoService extends CrudService<Arquivo, Long>{

	String uploadArquivo(MultipartFile file, String idUsuario, String idPesquisa, String tipoArquivo);

}
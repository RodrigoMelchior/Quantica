package br.com.web.pesquisas.repository;

import java.util.List;

import br.com.web.pesquisas.domain.Arquivo;
import br.com.web.pesquisas.domain.RegistroArquivo;

public interface RegistroArquivoRepository extends CustomRepository<RegistroArquivo, Long>{
	List<RegistroArquivo> findByArquivo(Arquivo arquirvo);
}

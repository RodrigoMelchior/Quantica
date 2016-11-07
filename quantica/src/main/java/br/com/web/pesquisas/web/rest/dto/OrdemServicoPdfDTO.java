
package br.com.web.pesquisas.web.rest.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class OrdemServicoPdfDTO implements Serializable{
    
    private static final long serialVersionUID = -3058213848140674122L;
    
    @Getter 
    @Setter
    private String numero;

    @Getter 
    @Setter
    private String fiscalAdministrativo;
    
    @Getter 
    @Setter
    private String fiscalTecnico;
    
    @Getter 
    @Setter
    private String gerenteTecnico;
    /*
    @Getter 
    @Setter
    private List<UnidadeCliente> unidadesAtendidas;*/
    
    @Getter 
    @Setter
    private String dataInicioExecucao;
    
    @Getter 
    @Setter
    private String dataFimExecucao;
    
    @Getter 
    @Setter
    private String dataAbertura;

/*    @Getter 
    @Setter
    private List<ListaServicoPdfDTO> servicos;*/
    
    @Getter 
    @Setter
    private String informacoesComplementares;
    
    @Getter 
    @Setter
    private Double totalValor = 0.0;
    
  /*  public OrdemServicoPdfDTO(OrdemServico ordemServico) {
        this.numero = ordemServico.getNumero();
        this.fiscalAdministrativo = ordemServico.getFiscalAdministrativo().getTitular().getNome();
        this.fiscalTecnico = ordemServico.getFiscalTecnico().getPessoaFisica().getNome();
        this.gerenteTecnico = ordemServico.getGerenteTecnico().getNome();
        this.dataInicioExecucao = ordemServico.getDataInicioExecucao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.dataFimExecucao = ordemServico.getDataFimExecucao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        if (ordemServico.getDataAbertura() != null) {
            this.dataAbertura = ordemServico.getDataAbertura().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
        this.informacoesComplementares = ordemServico.getInformacoesComplementares();
        
        if (ordemServico.getUnidadesAtendidas() != null) {
            this.unidadesAtendidas = new ArrayList<>(ordemServico.getUnidadesAtendidas());
        }
        if (ordemServico.getAtividades() != null) {
            this.servicos = ordemServico.getAtividades()
                    .stream().map((ordemServicoAtividade) -> new ListaServicoPdfDTO(ordemServicoAtividade))
                    .collect(Collectors.toList());
            Collections.sort(servicos, new Comparator<ListaServicoPdfDTO>() {
                @Override
                public int compare(ListaServicoPdfDTO o1, ListaServicoPdfDTO o2) {
                    String str1 = o1.getAtividade() + " - " + o1.getDescricao();
                    String str2 = o2.getAtividade() + " - " + o2.getDescricao();
                    return str1.compareToIgnoreCase(str2);
                }
            });
            this.servicos.forEach((dto) -> totalValor += dto.getValor());
        }
    }*/
}

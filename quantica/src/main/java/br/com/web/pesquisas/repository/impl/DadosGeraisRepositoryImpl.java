package br.com.web.pesquisas.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.NoRepositoryBean;

import br.com.web.pesquisas.repository.custom.DadosGeraisRepositoryCustom;
import br.com.web.pesquisas.web.rest.dto.FiltroDadosGeraisDTO;
import br.com.web.pesquisas.web.rest.dto.SalDTO;

@NoRepositoryBean
public class DadosGeraisRepositoryImpl implements DadosGeraisRepositoryCustom {
	
    @Autowired
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
	@Override
	public SalDTO maiorSalario(FiltroDadosGeraisDTO filtro) {
    	
    	Query query = (TypedQuery<SalDTO>) entityManager.createNativeQuery(sqlMax(filtro));
        setParameter(query, filtro);
        List<Object[]> sal = query.getResultList();
        
        return montarListaDTO(sal);
	}

    @SuppressWarnings("unchecked")
	@Override
	public SalDTO menorSalario(FiltroDadosGeraisDTO filtro) {
    	
    	Query query = (TypedQuery<SalDTO>) entityManager.createNativeQuery(sqlMin(filtro));
        setParameter(query, filtro);
        List<Object[]> sal = query.getResultList();
        
        return montarListaDTO(sal);
	}

    @SuppressWarnings("unchecked")
	@Override
	public SalDTO somatorio(FiltroDadosGeraisDTO filtro) {
    	
    	Query query = (TypedQuery<SalDTO>) entityManager.createNativeQuery(sqlSum(filtro));
        setParameter(query, filtro);
        List<Object[]> sal = query.getResultList();
        
        return montarListaDTO(sal);
	}

    private String sqlMax(FiltroDadosGeraisDTO filtro) {
        StringBuilder query = new StringBuilder();
        query.append(" SELECT max(dg.sal1) as sal1, max(dg.sal2) as sal2, max(dg.sal3) as sal3, max(dg.sal4) as sal4, max(dg.sal5) as sal5");
        query.append(" FROM salarial.dados_gerais dg ");
        query.append(" where 1= 1 ");
        query.append(montarClausulas(filtro));        
        return query.toString();
    }
    
    private String sqlMin(FiltroDadosGeraisDTO filtro) {
        StringBuilder query = new StringBuilder();
        query.append(" SELECT min(dg.sal1) as sal1, min(dg.sal2) as sal2, min(dg.sal3) as sal3, min(dg.sal4) as sal4, min(dg.sal5) as sal5");
        query.append(" FROM salarial.dados_gerais dg ");
        query.append(" where 1= 1 ");
        query.append(montarClausulas(filtro));        
        return query.toString();
    }
    
    private String sqlSum(FiltroDadosGeraisDTO filtro) {
        StringBuilder query = new StringBuilder();
        query.append(" SELECT sum(dg.sal1) as sal1, sum(dg.sal2) as sal2, sum(dg.sal3) as sal3, sum(dg.sal4) as sal4, sum(dg.sal5) as sal5");
        query.append(" FROM salarial.dados_gerais dg ");
        query.append(" where 1= 1 ");
        query.append(montarClausulas(filtro));        
        return query.toString();
    }
    
    private Object montarClausulas(FiltroDadosGeraisDTO filtro) {
        StringBuilder query = new StringBuilder();
        
        if(!StringUtils.isEmpty(filtro.getSetor())){
    		query.append(" and upper(dg.setor) = upper(:setor) ");
        }
        if(filtro.getEmpresa() != null){
    		query.append(" and dg.empresa in :empresa ");
        }
        if(!StringUtils.isEmpty(filtro.getNivel())){
    		query.append(" and upper(dg.nivel) = upper(:nivel) ");
        }
        if(!StringUtils.isEmpty(filtro.getCargo())){
    		query.append(" and upper(dg.cargo) = upper(:cargo) ");
        }
        return query.toString();
    }


    private void setParameter(Query query, FiltroDadosGeraisDTO filtro) {
    	if(!StringUtils.isEmpty(filtro.getSetor())){
        	    query.setParameter("setor", filtro.getSetor());
        }
    	if(filtro.getEmpresa() != null){
    	    query.setParameter("empresa", filtro.getEmpresa());
    	}
    	if(!StringUtils.isEmpty(filtro.getNivel())){
    	    query.setParameter("nivel", filtro.getNivel());
    	}
    	if(!StringUtils.isEmpty(filtro.getCargo())){
    	    query.setParameter("cargo", filtro.getCargo());
    	}
    
    }
    
    private SalDTO montarListaDTO(List<Object[]> salMax) {
        SalDTO salDTO = new SalDTO();
        for (Object[] sal : salMax) {
        	salDTO = new SalDTO(sal);
        }
        return salDTO;
    }
    
}

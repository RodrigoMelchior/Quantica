package br.com.web.pesquisas.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.NoRepositoryBean;

import br.com.web.pesquisas.repository.custom.CaesbRepositoryCustom;
import br.com.web.pesquisas.web.rest.dto.FiltroCaesbDTO;
import br.com.web.pesquisas.web.rest.dto.SalDTO;

@NoRepositoryBean
public class CaesbRepositoryImpl implements CaesbRepositoryCustom {
	
    @Autowired
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
	@Override
	public SalDTO maiorSalario(FiltroCaesbDTO filtro) {
    	
    	Query query = (TypedQuery<SalDTO>) entityManager.createNativeQuery(sqlMax(filtro));
        setParameter(query, filtro);
        List<Object[]> sal = query.getResultList();
        
        return montarListaDTO(sal);
	}

    @SuppressWarnings("unchecked")
	@Override
	public SalDTO menorSalario(FiltroCaesbDTO filtro) {
    	
    	Query query = (TypedQuery<SalDTO>) entityManager.createNativeQuery(sqlMin(filtro));
        setParameter(query, filtro);
        List<Object[]> sal = query.getResultList();
        
        return montarListaDTO(sal);
	}

    @SuppressWarnings("unchecked")
	@Override
	public SalDTO somatorio(FiltroCaesbDTO filtro) {
    	
    	Query query = (TypedQuery<SalDTO>) entityManager.createNativeQuery(sqlSum(filtro));
        setParameter(query, filtro);
        List<Object[]> sal = query.getResultList();
        
        return montarListaDTO(sal);
	}

    private String sqlMax(FiltroCaesbDTO filtro) {
        StringBuilder query = new StringBuilder();
        query.append(" SELECT max(dg.sal1) as sal1, max(dg.sal2) as sal2, max(dg.sal3) as sal3");
        query.append(" FROM salarial.caesb dg ");
        query.append(" where 1= 1 ");
        query.append(montarClausulas(filtro));        
        return query.toString();
    }
    
    private String sqlMin(FiltroCaesbDTO filtro) {
        StringBuilder query = new StringBuilder();
        query.append(" SELECT min(dg.sal1) as sal1, min(dg.sal2) as sal2, min(dg.sal3) as sal3");
        query.append(" FROM salarial.caesb dg ");
        query.append(" where 1= 1 ");
        query.append(montarClausulas(filtro));
        query.append(" and dg.sal1 > 0 and dg.sal2 > 0 and dg.sal3 > 0 ");
        return query.toString();
    }
    
    private String sqlSum(FiltroCaesbDTO filtro) {
        StringBuilder query = new StringBuilder();
        query.append(" SELECT sum(dg.sal1) as sal1, sum(dg.sal2) as sal2, sum(dg.sal3) as sal3");
        query.append(" FROM salarial.caesb dg ");
        query.append(" where 1= 1 ");
        query.append(montarClausulas(filtro));        
        return query.toString();
    }
    
    private Object montarClausulas(FiltroCaesbDTO filtro) {
        StringBuilder query = new StringBuilder();
        
        if(!StringUtils.isEmpty(filtro.getTipo())){
    		query.append(" and upper(dg.tipo) = upper(:tipo) ");
        }
        if(filtro.getEmpresa() != null){
    		query.append(" and dg.empresa in :empresa ");
        }
        if(!StringUtils.isEmpty(filtro.getCod())){
    		query.append(" and upper(dg.nome) = upper(:cod) ");
        }
        return query.toString();
    }


    private void setParameter(Query query, FiltroCaesbDTO filtro) {
    	if(!StringUtils.isEmpty(filtro.getTipo())){
        	    query.setParameter("tipo", filtro.getTipo());
        }
    	if(filtro.getEmpresa() != null){
    	    query.setParameter("empresa", filtro.getEmpresa());
    	}
    	if(!StringUtils.isEmpty(filtro.getCod() )){
    	    query.setParameter("cod", filtro.getCod());
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

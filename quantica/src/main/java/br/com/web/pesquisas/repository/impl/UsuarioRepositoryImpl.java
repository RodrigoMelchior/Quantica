package br.com.web.pesquisas.repository.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.NoRepositoryBean;

import br.com.web.pesquisas.repository.custom.UsuarioRepositoryCustom;
import br.com.web.pesquisas.web.rest.dto.FiltroUsuarioDTO;
import br.com.web.pesquisas.web.rest.dto.UsuarioDTO;

@NoRepositoryBean
public class UsuarioRepositoryImpl implements UsuarioRepositoryCustom {
    
    @Autowired
    private EntityManager entityManager;
    
    @Override
	public Page<UsuarioDTO> consultarColaboradorPorFiltro(PageRequest pageRequest, FiltroUsuarioDTO filtro) {
        Page<UsuarioDTO> pageResult = null;
        BigInteger totalRegistro = recuperarTotalregistro(filtro);
        
        Query query = (TypedQuery<UsuarioDTO>) entityManager.createNativeQuery(montarSqlFiltro(filtro))
                .setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize())
                .setMaxResults(pageRequest.getPageSize());
        setParameter(query, filtro);
        List<Object[]> usuarios = query.getResultList();
            
        pageResult = new PageImpl<UsuarioDTO>(montarListaDTO(usuarios), pageRequest, totalRegistro.longValue());
        
        return pageResult;
    
    }

    private BigInteger recuperarTotalregistro(FiltroUsuarioDTO filtro) {
        Query query = entityManager.createNativeQuery(sqlCont(filtro));
        setParameter(query, filtro);
        return (BigInteger) query.getSingleResult();
    }

    private String montarSqlFiltro(FiltroUsuarioDTO filtro) {
        StringBuilder query = new StringBuilder();
        
        query.append(" SELECT u.co_seq_usuario, u.no_login, p.no_perfil, e.no_empresa, u.st_ativo " );
        query.append(" FROM salarial.tb_usuario u ");
        query.append("join salarial.rl_usuario_perfil rl ON u.co_seq_usuario = rl.co_usuario ");
        query.append("join salarial.tb_perfil p ON rl.co_perfil = p.co_seq_perfil ");
        query.append("join salarial.tb_empresa e ON u.co_empresa = e.co_seq_empresa ");
        query.append(" where 1= 1 ");
        query.append(montarClausulas(filtro));        
        query.append(" order by u.no_login ");
        
        return query.toString();
    }
    
    private String sqlCont(FiltroUsuarioDTO filtro) {
        StringBuilder query = new StringBuilder();
        query.append(" SELECT count(*) ");
        query.append(" FROM salarial.tb_usuario u ");
        query.append("join salarial.rl_usuario_perfil rl ON u.co_seq_usuario = rl.co_usuario ");
        query.append("join salarial.tb_perfil p ON rl.co_perfil = p.co_seq_perfil ");
        query.append("join salarial.tb_empresa e ON u.co_empresa = e.co_seq_empresa ");
        query.append(" where 1= 1 ");
        query.append(montarClausulas(filtro));        
        return query.toString();
    }
  
    private Object montarClausulas(FiltroUsuarioDTO filtro) {
        StringBuilder query = new StringBuilder();
        
        if(!StringUtils.isEmpty(filtro.getUsuario())){
    		query.append(" and upper(u.no_login) like :login ");
        }
        if(!(null == filtro.getPerfil())){
        	query.append(" and rl.co_perfil = :idPerfil ");
        }
        if(!(null == filtro.getEmpresa())){
        	query.append(" and u.co_empresa = :idEmpresa ");
        }
        if(!StringUtils.isEmpty(filtro.getAtivo())){
            query.append(" and u.st_ativo = :ativo ");
        }
        return query.toString();
    }


    private void setParameter(Query query, FiltroUsuarioDTO filtro) {
        if(!(null == filtro.getPerfil())){
            query.setParameter("idPerfil", filtro.getPerfil());
        }
        if(!(null == filtro.getEmpresa())){
            query.setParameter("idEmpresa", filtro.getEmpresa());
        }
        if(!StringUtils.isEmpty(filtro.getUsuario())){
            query.setParameter("login", "%"+filtro.getUsuario().toUpperCase()+"%");
        }
        if(!StringUtils.isEmpty(filtro.getAtivo())){
            query.setParameter("ativo", filtro.getAtivo());
        }

    }
    
    private List<UsuarioDTO> montarListaDTO(List<Object[]> usuario) {
        List<UsuarioDTO> listaDTO = new ArrayList<>();
        for (Object[] usu : usuario) {
        	UsuarioDTO funcionarioCtis = new UsuarioDTO(usu);
            listaDTO.add(funcionarioCtis);
        }
        return listaDTO;
    }

}

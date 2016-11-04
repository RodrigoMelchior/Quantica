package br.com.web.pesquisas.service.impl;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import br.com.web.pesquisas.repository.CustomRepository;
import br.com.web.pesquisas.service.CrudService;

@Transactional(readOnly = true)
public class CrudServiceImpl<T, ID extends Serializable, R extends CustomRepository<T, ID>> implements
        CrudService<T, ID> {

    private static final String RESOURCE_CAN_T_BE_NULL = "Resource can't be null";
	private static final String RESOURCE_IDS_CAN_T_BE_NULL = "Resource ids can't be null";
	private static final String PAGE_REQUEST_CAN_T_BE_NULL = "page request can't be null";
	private static final String RESOURCE_ID_CAN_T_BE_NULL = "Resource ID can't be null";
	
	@Autowired	
    protected R repository;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public T create(T resource) {
        Assert.notNull(resource, RESOURCE_CAN_T_BE_NULL);
        validateCreate(resource);
        return repository.save(resource);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public T update(T resource) {
        Assert.notNull(resource, RESOURCE_CAN_T_BE_NULL);
        validateUpdate(resource);
        return repository.save(resource);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void delete(T resource) {
        Assert.notNull(resource, RESOURCE_CAN_T_BE_NULL);
        validateDelete(resource);
        repository.delete(resource);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void delete(ID id) {
        Assert.notNull(id, RESOURCE_ID_CAN_T_BE_NULL);
        validateDelete(id);
        repository.delete(id);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public T findById(ID id) {
        Assert.notNull(id, RESOURCE_ID_CAN_T_BE_NULL);
        return repository.findOne(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> findByIds(Set<ID> ids) {
        Assert.notNull(ids, RESOURCE_IDS_CAN_T_BE_NULL);
        return repository.findAll(ids);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> findAll(Sort sort) {
        return repository.findAll(sort);
    }
    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<T> findAll(Pageable pageRequest) {
        Assert.notNull(pageRequest, PAGE_REQUEST_CAN_T_BE_NULL);
        return repository.findAll(pageRequest);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long count() {
        return repository.count();
    }
    
    
    @SuppressWarnings("unchecked")
    @Override
    public Page<T> findBySpecification(Specification<T> specification, Pageable pageRequest) {
        return repository.findAll(specification, pageRequest);
    };
    
    
    
    protected void validateCreate(T resource){};
    
    protected void validateUpdate(T resource){};
    
    protected void validateDelete(T resource){};
    
    protected void validateDelete(ID id){}

    
}
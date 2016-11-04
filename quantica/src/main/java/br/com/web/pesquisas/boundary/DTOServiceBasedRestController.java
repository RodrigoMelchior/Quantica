package br.com.web.pesquisas.boundary;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.web.pesquisas.exception.NotFoundException;
import br.com.web.pesquisas.mapper.OrikaBeanMapper;
import br.com.web.pesquisas.service.CrudService;
import br.com.web.pesquisas.web.rest.util.HeadersUtil;

public abstract class DTOServiceBasedRestController<T, E, ID extends Serializable, S extends CrudService<E, ID>> implements
        RestController<T, ID> {

    private static final String PAGE_INDEX_MUST_BE_GREATER_THAN_0 = "Page index must be greater than 0";
	private static final String ID_CANNOT_BE_NULL = "id cannot be null";

	
    @Autowired
	protected  S service;

    @Autowired
    protected OrikaBeanMapper mapper;
    
    
    @SuppressWarnings("unchecked")
    private Class<T> getDTOClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @SuppressWarnings("unchecked")
    private Class<E> getEntityClass() {
        return (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }    

    /**
     * {@inheritDoc}
     */
    @Override
    public T create(@RequestBody @Valid T resource) {
    	E entity = mapper.map(resource, getEntityClass());
        return mapper.map(this.service.create(entity), getDTOClass());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T update(@PathVariable ID id, @RequestBody @Valid T resource) {
        Assert.notNull(id, ID_CANNOT_BE_NULL);

        E retreivedEntity = service.findById(id);
        if (retreivedEntity == null) {
            throw new NotFoundException();
        }
        
        E entityToUpdate = mapper.map(resource, getEntityClass());

        return (T) mapper.map(this.service.update(entityToUpdate), getDTOClass());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<List<T>> findAll(@RequestParam(value = "order", required = false, defaultValue = "ASC") String direction,
            @RequestParam(value = "sortFields", required = false) String properties) {
       List<E> entities = null;
        
        if (direction.isEmpty()) {
            entities = this.service.findAll();
        } else {
            Assert.notNull(properties);
            entities = this.service.findAll(new Sort(Sort.Direction.fromString(direction.toUpperCase()), properties.split(",")));
        }
 
        return new ResponseEntity<List<T>>(mapper.mapAsList(entities, getDTOClass()), HeadersUtil.generatePaginationHttpHeaders(entities.size()), HttpStatus.OK);
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<List<T>> findPaginated(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                 @RequestParam(value = "limit", required = false, defaultValue = "10") Integer size,
                                 @RequestParam(value = "sort", required = false, defaultValue = "") String direction,
                                 @RequestParam(value = "sortFields", required = false) String properties) {
        Assert.isTrue(page > 0, PAGE_INDEX_MUST_BE_GREATER_THAN_0);
        Assert.isTrue(direction.isEmpty() || direction.equalsIgnoreCase(Sort.Direction.ASC.toString()) || direction.equalsIgnoreCase(Sort.Direction.DESC.toString()), "Direction should be ASC or DESC");
        Page<E> pageResult = null;
        if (direction.isEmpty()) {
            pageResult =  this.service.findAll(new PageRequest(page - 1, size));
        } else {
            Assert.notNull(properties);
            pageResult =  this.service.findAll(new PageRequest(page - 1, size, new Sort(Sort.Direction.fromString(direction.toUpperCase()), properties.split(","))));
        }
        return new ResponseEntity<List<T>>(mapper.mapAsList(pageResult.getContent(), getDTOClass()), HeadersUtil.generatePaginationHttpHeaders(pageResult.getTotalElements()), HttpStatus.OK);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T findById(@PathVariable ID id) {
        E entity = (E) this.service.findById(id);
        if (entity == null) {
            throw new NotFoundException();
        }
        return mapper.map(entity, getDTOClass());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(@PathVariable ID id) {
        E entity = (E) this.service.findById(id);
        if (entity == null) {
            throw new NotFoundException();
        }
        this.service.delete(entity);
    }
}
package br.com.web.pesquisas.boundary;
import java.io.Serializable;
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
import br.com.web.pesquisas.service.CrudService;
import br.com.web.pesquisas.web.rest.util.HeadersUtil;

public abstract class EntityServiceBasedRestController<T, ID extends Serializable, S extends CrudService<T, ID>> implements
        RestController<T, ID> {

    protected static final String PAGE_INDEX_MUST_BE_GREATER_THAN_0 = "Page index must be greater than 0";
	protected static final String ID_CANNOT_BE_NULL = "id cannot be null";

	
    @Autowired
	protected S service;


    /**
     * {@inheritDoc}
     */
    @Override
    public T create(@RequestBody T resource) {
        return (T) this.service.create(resource);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T update(@PathVariable ID id, @RequestBody @Valid T resource) {
        Assert.notNull(id, ID_CANNOT_BE_NULL);

        T retreivedResource = this.findById(id);
        if (retreivedResource == null) {
            throw new NotFoundException();
        }

        return (T) this.service.update(resource);
    }

    /**
     * {@inheritDoc}
     */

	@Override
    public ResponseEntity<List<T>> findAll(@RequestParam(value = "order", required = false, defaultValue = "ASC") String direction,
            @RequestParam(value = "sortFields", required = false) String properties) {
        List<T> entities = null;
        
        if (direction.isEmpty()) {
            entities = this.service.findAll();
        } else {
            Assert.notNull(properties);
            entities = this.service.findAll(new Sort(Sort.Direction.fromString(direction.toUpperCase()), properties.split(",")));
        }
        return new ResponseEntity<List<T>>(entities, HeadersUtil.generatePaginationHttpHeaders(entities.size()), HttpStatus.OK);
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<List<T>> findPaginated(@RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
                                 @RequestParam(value = "limit", required = true, defaultValue = "10") Integer size,
                                 @RequestParam(value = "order", required = false, defaultValue = "") String direction,
                                 @RequestParam(value = "sortFields", required = false) String properties) {
        Assert.isTrue(page > 0, PAGE_INDEX_MUST_BE_GREATER_THAN_0);
        Assert.isTrue(direction.isEmpty() || direction.equalsIgnoreCase(Sort.Direction.ASC.toString()) || direction.equalsIgnoreCase(Sort.Direction.DESC.toString()), "Direction should be ASC or DESC");
        Page<T> pageResult = null;
        if (direction.isEmpty()) {
            pageResult =  this.service.findAll(new PageRequest(page - 1, size));
        } else {
            Assert.notNull(properties);
            pageResult =  this.service.findAll(new PageRequest(page - 1, size, new Sort(Sort.Direction.fromString(direction.toUpperCase()), properties.split(","))));
        }
        return new ResponseEntity<List<T>>(pageResult.getContent(), HeadersUtil.generatePaginationHttpHeaders(pageResult.getTotalElements()), HttpStatus.OK);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T findById(@PathVariable ID id) {
        T resource = (T) this.service.findById(id);
        if (resource == null) {
            throw new NotFoundException();
        }
        return resource;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(@PathVariable ID id) {
        T resource = this.findById(id);
        this.service.delete(resource);
    }
}
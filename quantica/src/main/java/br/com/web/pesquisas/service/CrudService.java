package br.com.web.pesquisas.service;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

public interface CrudService<T, ID extends Serializable> {

    T create(T resource);

    T update(T resource);

    void delete(T resource);

    void delete(ID id);

    T findById(ID id);
    
    List<T> findByIds(Set<ID> ids);

    List<T> findAll();

    Page<T> findAll(Pageable pageRequest);
    
    List<T> findAll(Sort sort);
    
    Page<T> findBySpecification(Specification<T> specification, Pageable pageRequest);

    Long count();
}
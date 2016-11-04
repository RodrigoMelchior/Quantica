package br.com.web.pesquisas.boundary;
import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.codahale.metrics.annotation.Timed;

public interface RestController<T, ID extends Serializable> {

    @RequestMapping(method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @Timed
    T create(@RequestBody @Valid T resource);

    @RequestMapping(value = "{id}", method = RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Timed
    T update(@PathVariable ID id, @RequestBody @Valid T resource);

    @RequestMapping(method = RequestMethod.GET, params = "paging=false", produces=MediaType.APPLICATION_JSON_VALUE)
    @Timed
    ResponseEntity<List<T>> findAll(@RequestParam(value = "order", required = false, defaultValue = "ASC") String direction,
                                    @RequestParam(value = "sortFields", required = false) String properties);

    @RequestMapping(method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    @Timed
    ResponseEntity<List<T>> findPaginated(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                          @RequestParam(value = "limit", required = false, defaultValue = "10") Integer size,
                          @RequestParam(value = "order", required = false, defaultValue = "ASC") String direction,
                          @RequestParam(value = "sortFields", required = false) String properties);

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Timed
    T findById(@PathVariable ID id);


    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Timed
    void delete(@PathVariable ID id);

}
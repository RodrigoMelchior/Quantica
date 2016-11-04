package br.com.web.pesquisas.web.rest.util;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;

import java.net.URI;
import java.net.URISyntaxException;

public class PaginationUtil {

    public static HttpHeaders generatePaginationHttpHeaders(Page<?> page)
        throws URISyntaxException {

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", "" + page.getTotalElements());
        return headers;
    }
    
}

package br.com.web.pesquisas.web.rest.util;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;

public class HeadersUtil {

    private static final String X_VALIDATION_ERROR = "X-Validation-Error";
	private static final String X_TOTAL_COUNT = "X-Total-Count";

	public static HttpHeaders generatePaginationHttpHeaders(Page<?> page) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(X_TOTAL_COUNT, "" + page.getTotalElements());
        return headers;
    }

    public static HttpHeaders addPaginationHttpHeaders(HttpHeaders headers, Page<?> page) {
        headers.add(X_TOTAL_COUNT, "" + page.getTotalElements());
        return headers;
    }
    
    public static HttpHeaders generatePaginationHttpHeaders(long totalElements) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(X_TOTAL_COUNT, "" + totalElements);
        return headers;
    }

    public static HttpHeaders addPaginationHttpHeaders(HttpHeaders headers, long totalElements) {
        headers.add(X_TOTAL_COUNT, "" + totalElements);
        return headers;
    }

    public static HttpHeaders generateValidationHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(X_VALIDATION_ERROR, Boolean.TRUE.toString());
        return headers;
    }

    public static HttpHeaders addValidationHttpHeaders(HttpHeaders headers) {
        headers.add(X_VALIDATION_ERROR, Boolean.TRUE.toString());
        return headers;
    }

    public static HttpHeaders generateBusinessHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Business-Error", Boolean.TRUE.toString());
        return headers;
    }

    public static HttpHeaders addBussinesHttpHeaders(HttpHeaders headers) {
        headers.add("X-Business-Error", Boolean.TRUE.toString());
        return headers;
    }
    
}

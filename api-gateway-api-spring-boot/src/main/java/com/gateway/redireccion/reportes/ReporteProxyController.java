package com.gateway.redireccion.reportes;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/api/proxy/reportes")
@RequiredArgsConstructor

public class ReporteProxyController {

    private final RestTemplate restTemplate;

    @RequestMapping(value = "/**", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public ResponseEntity<?> proxyReportes(HttpServletRequest request,
                                           @RequestBody(required = false) String body,
                                           @RequestHeader HttpHeaders headers) {

        String originalPath = request.getRequestURI().replace("/api/proxy/reportes", "");
        String targetUrl = "http://localhost:8090/api/reportes" + originalPath;
        HttpMethod method = HttpMethod.valueOf(request.getMethod());

        // Clonar headers válidos
        HttpHeaders clonedHeaders = new HttpHeaders();
        for (String headerName : headers.keySet()) {
            if (!headerName.equalsIgnoreCase(HttpHeaders.AUTHORIZATION)) {
                clonedHeaders.put(headerName, headers.get(headerName));
            }
        }

        HttpEntity<String> entity = new HttpEntity<>(body, clonedHeaders);

        try {
            return restTemplate.exchange(targetUrl, method, entity, String.class);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(e.getResponseBodyAsString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error\": \"Error interno del servidor\"}");
        }
    }
}

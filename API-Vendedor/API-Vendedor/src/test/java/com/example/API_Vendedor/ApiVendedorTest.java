package com.example.API_Vendedor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiVendedorTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldReturnVendedoresList() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/vendedores", String.class);
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        // Puedes agregar más asserts según la respuesta esperada
    }
}
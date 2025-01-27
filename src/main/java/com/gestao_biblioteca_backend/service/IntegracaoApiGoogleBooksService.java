package com.gestao_biblioteca_backend.service;

import com.gestao_biblioteca_backend.model.Livro;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class IntegracaoApiGoogleBooksService {

    public static final String URL_BASE_GOOGLE_BOOKS = "https://www.googleapis.com";
    public static final DateTimeFormatter FORMATO_DD_MM_AAAA = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static final DateTimeFormatter FORMATO_AAAA_MM_DD = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public List<Livro> buscarNoGoogleBooks(String titulo) {
        ResponseEntity<Map> response = this.executarBusca(titulo);
        if (!response.getStatusCode().equals(HttpStatus.OK) || response.getBody() == null) return new ArrayList<>();
        return this.mapearResponseParaListaDeLivros(response);
    }

    private ResponseEntity<Map> executarBusca(String titulo) {
        try {
            String url = URL_BASE_GOOGLE_BOOKS.concat("/books/v1/volumes?q=").concat(titulo);
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> httpEntity = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();

            return restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    httpEntity,
                    Map.class
            );
        } catch (Exception e) {
            throw new RuntimeException("Houve alguma falha ao tentar buscar livro no GoogleBooks");
        }
    }

    private List<Livro> mapearResponseParaListaDeLivros(ResponseEntity<Map> response) {
        Map<String, Object> body = response.getBody();
        List<Map<String, Object>> livrosGoogle = (List<Map<String, Object>>) body.get("items");
        return this.criarListaDeLivros(livrosGoogle);
    }

    private List<Livro> criarListaDeLivros(List<Map<String, Object>> livrosGoogle) {
        List<Livro> livros = new ArrayList<>();
        livrosGoogle.forEach(livro -> {
            Map<String, Object> volumeInfo = (Map<String, Object>) livro.get("volumeInfo");
            Map<String, String> industryIdentifiers = volumeInfo.get("industryIdentifiers") != null ? ((List<Map<String, String>>) volumeInfo.get("industryIdentifiers")).get(0) : null;
            List<String> categories = (List<String>) volumeInfo.get("categories");
            List<String> authors = (List<String>) volumeInfo.get("authors");

            String title = (String) volumeInfo.get("title");
            LocalDate publishedDate = LocalDate.parse(volumeInfo.get("publishedDate").toString(), FORMATO_AAAA_MM_DD);
            String author = authors != null && !authors.isEmpty() ? authors.get(0) : "Autor não informado";
            String isbn = industryIdentifiers != null && !industryIdentifiers.isEmpty() ? industryIdentifiers.get("identifier") : "ISBN não informado";
            String category = categories != null && !categories.isEmpty() ? categories.get(0) : "Categoria não informada";

            livros.add(new Livro(title, author, isbn, publishedDate, category));
        });
        return livros;
    }
}

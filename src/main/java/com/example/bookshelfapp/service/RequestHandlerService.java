package com.example.bookshelfapp.service;

import com.example.bookshelfapp.entity.Book;
import com.example.bookshelfapp.entity.SearchResult;
import com.example.bookshelfapp.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class RequestHandlerService {

    private final RestTemplate restTemplate;
    private final BookRepository bookRepository;

    public RequestHandlerService(RestTemplate restTemplate, BookRepository bookRepository) {
        this.restTemplate = restTemplate;
        this.bookRepository = bookRepository;
    }

    public SearchResult processRequest(String input, int startIndex, int maxElements) {
        bookRepository.clear();
        String url = constructUrl(input, startIndex, maxElements);
        SearchResult result = restTemplate.getForObject(url, SearchResult.class);
        assert result != null;
        assert result.getItems() != null;
        bookRepository.addItems(result.getItems());
        return result;
    }

    public Book getBookById(String id) {
        return bookRepository.getById(id);
    }

    private String constructUrl(String input, int startIndex, int maxElements) {
        StringBuilder url = new StringBuilder("https://www.googleapis.com/books/v1/volumes?q=");
        String[] inputArr = input.trim().split(" ");
        url.append(String.join("+", inputArr));
        url.append("&startIndex=");
        url.append(startIndex);
        url.append("&maxResults=");
        url.append(maxElements);
        url.append("&printType=books");
        return url.toString();
    }
}

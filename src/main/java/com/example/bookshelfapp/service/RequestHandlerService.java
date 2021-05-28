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

    private static final String googleApiKey = "AIzaSyBAJgqIvsSBTnQtgDJZYdSiXeqUHUzSYgc";

    public RequestHandlerService(RestTemplate restTemplate, BookRepository bookRepository) {
        this.restTemplate = restTemplate;
        this.bookRepository = bookRepository;
    }

    public SearchResult processRequest(String input) {
        String url = constructUrl(input, 0, 40);
        SearchResult result = restTemplate.getForObject(url, SearchResult.class);
        assert result != null;
        bookRepository.clear();
        bookRepository.addItems(result.getItems());
        return result;
    }

    public Book getBookById(String id) {
        return bookRepository.getById(id);
    }

    private String constructUrl(String input, int startIndex, int maxElements) {
        StringBuilder url = new StringBuilder("https://www.googleapis.com/books/v1/volumes?q=");
        String[] inputArr = input.trim().split(" ");
        for (int i = 0; i < inputArr.length; i++) {
            url.append(inputArr[i]);
            if (i != inputArr.length - 1) {
                url.append("+");
            }
        }
        url.append("&startIndex=");
        url.append(startIndex);
        url.append("&maxResults=");
        url.append(maxElements);
        url.append("&printType=books");
        //url.append("&key=");
        //url.append(googleApiKey);
        return url.toString();
    }
}

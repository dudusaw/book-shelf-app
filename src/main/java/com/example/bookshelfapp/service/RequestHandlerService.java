package com.example.bookshelfapp.service;

import com.example.bookshelfapp.entity.model.Book;
import com.example.bookshelfapp.entity.model.Pagination;
import com.example.bookshelfapp.entity.model.SearchResult;
import com.example.bookshelfapp.repository.BookRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RequestHandlerService implements ISearchService {

    private final RestTemplate restTemplate;
    private final BookRepository bookRepository;

    @Setter
    @Getter
    @Value("${app.max-elements-per-page}")
    private int maxElementsPerPage;

    public RequestHandlerService(RestTemplate restTemplate, BookRepository bookRepository) {
        this.restTemplate = restTemplate;
        this.bookRepository = bookRepository;
    }

    @Override
    public SearchResult processSearch(String input, int startIndex) {
        bookRepository.clear();
        String url = constructUrl(input, startIndex, maxElementsPerPage);
        SearchResult result = restTemplate.getForObject(url, SearchResult.class);
        assert result != null;
        assert result.getItems() != null;
        bookRepository.addItems(result.getItems());
        Pagination pagination = new Pagination(startIndex, input, maxElementsPerPage, result.getTotalItems());
        result.setPagination(pagination);
        return result;
    }

    @Override
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

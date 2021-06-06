package com.example.bookshelfapp.service;

import com.example.bookshelfapp.entity.model.Book;
import com.example.bookshelfapp.entity.model.SearchResult;

public interface ISearchService {
    SearchResult processSearch(String input, int startIndex);

    Book getBookById(String id);
}

package com.example.bookshelfapp.repository;

import com.example.bookshelfapp.entity.model.Book;
import com.example.bookshelfapp.entity.model.ResultItem;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookRepository {

    private final Map<String, Book> bookMap = new LinkedHashMap<>();

    public void addItems(List<ResultItem> items) {
        for (ResultItem item : items) {
            bookMap.put(item.getId(), item.getVolumeInfo());
        }
    }

    public Book getById(String id) {
        return bookMap.get(id);
    }

    public void removeByIds(String... ids) {
        for (String id : ids) {
            bookMap.remove(id);
        }
    }

    public void clear() {
        bookMap.clear();
    }
}

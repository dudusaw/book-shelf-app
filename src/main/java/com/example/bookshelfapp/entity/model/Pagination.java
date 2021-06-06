package com.example.bookshelfapp.entity.model;

import lombok.Data;

@Data
public class Pagination {

    private int startIndex;
    private int maxElements;
    private int currentPage;
    private String inputText;
    private boolean isLastPage;
    private boolean isFirstPage;

    public Pagination(int startIndex, String inputText, int maxElements, int totalItems) {
        this.startIndex = startIndex;
        this.inputText = inputText;
        this.maxElements = maxElements;
        currentPage = startIndex / maxElements + 1;
        isFirstPage = currentPage > 1;
        isLastPage = totalItems - startIndex <= maxElements;
    }
}

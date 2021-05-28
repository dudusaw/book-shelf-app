package com.example.bookshelfapp.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Pagination {

    private int startIndex;
    private int maxElements;
    private int currentPage;
    private String inputText;
    private boolean isLastPage;
    private boolean isFirstPage;

    public Pagination(int startIndex, int maxElements, String inputText, int totalItems) {
        this.startIndex = startIndex;
        this.maxElements = maxElements;
        this.inputText = inputText;
        currentPage = startIndex / maxElements + 1;
        isFirstPage = currentPage > 1;
        isLastPage = totalItems - startIndex <= maxElements;
    }
}

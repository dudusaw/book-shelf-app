package com.example.bookshelfapp.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
public class SearchResult {

    private int totalItems;
    private List<ResultItem> items;
    private Pagination pagination;

}

package com.example.bookshelfapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Calendar;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
public class Book {

    private String title;
    private List<String> authors;
    private String description;
    private String publisher;
    private String publishedDate;
    private BookImageLinks imageLinks;
    private int pageCount;
    private List<BookISDN> industryIdentifiers;

    @Getter @Setter @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class BookImageLinks {
        private String thumbnail;
    }

    @Getter @Setter @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class BookISDN {
        private String type;
        private String identifier;
    }

}

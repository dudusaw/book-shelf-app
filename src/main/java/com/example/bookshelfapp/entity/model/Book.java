package com.example.bookshelfapp.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Calendar;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Book {

    private String title;
    private String shortTitle;
    private List<String> authors;
    private String description;
    private String publisher;
    private String publishedDate;
    private BookImageLinks imageLinks;
    private int pageCount;
    private List<BookISDN> industryIdentifiers;

    public static final int MAX_CHARACTERS_IN_TITLE = 60;

    public void setTitle(String title) {
        this.title = title;
        if (title.length() > MAX_CHARACTERS_IN_TITLE) {
            shortTitle = title.substring(0, MAX_CHARACTERS_IN_TITLE) + "...";
        } else {
            shortTitle = title;
        }
    }

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

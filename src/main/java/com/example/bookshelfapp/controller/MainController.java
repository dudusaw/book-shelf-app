package com.example.bookshelfapp.controller;

import com.example.bookshelfapp.entity.model.Book;
import com.example.bookshelfapp.entity.model.Pagination;
import com.example.bookshelfapp.entity.model.SearchResult;
import com.example.bookshelfapp.service.ISearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/books")
public class MainController {

    private final ISearchService ISearchService;

    public MainController(ISearchService ISearchService) {
        this.ISearchService = ISearchService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/process-request")
    public String processRequest(Model ui, @RequestParam String inputText, @RequestParam(defaultValue = "0") int startIndex) {
        SearchResult result = ISearchService.processSearch(inputText, startIndex);
        ui.addAttribute("searchResult", result);
        return "search-result";
    }

    @GetMapping("/details")
    public String details(Model ui, @RequestParam String id) {
        Book book = ISearchService.getBookById(id);
        ui.addAttribute("bookId", id);
        ui.addAttribute("book", book);
        return "details";
    }
}

package com.example.bookshelfapp.controller;

import com.example.bookshelfapp.entity.Book;
import com.example.bookshelfapp.entity.SearchResult;
import com.example.bookshelfapp.service.RequestHandlerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    private RequestHandlerService requestHandlerService;

    public MainController(RequestHandlerService requestHandlerService) {
        this.requestHandlerService = requestHandlerService;
    }

    @GetMapping("/")
    public String home(Model ui, HttpServletRequest request) {
        return "index";
    }

    @GetMapping("/process-request")
    public String processRequest(Model ui, HttpServletRequest request) {
        String inputText = request.getParameter("inputText");
        SearchResult result = requestHandlerService.processRequest(inputText);
        ui.addAttribute("searchResult", result);
        return "search-result";
    }

    @GetMapping("/details")
    public String details(Model ui, @RequestParam String id) {
        Book book = requestHandlerService.getBookById(id);
        ui.addAttribute("bookId", id);
        ui.addAttribute("book", book);
        return "details";
    }
}

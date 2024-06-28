package com.example.dbproj.controller;

import com.example.dbproj.dto.FigureBriefInfo;
import com.example.dbproj.dto.TitleBriefInfo;
import com.example.dbproj.service.SearchServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {
    //http://...:8080/search/title?title=...
    @GetMapping("/search/title")
    public List<TitleBriefInfo> searchTitles(String title){
        SearchServiceImpl searchService = new SearchServiceImpl();
        return searchService.searchTitles(title);
    }

    //http://...:8080/search/figure?name=...
    @GetMapping("/search/figure")
    public List<FigureBriefInfo> searchFigures(String name){
        SearchServiceImpl searchService = new SearchServiceImpl();
        return searchService.searchFigures(name);
    }
}

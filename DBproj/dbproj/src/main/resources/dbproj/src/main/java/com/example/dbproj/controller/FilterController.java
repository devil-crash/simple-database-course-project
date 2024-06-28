package com.example.dbproj.controller;

import com.example.dbproj.dto.TitleBriefInfo;
import com.example.dbproj.service.FilterServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FilterController {
    //http://...:8080/filter?titleType=...&genre=...&startYear=...
    @GetMapping("/filter")
    public List<TitleBriefInfo> filterTitles(String titleType, String genre, int startYear)
    {
        FilterServiceImpl filterService = new FilterServiceImpl();
        return filterService.filterTitles(titleType, genre, startYear);
    }

    @GetMapping("/titleTypes")
    public List<String> getTitleTypes()
    {
        FilterServiceImpl filterService = new FilterServiceImpl();
        return filterService.getTitleTypes();
    }

    @GetMapping("/genres")
    public List<String> getGenres()
    {
        FilterServiceImpl filterService = new FilterServiceImpl();
        return filterService.getGenres();
    }
}

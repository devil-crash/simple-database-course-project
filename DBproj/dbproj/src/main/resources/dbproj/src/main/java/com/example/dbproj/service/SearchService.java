package com.example.dbproj.service;

import com.example.dbproj.dto.FigureBriefInfo;
import com.example.dbproj.dto.TitleBriefInfo;

import java.util.List;

public interface SearchService {
    List<TitleBriefInfo> searchTitles(String title);
    List<FigureBriefInfo> searchFigures(String name);
}

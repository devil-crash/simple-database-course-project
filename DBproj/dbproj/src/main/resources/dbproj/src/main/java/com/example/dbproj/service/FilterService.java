package com.example.dbproj.service;

import com.example.dbproj.dto.TitleBriefInfo;

import java.sql.Connection;
import java.util.List;

public interface FilterService {
    List<TitleBriefInfo> filterTitles(String titleType, String genre, int startYear);
    List<String> getTitleTypes();
    List<String> getGenres();
}

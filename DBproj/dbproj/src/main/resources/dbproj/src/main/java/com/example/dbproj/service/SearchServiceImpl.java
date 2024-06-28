package com.example.dbproj.service;

import com.example.dbproj.dao.DBop;
import com.example.dbproj.dto.FigureBriefInfo;
import com.example.dbproj.dto.TitleBriefInfo;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService{

    @Override
    public List<TitleBriefInfo> searchTitles(String title) {
        Connection conn = DBop.getConnection();
        List<TitleBriefInfo> briefTitles = DBop.searchTitleByName(conn, title);
        try {
            conn.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage());
            return null;
        }

        return briefTitles;
    }

    @Override
    public List<FigureBriefInfo> searchFigures(String name) {
        Connection conn = DBop.getConnection();
        List<FigureBriefInfo> briefFigures = DBop.searchFigureByName(conn, name);
        try {
            conn.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage());
            return null;
        }

        return briefFigures;
    }
}

package com.example.dbproj.service;

import com.example.dbproj.dao.DBop;
import com.example.dbproj.dto.TitleBriefInfo;

import java.sql.Connection;
import java.util.List;

public class FilterServiceImpl implements FilterService{

    @Override
    public List<TitleBriefInfo> filterTitles(String titleType, String genre, int startYear) {
        Connection conn = DBop.getConnection();
        List<TitleBriefInfo> briefTitles = DBop.filterTitles(conn, titleType, genre, startYear);
        try {
            conn.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage());
            return null;
        }

        return briefTitles;
    }

    @Override
    public List<String> getTitleTypes() {
        Connection conn = DBop.getConnection();
        List<String> titleTypes = DBop.getTitleTypes(conn);
        try {
            conn.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage());
            return null;
        }

        return titleTypes;
    }

    @Override
    public List<String> getGenres() {
        Connection conn = DBop.getConnection();
        List<String> genres = DBop.getGenres(conn);
        try {
            conn.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage());
            return null;
        }

        return genres;
    }
}

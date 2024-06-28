package com.example.dbproj.service;

import com.example.dbproj.dao.DBop;
import com.example.dbproj.dto.FigureBriefInfo;
import com.example.dbproj.dto.FigureDetailInfo;
import com.example.dbproj.dto.TitleBriefInfo;
import com.example.dbproj.dto.TitleDetailInfo;

import java.sql.Connection;
import java.util.List;

public class ViewServiceImpl implements ViewService{

    @Override
    public TitleDetailInfo viewTitle(String titleId) {
        Connection conn = DBop.getConnection();
        TitleDetailInfo detailTitle = TitleDetailInfo.extendToDetail(conn, DBop.searchTitleById(conn, titleId));
        try {
            conn.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage());
            return null;
        }

        return detailTitle;
    }

    @Override
    public FigureDetailInfo viewFigure(String personId) {
        Connection conn = DBop.getConnection();
        FigureDetailInfo detailFigure = FigureDetailInfo.extendToDetail(conn, DBop.searchFigureById(conn, personId));
        try {
            conn.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage());
            return null;
        }

        return detailFigure;
    }

    @Override
    public TitleBriefInfo getRelativeTitle(String titleId) {
        Connection conn = DBop.getConnection();
        TitleBriefInfo briefTitle = DBop.searchTitleById(conn, titleId);
        try {
            conn.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage());
            return null;
        }

        return briefTitle;
    }

    @Override
    public FigureBriefInfo getRelativeFigure(String personId) {
        Connection conn = DBop.getConnection();
        FigureBriefInfo briefFigure = DBop.searchFigureById(conn, personId);
        try {
            conn.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage());
            return null;
        }

        return briefFigure;
    }
}

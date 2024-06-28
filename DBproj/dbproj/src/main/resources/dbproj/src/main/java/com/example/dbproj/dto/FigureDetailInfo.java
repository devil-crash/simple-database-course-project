package com.example.dbproj.dto;

import com.example.dbproj.dao.DBop;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

public class FigureDetailInfo extends FigureBriefInfo{
    List<String> primaryProfessions;    //职业/工作
    List<String> knownForTitles;        //著名作品

    public List<String> getPrimaryProfessions() {
        return primaryProfessions;
    }
    public List<String> getKnownForTitles() {
        return knownForTitles;
    }



    public FigureDetailInfo(FigureBriefInfo briefFigure)
    {
        super(briefFigure.personId, briefFigure.primaryName, briefFigure.birthYear, briefFigure.deathYear);
    }



    public static FigureDetailInfo extendToDetail(Connection conn, FigureBriefInfo briefFigure)
    {
        FigureDetailInfo detailFigure = new FigureDetailInfo(briefFigure);
        detailFigure.primaryProfessions = DBop.searchPrimaryProfessions(conn, detailFigure.personId);
        detailFigure.knownForTitles = DBop.searchFigureKnownTitles(conn, detailFigure.personId);

        return detailFigure;
    }
}

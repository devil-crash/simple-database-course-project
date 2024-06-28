package com.example.dbproj.dto;

import com.example.dbproj.dao.DBop;
import com.example.dbproj.dto.Season;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TitleDetailInfo extends TitleBriefInfo{
    List<Season> seasons;   //series属性，每季编号及其集数
    List<String> writers;
    List<String> directors;
    List<String> actors;

    public TitleDetailInfo(TitleBriefInfo briefTitle)
    {
        super(briefTitle.titleId, briefTitle.primaryTitle, briefTitle.originalTitle, briefTitle.titleType, briefTitle.startYear, briefTitle.endYear, briefTitle.averageRating, briefTitle.numVotes);
        this.genres = briefTitle.genres;
    }



    public List<Season> getSeasons() {
        return seasons;
    }
    public List<String> getWriters() {
        return writers;
    }
    public List<String> getDirectors() {
        return directors;
    }
    public List<String> getActors() {
        return actors;
    }

    public static TitleDetailInfo extendToDetail(Connection conn, TitleBriefInfo briefTitle)
    {
        TitleDetailInfo detailTitle = new TitleDetailInfo(briefTitle);
        detailTitle.writers = DBop.searchWriters(conn, detailTitle.titleId);
        detailTitle.directors = DBop.searchDirectors(conn, detailTitle.titleId);
        detailTitle.actors = DBop.searchActors(conn, detailTitle.titleId);
        detailTitle.seasons = DBop.searchSeriesInfo(conn, detailTitle.titleId);

        return detailTitle;
    }
}

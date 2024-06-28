package com.example.dbproj.dto;

import com.example.dbproj.dao.DBop;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//title---影视作品/名
public class TitleBriefInfo {
    String titleId;
    String primaryTitle;
    String originalTitle;
    String titleType;       //类型 short, tvSeries, movie...
    List<String> genres;    //风格/体裁 Comedy, Romance, Sport...
    int startYear;          //若非series则只有start
    int endYear;
    float averageRating;    //评分
    int numVotes;           //评分人数
    public TitleBriefInfo(String titleId, String primaryTitle, String originalTitle, String titleType,
                          int startYear, int endYear, float averageRating, int numVotes)
    {
        this.titleId = titleId;
        this.primaryTitle = primaryTitle;
        this.originalTitle = originalTitle;
        this.titleType = titleType;
        this.startYear = startYear;
        this.endYear = endYear;
        this.averageRating = averageRating;
        this.numVotes = numVotes;
    }

    public String getTitleId() {
        return titleId;
    }
    public String getPrimaryTitle() {
        return primaryTitle;
    }
    public String getOriginalTitle() {
        return originalTitle;
    }
    public String getTitleType() {
        return titleType;
    }
    public int getStartYear() {
        return startYear;
    }
    public int getEndYear() {
        return endYear;
    }
    public float getAverageRating() {
        return averageRating;
    }
    public int getNumVotes() {
        return numVotes;
    }
    public List<String> getGenres() {
        return genres;
    }
    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
}

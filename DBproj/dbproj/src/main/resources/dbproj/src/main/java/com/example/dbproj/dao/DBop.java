package com.example.dbproj.dao;

import com.example.dbproj.dto.FigureBriefInfo;
import com.example.dbproj.dto.Season;
import com.example.dbproj.dto.TitleBriefInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBop {
    //Connect to database
    public static Connection getConnection()
    {
        String driver = "com.mysql.jdbc.Driver";

    String sourceURL = "jdbc:mysql://localhost:3306/dbproj?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&serverTimezone=UTC&useSSL=false";
        String username = "root";
        String passwd = "root";
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(sourceURL, username, passwd);
            System.out.println("Connect to database successfully!");
            conn.setAutoCommit(false);
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage());
            return null;
        }

        return conn;
    }

    //disconnect
    public static boolean disconnect(Connection conn)
    {
        try {
            conn.close();
            return true;
        } catch (SQLException e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage());
            return false;
        }
    }

    //Search
    //search title (by name)---titleId, primaryTitle, originalTitle, titleType, startYear, endYear, averageRating, numVotes
    public static List<TitleBriefInfo> searchTitleByName(Connection conn, String title)
    {
        List<TitleBriefInfo> briefTitles = new ArrayList<>();
        ResultSet rs = null;
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("select titleId, primaryTitle, originalTitle, titleType, startYear, endYear, averageRating, numVotes, count(distinct title) " +
                                        "from (title_akas natural join title_basics) natural join title_ratings " +
                                        "where titleType!='tvEpisode' and title like '%" + title + "%' " +
                                        "group by titleId");
            conn.commit();

            while(rs.next()) {
                briefTitles.add(new TitleBriefInfo(
                        rs.getString("titleId"),
                        rs.getString("primaryTitle"),
                        rs.getString("originalTitle"),
                        rs.getString("titleType"),
                        rs.getInt("startYear"),
                        rs.getInt("endYear"),
                        rs.getFloat("averageRating"),
                        rs.getInt("numVotes")
                ));
            }
            rs.close();

            stmt.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage());
            return new ArrayList<>();
        }

        System.out.println("Operation--searchTitleByName--title" + title + "--success.");
        return briefTitles;
    }

    //search title genres
    public static List<String> searchTitleGenres(Connection conn, String titleId) {
        List<String> genres = new ArrayList<>();
        ResultSet rs = null;
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("select genres from title_genres where titleId='" + titleId + "'");
            conn.commit();

            while(rs.next()) {
                genres.add(rs.getString("genres"));
            }

            stmt.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage());
            return new ArrayList<>();
        }

        System.out.println("Operation--searchTitleGenres--titleId" + titleId + "--success.");
        return genres;
    }

    //search title relative figures --- writer, director, actor
    public static List<String> searchWriters(Connection conn, String titleId)
    {
        List<String> writers = new ArrayList<>();
        ResultSet rs = null;
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from title_writers where titleId='" + titleId+ "'");
            conn.commit();

            while(rs.next()) {
                writers.add(rs.getString("writers"));
            }
            rs.close();

            stmt.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage());
            return new ArrayList<>();
        }

        System.out.println("Operation--searchWriters--titleId--" + titleId + "--success.");
        return writers;
    }
    public static List<String> searchDirectors(Connection conn, String titleId)
    {
        List<String> directors = new ArrayList<>();
        ResultSet rs = null;
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from title_directors where titleId='" + titleId+ "'");
            conn.commit();

            while(rs.next()) {
                directors.add(rs.getString("directors"));
            }
            rs.close();

            stmt.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage());
            return new ArrayList<>();
        }

        System.out.println("Operation--searchDirectors--titleId--" + titleId + "--success.");
        return directors;
    }
    public static List<String> searchActors(Connection conn, String titleId)
    {
        List<String> actors = new ArrayList<>();
        ResultSet rs = null;
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("select personId from title_principals where (category='actor' or category='actress') and titleId='" + titleId + "'");
            conn.commit();

            while(rs.next()) {
                actors.add(rs.getString("personId"));
            }
            rs.close();

            stmt.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage());
            return new ArrayList<>();
        }

        System.out.println("Operation--searchActors--titleId--" + titleId + "--success.");
        return actors;
    }

    //search title series info --- seasonNumber, episodes
    public static List<Season> searchSeriesInfo(Connection conn, String titleId)
    {
        List<Season> seasons = new ArrayList<>();
        ResultSet rs = null;
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("select seasonNumber, count(episodeNumber) episodes from title_episode where parentTitleId='" + titleId + "' group by seasonNumber");
            conn.commit();

            while(rs.next()) {
                seasons.add(new Season(rs.getInt("seasonNumber"), rs.getInt("episodes")));
            }
            rs.close();

            stmt.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage());
            return new ArrayList<>();
        }

        System.out.println("Operation--searchSeriesInfo--titleId--" + titleId + "--success.");
        return seasons;
    }

    //search figure (by name) --- personId, primaryName, birthYear/deathYear
    public static List<FigureBriefInfo> searchFigureByName(Connection conn, String name)
    {
        ResultSet rs = null;
        List<FigureBriefInfo> briefFigures = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from person_basics where primaryName like '%" + name + "%'");
            conn.commit();

            while(rs.next()) {
                briefFigures.add(new FigureBriefInfo(
                        rs.getString("personId"),
                        rs.getString("primaryName"),
                        rs.getInt("birthYear"),
                        rs.getInt("deathYear")
                ));
            }
            rs.close();

            stmt.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage());
            return new ArrayList<>();
        }

        System.out.println("Operation--searchFigureBasicInfo--name--" + name + "--success.");
        return briefFigures;
    }

    //search figure detail info --- primaryProfessions
    public static List<String> searchPrimaryProfessions(Connection conn, String personId)
    {
        List<String> primaryProfessions = new ArrayList<>();
        ResultSet rs = null;
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("select primaryProfession from person_primaryprofession where personId='" + personId + "'");
            conn.commit();

            while(rs.next()) {
                primaryProfessions.add(rs.getString("primaryProfession"));
            }
            rs.close();

            stmt.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage());
            return new ArrayList<>();
        }

        System.out.println("Operation--searchPrimaryProfessions--personId--" + personId + "--success.");
        return primaryProfessions;
    }
    // --- knownForTitles
    public static List<String> searchFigureKnownTitles(Connection conn, String personId)
    {
        List<String> knownTitles = new ArrayList<>();
        ResultSet rs = null;
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("select knwonForTitles from person_knownfortitles where personId='" + personId + "'");
            conn.commit();

            while(rs.next()) {
                knownTitles.add(rs.getString("knownForTitles"));
            }
            rs.close();

            stmt.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage());
            return new ArrayList<>();
        }

        System.out.println("Operation--searchFigureKnownTitles--personId--" + personId + "--success.");
        return knownTitles;
    }


    //Title-Filter
    public static List<String> getTitleTypes(Connection conn)
    {
        List<String> titleTypes = new ArrayList<>();
        ResultSet rs = null;
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("select distinct titleType from title_basics");
            conn.commit();

            while(rs.next()) {
                titleTypes.add(rs.getString("titleType"));
            }
            rs.close();

            stmt.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage());
            return new ArrayList<>();
        }

        System.out.println("Operation--getTitleTypes--success.");
        return titleTypes;
    }
    public static List<String> getGenres(Connection conn)
    {
        List<String> genres = new ArrayList<>();
        ResultSet rs = null;
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("select distinct genres from title_genres");
            conn.commit();

            while(rs.next()) {
                genres.add(rs.getString("genres"));
            }
            rs.close();

            stmt.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage());
            return new ArrayList<>();
        }

        System.out.println("Operation--getGenres--success.");
        return genres;
    }

    public static List<TitleBriefInfo> filterTitles(Connection conn, String titleType, String genre, int startYear)
    {
        List<TitleBriefInfo> briefTitles = new ArrayList<>();
        ResultSet rs = null;
        String sql = "select titleId, primaryTitle, originalTitle, titleType, startYear, endYear, averageRating, numVotes " +
                "from title_basics natural join title_ratings natural join title_genres " +
                "where titleType!='tvEpisode' ";
        if(!"null".equals(titleType)) sql += "and titleType='" + titleType + "' ";
        if(!"null".equals(genre)) sql += "and genres='" + genre + "' ";
        if(startYear != -1) sql += "and startYear=" + startYear + " ";
        sql += "order by startYear desc, averageRating desc";
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            conn.commit();

            while(rs.next()) {
                briefTitles.add(new TitleBriefInfo(
                        rs.getString("titleId"),
                        rs.getString("primaryTitle"),
                        rs.getString("originalTitle"),
                        rs.getString("titleType"),
                        rs.getInt("startYear"),
                        rs.getInt("endYear"),
                        rs.getFloat("averageRating"),
                        rs.getInt("numVotes")
                ));
            }
            rs.close();
            for(TitleBriefInfo briefTitle: briefTitles) {
                briefTitle.setGenres(DBop.searchTitleGenres(conn, briefTitle.getTitleId()));
            }

            stmt.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage());
            return new ArrayList<>();
        }

        System.out.println("Operation--filterTitles--titleType--" + titleType + "--genre--" + genre + "--startYear--" + startYear + "--success.");
        return briefTitles;
    }
    public static TitleBriefInfo searchTitleById(Connection conn, String titleId)
    {
        TitleBriefInfo briefTitle = null;
        ResultSet rs = null;
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("select titleId, primaryTitle, originalTitle, titleType, startYear, endYear, averageRating, numVotes " +
                    "from title_basics natural join title_ratings " +
                    "where titleId='" + titleId + "'");
            conn.commit();
            if(rs.next()) {
                briefTitle = new TitleBriefInfo(
                        rs.getString("titleId"),
                        rs.getString("primaryTitle"),
                        rs.getString("originalTitle"),
                        rs.getString("titleType"),
                        rs.getInt("startYear"),
                        rs.getInt("endYear"),
                        rs.getFloat("averageRating"),
                        rs.getInt("numVotes")
                );
            }
            rs.close();
            briefTitle.setGenres(DBop.searchTitleGenres(conn, briefTitle.getTitleId()));

            stmt.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage());
            return null;
        }

        System.out.println("Operation--searchTitleById--titleId--" + titleId + "--success.");
        return briefTitle;
    }
    public static FigureBriefInfo searchFigureById(Connection conn, String personId)
    {
        FigureBriefInfo briefFigure = null;
        ResultSet rs = null;
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from person_basics where personId='" + personId +"'");
            conn.commit();

            if(rs.next()) {
                briefFigure = new FigureBriefInfo(
                        rs.getString("personId"),
                        rs.getString("primaryName"),
                        rs.getInt("birthYear"),
                        rs.getInt("deathYear")
                );
            }

            stmt.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage());
            return null;
        }

        System.out.println("Operation--searchFigureById--personId--" + personId + "--success.");
        return briefFigure;
    }
}

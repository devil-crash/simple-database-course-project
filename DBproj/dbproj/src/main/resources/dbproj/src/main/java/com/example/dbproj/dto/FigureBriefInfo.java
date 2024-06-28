package com.example.dbproj.dto;

import com.example.dbproj.dao.DBop;
import org.springframework.boot.context.event.SpringApplicationEvent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FigureBriefInfo {
    String personId;
    String primaryName;
    int birthYear;
    int deathYear;

    public FigureBriefInfo(String personId, String primaryName, int birthYear, int deathYear)
    {
        this.personId = personId;
        this.primaryName = primaryName;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

    public String getPersonId() {
        return personId;
    }
    public String getPrimaryName() {
        return  primaryName;
    }
    public int getBirthYear() {
        return birthYear;
    }
    public int getDeathYear() {
        return deathYear;
    }
}

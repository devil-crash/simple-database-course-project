package com.example.dbproj.service;

import com.example.dbproj.dto.FigureBriefInfo;
import com.example.dbproj.dto.FigureDetailInfo;
import com.example.dbproj.dto.TitleBriefInfo;
import com.example.dbproj.dto.TitleDetailInfo;

import java.util.List;

public interface ViewService {
    TitleDetailInfo viewTitle(String titleId);
    FigureDetailInfo viewFigure(String personId);
    TitleBriefInfo getRelativeTitle(String titleId);
    FigureBriefInfo getRelativeFigure(String personId);
}

package com.example.dbproj.controller;

import com.example.dbproj.dto.FigureBriefInfo;
import com.example.dbproj.dto.FigureDetailInfo;
import com.example.dbproj.dto.TitleBriefInfo;
import com.example.dbproj.dto.TitleDetailInfo;
import com.example.dbproj.service.ViewService;
import com.example.dbproj.service.ViewServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ViewController {
    //http://...:8080/view/title?titleId=...
    @GetMapping("/view/title")
    public TitleDetailInfo viewTitle(String titleId){
        ViewServiceImpl viewService = new ViewServiceImpl();
        return viewService.viewTitle(titleId);
    }

    //http://...:8080/view/figure?personId=...
    @GetMapping("/view/figure")
    public FigureDetailInfo viewFigure(String personId){
        ViewServiceImpl viewService = new ViewServiceImpl();
        return viewService.viewFigure(personId);
    }

    //view拿detailInfo 相关briefInfo在attach拿
    //http://...:8080/attach/title?titleId=...
    @GetMapping("/attach/title")
    public TitleBriefInfo getRelativeTitle(String titleId)
    {
        ViewServiceImpl viewService = new ViewServiceImpl();
        return viewService.getRelativeTitle(titleId);
    }

    //http://...:8080/attach/figure?personId=...
    @GetMapping("/attach/figure")
    public FigureBriefInfo getRelativeFigure(String personId)
    {
        ViewServiceImpl viewService = new ViewServiceImpl();
        return viewService.getRelativeFigure(personId);
    }
}

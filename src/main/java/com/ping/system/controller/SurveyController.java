package com.ping.system.controller;

import com.ping.system.entity.Answer;
import com.ping.system.entity.Survey;
import com.ping.system.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ping
 * @date 2023/10/22
 */
@RestController
@RequestMapping("/survey")
public class SurveyController {
    @Autowired
    private SurveyService surveyService;

    /**
     * 查询所有
     * @return
     */
    @GetMapping("/findAllSurvey")
    public List<Survey> findAllSurvey(){
        return surveyService.findAllSurvey();
    }

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findSurveyByPage")
    public Map<String, Object> findSurveyByPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        pageNum = (pageNum - 1) * pageSize;
        List<Answer> data = surveyService.findSurveyByPage(pageNum,pageSize);
        Integer totalCount = surveyService.selectSurveyTotal();
        Map<String, Object> res = new HashMap<>();
        res.put("data",data);
        res.put("total",totalCount);
        return res;
    }

    @GetMapping("/findById/{surveyId}")
    public Survey findById(@PathVariable("surveyId") Integer surveyId){
        return surveyService.findById(surveyId);
    }

    /**
     * 添加数据
     *
     * @param survey
     * @return
     */
    @PostMapping("/insertSurvey")
    public int insertSurvey(@RequestBody Survey survey){
        return surveyService.insertSurvey(survey);
    }

    @PostMapping("/updateSurvey")
    public int updateSurvey(@RequestBody Survey survey){
        return surveyService.insertOrUpdateSurvey(survey);
    }

    @PostMapping("/deleteSurveyById/{surveyId}")
    public int deleteSurveyById(@PathVariable("surveyId") Integer surveyId){
        return surveyService.deleteSurveyById(surveyId);
    }
}

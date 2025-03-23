package com.ping.system.service;

import com.ping.system.entity.Answer;
import com.ping.system.entity.Survey;
import com.ping.system.mapper.SurveyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author ping
 * @date 2023/10/22
 */
@Service
public class SurveyService {
    @Autowired
    private SurveyMapper surveyMapper;

    public List<Survey> findAllSurvey() {
        return surveyMapper.findAllSurvey();
    }

    public Survey findById(Integer surveyId) {
        return surveyMapper.findById(surveyId);
    }

    public int insertOrUpdateSurvey(Survey survey) {
        if (survey.getSurveyId() == null){
           return surveyMapper.insertSurvey(survey);
        } else {
           return surveyMapper.updateSurvey(survey);
        }
    }

    public int deleteSurveyById(@PathVariable Integer surveyId) {
        return surveyMapper.deleteSurvey(surveyId);
    }

    public Integer selectSurveyTotal() {
        return surveyMapper.selectSurveyTotal();
    }

    public List<Answer> findSurveyByPage(Integer pageNum, Integer pageSize) {
        return surveyMapper.findSurveyByPage(pageNum, pageSize);
    }


    public int insertSurvey(Survey survey) {
        return surveyMapper.insertSurvey(survey);
    }
}

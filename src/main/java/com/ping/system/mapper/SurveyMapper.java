package com.ping.system.mapper;

import com.ping.system.entity.Answer;
import com.ping.system.entity.Survey;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author ping
 * @date 2023/10/22
 */
@Mapper
@Repository
public interface SurveyMapper {
    List<Survey> findAllSurvey();

    Survey findById(@Param("survey_id") Integer surveyId);

    int insertSurvey(Survey survey);

    int updateSurvey(Survey survey);

    int deleteSurvey(@Param("survey_id") Integer surveyId);

    @Select("select count(*) from survey")
    Integer selectSurveyTotal();

    List<Answer> findSurveyByPage(Integer pageNum, Integer pageSize);
}

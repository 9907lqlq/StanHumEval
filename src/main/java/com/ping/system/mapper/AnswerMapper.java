package com.ping.system.mapper;

import com.ping.system.entity.Answer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ping
 * @date 2023/10/22
 */
@Mapper
@Repository
public interface AnswerMapper {

    int insertAnswer(Answer answer);

    int insertAnswerUsername(String username,String surveyName,String hostAddress,String hostName);

    List<Answer> findAll();

    List<Answer> findByPage(Integer pageNum,Integer pageSize,String answerUser);

    Integer selectTotal(String answerUser);

    List<Answer> selectAnswerByNameAndSurvey(String username, String surveyName);

    List<Answer> findReport1();

    List<Answer> findReport2();

    List<Answer> findReport3();

    List<Answer> findAllReport();
}

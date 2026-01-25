package com.ping.system.service;

import com.ping.system.entity.Answer;
import com.ping.system.mapper.AnswerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;


@Service
public class AnswerService {
    @Autowired
    private AnswerMapper answerMapper;

    public int insertAnswer(Answer answer) {
        return answerMapper.insertAnswer(answer);
    }

    public int insertAnswerUsername(String username,String surveyName, String ipAddress) {
        String hostName = null;
        return answerMapper.insertAnswerUsername(username, surveyName, ipAddress, hostName);
    }

    public List<Answer> findAll() {
        return answerMapper.findAll();
    }

    public List<Answer> findByPage(Integer pageNum, Integer pageSize,String answerUser) {
        return answerMapper.findByPage(pageNum, pageSize,answerUser);
    }

    public Integer selectTotal(String answerUser) {
        return answerMapper.selectTotal(answerUser);
    }

    public List<Answer> selectAnswerByNameAndSurvey(String username, String surveyName) {
        return answerMapper.selectAnswerByNameAndSurvey(username, surveyName);
    }

    public List<Answer> findReport1() {
        return answerMapper.findReport1();
    }

    public List<Answer> findReport2() {
        return answerMapper.findReport2();
    }

    public List<Answer> findReport3() {
        return answerMapper.findReport3();
    }

    public List<Answer> findAllReport() {
        return answerMapper.findAllReport();
    }
}

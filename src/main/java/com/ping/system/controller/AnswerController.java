package com.ping.system.controller;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.ping.system.config.IpUtil;
import com.ping.system.entity.Answer;
import com.ping.system.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/answer")
public class AnswerController {
    @Autowired
    private AnswerService answerService;



    @GetMapping("/findAll")
    public List<Answer> findAll(){
        return answerService.findAll();
    }

    /**
     * 
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findByPage")
    public Map<String, Object> findByPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize, @RequestParam String answerUser){
        pageNum = (pageNum - 1) * pageSize;
        List<Answer> data = answerService.findByPage(pageNum,pageSize,answerUser);
        Integer totalCount = answerService.selectTotal(answerUser);
        Map<String, Object> res = new HashMap<>();
        res.put("data",data);
        res.put("total",totalCount);
        return res;
    }

    /**
     * 
     *
     * @param answer
     * @return
     */
    @PostMapping("/insertAnswer")
    public Boolean insertAnswer(@RequestBody Answer answer){
        int count = answerService.insertAnswer(answer);
        System.out.println("count====="+count);
        if (count > 0){
            return true;
        }
        return false;
    }

    /**
     * 
     *
     * @return
     */
    @PostMapping("/insertAnswerUsername")
    public boolean insertAnswerUsername(@RequestParam String username,@RequestParam String surveyName, HttpServletRequest request) {
        List<Answer> answerList = answerService.selectAnswerByNameAndSurvey(username,surveyName);
        String ipAddress = IpUtil.getIpAddr(request);
        if (answerList.size() > 0) {
            return false; 
        }else {
            int count1 = answerService.insertAnswerUsername(username, surveyName, ipAddress);
            if (count1 > 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * 
     *
     * @param response
     * @throws Exception
     */
    @GetMapping("/report1")
    public void export1(HttpServletResponse response) throws Exception{
        List<Answer> list = answerService.findReport1();


        ExcelWriter writer = ExcelUtil.getWriter(true);
 
       

       writer.addHeaderAlias("answer1","1. Does the image look AI-generated or real?");
       writer.addHeaderAlias("answer2","2. How lifelike are the images?");
       writer.addHeaderAlias("answer3","3. How realistic are the images?");
       writer.addHeaderAlias("answer4","4. How convincing is the image?");
       writer.addHeaderAlias("answer5","5. How can you tell whether this image is real or fake?");
       writer.addHeaderAlias("answer6","6. How similar is the image to the real image?");
       writer.addHeaderAlias("answer7","7. How well does the image match the description?");
       writer.addHeaderAlias("answer8","8. How consistent is the image with the input?");
       writer.addHeaderAlias("answer9","9. How well does the image semantically match the input content?");
       writer.addHeaderAlias("answer10","10. How well does the image achieve the expected effect?");
       writer.addHeaderAlias("answer11","11. How relevant is the image to the input content?");
       writer.addHeaderAlias("answer12","12. How well does the image accurately reflect the input?");
       writer.addHeaderAlias("answer13","13. What do you think of the recognizability of the objects in this image?");
       writer.addHeaderAlias("answer14","14. Can you accurately recognize the objects in this image?");
       writer.addHeaderAlias("answer15","15. How many objects do you think can be recognized in this image?");
       writer.addHeaderAlias("answer16","16. How satisfied are you with the recognizability of objects in this image?");
       writer.addHeaderAlias("answer17","17. How good does the overall quality of the entire image appear to be?");
       writer.addHeaderAlias("answer18","18. How is the overall quality of this image?");
       writer.addHeaderAlias("answer19","19. Are you satisfied with the overall quality of this image?");
       writer.addHeaderAlias("answer20","20. How would you rate the overall quality of this image?");
       writer.addHeaderAlias("answer21","21. Do you like this image?");
       writer.addHeaderAlias("answer22","22. Will this image give you visual pleasure?");
       writer.addHeaderAlias("answer23","23. How much do you prefer this image?");
       writer.addHeaderAlias("answer24","24. How well does this image match your preferences?");
       writer.addHeaderAlias("answer25","25. What do you think of the diversity of these images?");
       writer.addHeaderAlias("answer26","26. How much have these images changed?");
       writer.addHeaderAlias("answer27","27. How different are these images?");
       writer.addHeaderAlias("answer28","28. Are you satisfied with the diversity of these images?");

        writer.write(list,true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("answer", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream outputStream = response.getOutputStream();
        writer.flush(outputStream, true);
        outputStream.close();
        writer.close();
    }

    /**
     * 
     * @param response
     * @throws Exception
     */
    @GetMapping("/report2")
    public void export2(HttpServletResponse response) throws Exception{
        List<Answer> list = answerService.findReport2();

        ExcelWriter writer = ExcelUtil.getWriter(true);
       writer.addHeaderAlias("answer1","1. How realistic are the images?");
       writer.addHeaderAlias("answer2","2. How well does the image match the description?");
       writer.addHeaderAlias("answer3","3. How many objects do you think can be recognized in this image?");
       writer.addHeaderAlias("answer4","4. How is the overall quality of this image?");
       writer.addHeaderAlias("answer5","5. Do you like this image?");
       writer.addHeaderAlias("answer6","6. What do you think of the diversity of these images?");

        writer.write(list,true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("answer", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream outputStream = response.getOutputStream();
        writer.flush(outputStream, true);
        outputStream.close();
        writer.close();
    }

    /**
     * 
     * @param response
     * @throws Exception
     */
    @GetMapping("/report3")
    public void export3(HttpServletResponse response) throws Exception{
        List<Answer> list = answerService.findReport3();

        ExcelWriter writer = ExcelUtil.getWriter(true);

       writer.addHeaderAlias("answer1","1. How realistic are the images?");
       writer.addHeaderAlias("answer2","2. How well does the image match the description?");
       writer.addHeaderAlias("answer3","3. How many objects do you think can be recognized in this image?");
       writer.addHeaderAlias("answer4","4. How is the overall quality of this image?");
       writer.addHeaderAlias("answer5","5. Do you like this image?");
       writer.addHeaderAlias("answer6","6. What do you think of the diversity of these images?");

        writer.write(list,true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("answer", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream outputStream = response.getOutputStream();
        writer.flush(outputStream, true);
        outputStream.close();
        writer.close();
    }

    /**
     * 
     * @param response
     * @throws Exception
     */
    @GetMapping("/reportAll")
    public void exportAll(HttpServletResponse response) throws Exception{
        List<Answer> list = answerService.findAllReport();

        ExcelWriter writer = ExcelUtil.getWriter(true);

       writer.addHeaderAlias("answer1","1. Does the image look AI-generated or real?");
       writer.addHeaderAlias("answer2","2. How lifelike are the images?");
       writer.addHeaderAlias("answer3","3. How realistic are the images?");
       writer.addHeaderAlias("answer4","4. How convincing is the image?");
       writer.addHeaderAlias("answer5","5. How can you tell whether this image is real or fake?");
       writer.addHeaderAlias("answer6","6. How similar is the image to the real image?");
       writer.addHeaderAlias("answer7","7. How well does the image match the description?");
       writer.addHeaderAlias("answer8","8. How consistent is the image with the input?");
       writer.addHeaderAlias("answer9","9. How well does the image semantically match the input content?");
       writer.addHeaderAlias("answer10","10. How well does the image achieve the expected effect?");
       writer.addHeaderAlias("answer11","11. How relevant is the image to the input content?");
       writer.addHeaderAlias("answer12","12. How well does the image accurately reflect the input?");
       writer.addHeaderAlias("answer13","13. What do you think of the recognizability of the objects in this image?");
       writer.addHeaderAlias("answer14","14. Can you accurately recognize the objects in this image?");
       writer.addHeaderAlias("answer15","15. How many objects do you think can be recognized in this image?");
       writer.addHeaderAlias("answer16","16. How satisfied are you with the recognizability of objects in this image?");
       writer.addHeaderAlias("answer17","17. How good does the overall quality of the entire image appear to be?");
       writer.addHeaderAlias("answer18","18. How is the overall quality of this image?");
       writer.addHeaderAlias("answer19","19. Are you satisfied with the overall quality of this image?");
       writer.addHeaderAlias("answer20","20. How would you rate the overall quality of this image?");
       writer.addHeaderAlias("answer21","21. Do you like this image?");
       writer.addHeaderAlias("answer22","22. Will this image give you visual pleasure?");
       writer.addHeaderAlias("answer23","23. How much do you prefer this image?");
       writer.addHeaderAlias("answer24","24. How well does this image match your preferences?");
       writer.addHeaderAlias("answer25","25. What do you think of the diversity of these images?");
       writer.addHeaderAlias("answer26","26. How much have these images changed?");
       writer.addHeaderAlias("answer27","27. How different are these images?");
       writer.addHeaderAlias("answer28","28. Are you satisfied with the diversity of these images?");
        writer.write(list,true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("answer", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream outputStream = response.getOutputStream();
        writer.flush(outputStream, true);
        outputStream.close();
        writer.close();
    }

}

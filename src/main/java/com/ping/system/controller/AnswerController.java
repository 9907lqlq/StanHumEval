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

/**
 * @author ping
 * @date 2023/10/22
 */
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
     * 分页查询
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
     * 添加用户
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
     * 添加用户
     *
     * @return
     */
    @PostMapping("/insertAnswerUsername")
    public boolean insertAnswerUsername(@RequestParam String username,@RequestParam String surveyName, HttpServletRequest request) {
        List<Answer> answerList = answerService.selectAnswerByNameAndSurvey(username,surveyName);
        //获取IP地址
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
     * 导出问卷一接口
     *
     * @param response
     * @throws Exception
     */
    @GetMapping("/report1")
    public void export1(HttpServletResponse response) throws Exception{
        List<Answer> list = answerService.findReport1();

        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        // 自定义标题别名
        writer.addHeaderAlias("answerBeginTime","问卷开始时间");
        writer.addHeaderAlias("answerTime","问卷结束时间");
        writer.addHeaderAlias("answerUser","问卷作答者");
        writer.addHeaderAlias("diff","作答时长(s)");
        writer.addHeaderAlias("surveyName","问卷");
        writer.addHeaderAlias("hostAddress","主机IP");
        writer.addHeaderAlias("hostName","主机名称");
        writer.addHeaderAlias("answer1","问题一");
        writer.addHeaderAlias("answer2","问题二");
        writer.addHeaderAlias("answer3","问题三");
        writer.addHeaderAlias("answer4","问题四");
        writer.addHeaderAlias("answer5","问题五");
        writer.addHeaderAlias("answer6","问题六");
        writer.addHeaderAlias("answer7","问题七");
        writer.addHeaderAlias("answer8","问题八");
        writer.addHeaderAlias("answer9","问题九");
        writer.addHeaderAlias("answer10","问题十");
        writer.addHeaderAlias("answer11","问题十一");
        writer.addHeaderAlias("answer12","问题十二");
        writer.addHeaderAlias("answer13","问题十三");
        writer.addHeaderAlias("answer14","问题十四");
        writer.addHeaderAlias("answer15","问题十五");
        writer.addHeaderAlias("answer16","问题十六");
        writer.addHeaderAlias("answer17","问题十七");
        writer.addHeaderAlias("answer18","问题十八");
        writer.addHeaderAlias("answer19","问题十九");
        writer.addHeaderAlias("answer20","问题二十");
        writer.addHeaderAlias("answer21","问题二十一");
        writer.addHeaderAlias("answer22","问题二十二");
        writer.addHeaderAlias("answer23","问题二十三");
        writer.addHeaderAlias("answer24","问题二十四");
        writer.addHeaderAlias("answer25","问题二十五");
        writer.addHeaderAlias("answer26","问题二十六");
        writer.addHeaderAlias("answer27","问题二十七");
        writer.addHeaderAlias("answer28","问题二十八");
        writer.addHeaderAlias("answer29","问题二十九");
        writer.addHeaderAlias("answer30","问题三十");
        writer.addHeaderAlias("answer31","问题三十一");
        writer.addHeaderAlias("answer32","问题三十二");
        writer.addHeaderAlias("answer33","问题三十三");
        writer.addHeaderAlias("answer34","问题三十四");
        writer.addHeaderAlias("answer35","问题三十五");
        writer.addHeaderAlias("answer36","问题三十六");
        writer.addHeaderAlias("answer37","问题三十七");
        writer.addHeaderAlias("answer38","问题三十八");
        writer.addHeaderAlias("answer39","问题三十九");
        writer.addHeaderAlias("answer40","问题四十");
        writer.addHeaderAlias("answer41","问题四十一");
        writer.addHeaderAlias("answer42","问题四十二");
        writer.addHeaderAlias("answer43","问题四十三");
        writer.addHeaderAlias("answer44","问题四十四");
        writer.addHeaderAlias("answer45","问题四十五");
        writer.addHeaderAlias("answer46","问题四十六");
        writer.addHeaderAlias("answer47","问题四十七");
        writer.addHeaderAlias("answer48","问题四十八");
        writer.addHeaderAlias("answer49","问题四十九");
        writer.addHeaderAlias("answer50","问题五十");
        writer.addHeaderAlias("answer51","问题五十一");
        writer.addHeaderAlias("answer52","问题五十二");
        writer.addHeaderAlias("answer53","问题五十三");
        writer.addHeaderAlias("answer54","问题五十四");
        writer.addHeaderAlias("answer55","问题五十五");
        writer.addHeaderAlias("answer56","问题五十六");
        writer.addHeaderAlias("answer57","问题五十七");
        writer.addHeaderAlias("answer58","问题五十八");
        writer.addHeaderAlias("answer59","问题五十九");
        writer.addHeaderAlias("answer60","问题六十");

//        writer.addHeaderAlias("answer1","1. Does the image look AI-generated or real?");
//        writer.addHeaderAlias("answer2","2. How lifelike are the images?");
//        writer.addHeaderAlias("answer3","3. How realistic are the images?");
//        writer.addHeaderAlias("answer4","4. How convincing is the image?");
//        writer.addHeaderAlias("answer5","5. How can you tell whether this image is real or fake?");
//        writer.addHeaderAlias("answer6","6. How similar is the image to the real image?");
//        writer.addHeaderAlias("answer7","7. How well does the image match the description?");
//        writer.addHeaderAlias("answer8","8. How consistent is the image with the input?");
//        writer.addHeaderAlias("answer9","9. How well does the image semantically match the input content?");
//        writer.addHeaderAlias("answer10","10. How well does the image achieve the expected effect?");
//        writer.addHeaderAlias("answer11","11. How relevant is the image to the input content?");
//        writer.addHeaderAlias("answer12","12. How well does the image accurately reflect the input?");
//        writer.addHeaderAlias("answer13","13. What do you think of the recognizability of the objects in this image?");
//        writer.addHeaderAlias("answer14","14. Can you accurately recognize the objects in this image?");
//        writer.addHeaderAlias("answer15","15. How many objects do you think can be recognized in this image?");
//        writer.addHeaderAlias("answer16","16. How satisfied are you with the recognizability of objects in this image?");
//        writer.addHeaderAlias("answer17","17. How good does the overall quality of the entire image appear to be?");
//        writer.addHeaderAlias("answer18","18. How is the overall quality of this image?");
//        writer.addHeaderAlias("answer19","19. Are you satisfied with the overall quality of this image?");
//        writer.addHeaderAlias("answer20","20. How would you rate the overall quality of this image?");
//        writer.addHeaderAlias("answer21","21. Do you like this image?");
//        writer.addHeaderAlias("answer22","22. Will this image give you visual pleasure?");
//        writer.addHeaderAlias("answer23","23. How much do you prefer this image?");
//        writer.addHeaderAlias("answer24","24. How well does this image match your preferences?");
//        writer.addHeaderAlias("answer25","25. What do you think of the diversity of these images?");
//        writer.addHeaderAlias("answer26","26. How much have these images changed?");
//        writer.addHeaderAlias("answer27","27. How different are these images?");
//        writer.addHeaderAlias("answer28","28. Are you satisfied with the diversity of these images?");

        // 一次性写出list内的对象到excel，使用默认样样式，强制输出标题
        writer.write(list,true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("问卷信息1", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream outputStream = response.getOutputStream();
        writer.flush(outputStream, true);
        outputStream.close();
        writer.close();
    }

    /**
     * 导出问卷二接口
     * @param response
     * @throws Exception
     */
    @GetMapping("/report2")
    public void export2(HttpServletResponse response) throws Exception{
        List<Answer> list = answerService.findReport2();

        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        // 自定义标题别名
        writer.addHeaderAlias("answerBeginTime","问卷开始时间");
        writer.addHeaderAlias("answerTime","问卷结束时间");
        writer.addHeaderAlias("answerUser","问卷作答者");
        writer.addHeaderAlias("diff","作答时长(s)");
        writer.addHeaderAlias("surveyName","问卷");
        writer.addHeaderAlias("hostAddress","主机IP");
        writer.addHeaderAlias("hostName","主机名称");
        writer.addHeaderAlias("answer1","问题一");
        writer.addHeaderAlias("answer2","问题二");
        writer.addHeaderAlias("answer3","问题三");
        writer.addHeaderAlias("answer4","问题四");
        writer.addHeaderAlias("answer5","问题五");
        writer.addHeaderAlias("answer6","问题六");
//        writer.addHeaderAlias("answer1","1. How realistic are the images?");
//        writer.addHeaderAlias("answer2","2. How well does the image match the description?");
//        writer.addHeaderAlias("answer3","3. How many objects do you think can be recognized in this image?");
//        writer.addHeaderAlias("answer4","4. How is the overall quality of this image?");
//        writer.addHeaderAlias("answer5","5. Do you like this image?");
//        writer.addHeaderAlias("answer6","6. What do you think of the diversity of these images?");

        // 一次性写出list内的对象到excel，使用默认样样式，强制输出标题
        writer.write(list,true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("问卷信息2", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream outputStream = response.getOutputStream();
        writer.flush(outputStream, true);
        outputStream.close();
        writer.close();
    }

    /**
     * 导出问卷三接口
     * @param response
     * @throws Exception
     */
    @GetMapping("/report3")
    public void export3(HttpServletResponse response) throws Exception{
        List<Answer> list = answerService.findReport3();

        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        // 自定义标题别名
        writer.addHeaderAlias("answerBeginTime","问卷开始时间");
        writer.addHeaderAlias("answerTime","问卷结束时间");
        writer.addHeaderAlias("answerUser","问卷作答者");
        writer.addHeaderAlias("diff","作答时长(s)");
        writer.addHeaderAlias("surveyName","问卷");
        writer.addHeaderAlias("hostAddress","主机IP");
        writer.addHeaderAlias("hostName","主机名称");
        writer.addHeaderAlias("answer1","问题一");
        writer.addHeaderAlias("answer2","问题二");
        writer.addHeaderAlias("answer3","问题三");
        writer.addHeaderAlias("answer4","问题四");
        writer.addHeaderAlias("answer5","问题五");
        writer.addHeaderAlias("answer6","问题六");
//        writer.addHeaderAlias("answer1","1. How realistic are the images?");
//        writer.addHeaderAlias("answer2","2. How well does the image match the description?");
//        writer.addHeaderAlias("answer3","3. How many objects do you think can be recognized in this image?");
//        writer.addHeaderAlias("answer4","4. How is the overall quality of this image?");
//        writer.addHeaderAlias("answer5","5. Do you like this image?");
//        writer.addHeaderAlias("answer6","6. What do you think of the diversity of these images?");

        // 一次性写出list内的对象到excel，使用默认样样式，强制输出标题
        writer.write(list,true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("问卷信息3", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream outputStream = response.getOutputStream();
        writer.flush(outputStream, true);
        outputStream.close();
        writer.close();
    }

    /**
     * 导出所有问卷接口
     * @param response
     * @throws Exception
     */
    @GetMapping("/reportAll")
    public void exportAll(HttpServletResponse response) throws Exception{
        List<Answer> list = answerService.findAllReport();

        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        // 自定义标题别名
        writer.addHeaderAlias("answerBeginTime","问卷开始时间");
        writer.addHeaderAlias("answerTime","问卷结束时间");
        writer.addHeaderAlias("diff","作答时长(s)");
        writer.addHeaderAlias("answerUser","问卷作答者");
        writer.addHeaderAlias("surveyName","问卷");
        writer.addHeaderAlias("hostAddress","主机IP");
        writer.addHeaderAlias("hostName","主机名称");
        writer.addHeaderAlias("answer1","问题一");
        writer.addHeaderAlias("answer2","问题二");
        writer.addHeaderAlias("answer3","问题三");
        writer.addHeaderAlias("answer4","问题四");
        writer.addHeaderAlias("answer5","问题五");
        writer.addHeaderAlias("answer6","问题六");
        writer.addHeaderAlias("answer7","问题七");
        writer.addHeaderAlias("answer8","问题八");
        writer.addHeaderAlias("answer9","问题九");
        writer.addHeaderAlias("answer10","问题十");
        writer.addHeaderAlias("answer11","问题十一");
        writer.addHeaderAlias("answer12","问题十二");
        writer.addHeaderAlias("answer13","问题十三");
        writer.addHeaderAlias("answer14","问题十四");
        writer.addHeaderAlias("answer15","问题十五");
        writer.addHeaderAlias("answer16","问题十六");
        writer.addHeaderAlias("answer17","问题十七");
        writer.addHeaderAlias("answer18","问题十八");
        writer.addHeaderAlias("answer19","问题十九");
        writer.addHeaderAlias("answer20","问题二十");
        writer.addHeaderAlias("answer21","问题二十一");
        writer.addHeaderAlias("answer22","问题二十二");
        writer.addHeaderAlias("answer23","问题二十三");
        writer.addHeaderAlias("answer24","问题二十四");
        writer.addHeaderAlias("answer25","问题二十五");
        writer.addHeaderAlias("answer26","问题二十六");
        writer.addHeaderAlias("answer27","问题二十七");
        writer.addHeaderAlias("answer28","问题二十八");
        writer.addHeaderAlias("answer29","问题二十九");
        writer.addHeaderAlias("answer30","问题三十");
        writer.addHeaderAlias("answer31","问题三十一");
        writer.addHeaderAlias("answer32","问题三十二");
        writer.addHeaderAlias("answer33","问题三十三");
        writer.addHeaderAlias("answer34","问题三十四");
        writer.addHeaderAlias("answer35","问题三十五");
        writer.addHeaderAlias("answer36","问题三十六");
        writer.addHeaderAlias("answer37","问题三十七");
        writer.addHeaderAlias("answer38","问题三十八");
        writer.addHeaderAlias("answer39","问题三十九");
        writer.addHeaderAlias("answer40","问题四十");
        writer.addHeaderAlias("answer41","问题四十一");
        writer.addHeaderAlias("answer42","问题四十二");
        writer.addHeaderAlias("answer43","问题四十三");
        writer.addHeaderAlias("answer44","问题四十四");
        writer.addHeaderAlias("answer45","问题四十五");
        writer.addHeaderAlias("answer46","问题四十六");
        writer.addHeaderAlias("answer47","问题四十七");
        writer.addHeaderAlias("answer48","问题四十八");
        writer.addHeaderAlias("answer49","问题四十九");
        writer.addHeaderAlias("answer50","问题五十");
        writer.addHeaderAlias("answer51","问题五十一");
        writer.addHeaderAlias("answer52","问题五十二");
        writer.addHeaderAlias("answer53","问题五十三");
        writer.addHeaderAlias("answer54","问题五十四");
        writer.addHeaderAlias("answer55","问题五十五");
        writer.addHeaderAlias("answer56","问题五十六");
        writer.addHeaderAlias("answer57","问题五十七");
        writer.addHeaderAlias("answer58","问题五十八");
        writer.addHeaderAlias("answer59","问题五十九");
        writer.addHeaderAlias("answer60","问题六十");

        // 一次性写出list内的对象到excel，使用默认样样式，强制输出标题
        writer.write(list,true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("所有问卷信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream outputStream = response.getOutputStream();
        writer.flush(outputStream, true);
        outputStream.close();
        writer.close();
    }

}

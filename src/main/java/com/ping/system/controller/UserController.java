package com.ping.system.controller;

import com.ping.system.entity.Survey;
import com.ping.system.entity.User;
import com.ping.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author ping
 * @date 2023/10/22
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/insertUser")
    public Boolean insertUser(@RequestBody User user){
        User user1 = userService.findUserByName(user.getUsername());
        if (user1 != null) {
            return false;
        } else {
            userService.insertUser(user);
            return true;
        }
    }

    /**
     * 插入数据
     *
     * @param user
     * @return
     */
    @PostMapping("/insertUserDetail")
    public Boolean insertUserDetail(@RequestBody User user){
        int num = userService.insertUser(user);
        if (num > 0) {
            return true;
        }
        return false;
    }
}

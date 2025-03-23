package com.ping.system.entity;

import lombok.Data;

/**
 * @author ping
 * @date 2023/10/22
 */
@Data
public class User {
    private Integer id;

    private String username;

    private String gender;

    private Integer age;

    private String occupation;

    private String experience;

    private String time;
}

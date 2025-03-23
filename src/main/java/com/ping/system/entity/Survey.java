package com.ping.system.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author ping
 * @date 2023/10/22
 */
@Data
public class Survey {
    private Integer surveyId;

    private String surveyName;

    private String username;

    private String surveyTime;
}

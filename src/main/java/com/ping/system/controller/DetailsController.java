package com.ping.system.controller;

import com.ping.system.entity.Details;
import com.ping.system.entity.Survey;
import com.ping.system.service.DetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ping
 * @date 2023/10/22
 */
@RestController
@RequestMapping("/details")
public class DetailsController {
    @Autowired
    private DetailsService detailsService;

    @GetMapping("/findById/{detailsId}")
    public Details findById(@PathVariable("detailsId") Integer detailsId){
        System.out.println(detailsService.findById(detailsId));
        return detailsService.findById(detailsId);
    }
}

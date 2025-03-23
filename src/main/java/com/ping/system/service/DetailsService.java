package com.ping.system.service;

import com.ping.system.entity.Details;
import com.ping.system.entity.Survey;
import com.ping.system.mapper.DetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ping
 * @date 2023/10/22
 */
@Service
public class DetailsService {
    @Autowired
    private DetailsMapper detailsMapper;

    public Details findById(Integer detailsId) {
        return detailsMapper.findById(detailsId);
    }
}

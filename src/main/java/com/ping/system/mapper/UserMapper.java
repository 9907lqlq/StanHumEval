package com.ping.system.mapper;

import com.ping.system.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface UserMapper {
    int insertUser(User user);

    User findUserByName(String username);

    int insertUserDetail(User user);
}

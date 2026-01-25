package com.ping.system.mapper;

import com.ping.system.entity.Details;
import com.ping.system.entity.Survey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface DetailsMapper {

    Details findById(@Param("details_id") Integer detailsId);
}

package com.turbid.dataserver.dao.cluster;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CodeLibMapper {

    @Select("SELECT COUNT(*) FROM codelib WHERE code=#{code}")
    int selectCodeLib(@Param("code")String coce);

    @Insert("INSERT INTO codelib(code) VALUES(#{code})")
    int insertCodeLib(@Param("code")String code);

}

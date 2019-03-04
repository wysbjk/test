package com.turbid.fileserver.dao;

import com.turbid.basicapi.entity.file.File;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface FileMapper {

    @Insert("insert into file(code,fg_code,title,size,url,type,addtime) values(#{code},#{fg_code},#{title},#{size},#{url},#{type},date_format(now(),'%Y-%m-%d %H:%i:%s'))")
    int insertFile(File file);


    @Select("select * from file where fg_code =#{fg_code}")
    List<File> selectFiles(@Param("fg_code") String fg_code);

}

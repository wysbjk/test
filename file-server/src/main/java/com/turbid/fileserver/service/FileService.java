package com.turbid.fileserver.service;

import com.turbid.basicapi.entity.BJSON;
import com.turbid.basicapi.tools.DateDealwith;
import com.turbid.fileserver.dao.FileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class FileService {

    @Value("${com.turbid.upload-path.images}")
    private String	imagespath;

    @Autowired
    private FileMapper fileMapper;

    public BJSON images(FilePart filePart, com.turbid.basicapi.entity.file.File fileinfo) throws IOException {
        BJSON json =new BJSON();
        if (filePart != null) {
            if (filePart.filename() != null || "".equals(filePart.filename())) {
                String[] name = filePart.filename().split("\\.");
                if ("BMP".equals(name[1]) || "JPG".equals(name[1])
                        || "JPEG".equals(name[1]) || "bmp".equals(name[1])
                        || "jpg".equals(name[1]) || "jpeg".equals(name[1])|| "PNG".equals(name[1])| "png".equals(name[1])) {
                  File  file= new File(imagespath + DateDealwith.getSHC()+"."+name[1]);
                    // 物理地址
                    filePart.transferTo(file);

                    fileinfo.setSize(file.length());
                    fileinfo.setType(name[1]);
                    fileinfo.setUrl(DateDealwith.getSHC()+"."+name[1]);
                    fileMapper.insertFile(fileinfo);
                    json.setStatus(200);
                    json.setMassages("上传成功!");
                    json.setData(fileinfo);
                    return json;
                } else {
                    json.setStatus(400);
                    json.setMassages("格式不正确!");
                }
            } else {
                json.setStatus(400);
                json.setMassages("请选择文件!");
            }
        } else {
            json.setStatus(400);
            json.setMassages("请选择文件!");
        }
        return json;
    }
}

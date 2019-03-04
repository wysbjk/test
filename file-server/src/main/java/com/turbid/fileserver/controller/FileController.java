package com.turbid.fileserver.controller;

import com.turbid.basicapi.entity.BJSON;
import com.turbid.fileserver.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.io.IOException;


@RestController
public class FileController {

    @Autowired
    FileService fileService;

    @RequestMapping(value = "/images", method = RequestMethod.POST)
    public Mono<BJSON> images(@RequestPart("file") FilePart filePart) {
        try {
            return Mono.just( fileService.images(filePart,null));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

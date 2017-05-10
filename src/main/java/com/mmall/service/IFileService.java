package com.mmall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by zjfsharp on 2017/5/7.
 */
public interface IFileService {

    String upload(MultipartFile file, String path);

}

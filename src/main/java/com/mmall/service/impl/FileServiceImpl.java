package com.mmall.service.impl;

import com.google.common.collect.Lists;
import com.mmall.service.IFileService;
import com.mmall.util.FTPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by zjfsharp on 2017/5/7.
 */
@Service("iFileService")
public class FileServiceImpl implements IFileService {

    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    public String upload(MultipartFile file, String path){
        String fileName = file.getOriginalFilename();
        String fileExtensionName = fileName.substring(0, fileName.lastIndexOf(".")+1);

        String uploadFileName = UUID.randomUUID().toString()+"."+fileExtensionName;
        logger.info("开始长传文件，上传文件的文件名:{},上传的路径:{},新文件名：{}",fileName, path, uploadFileName);

        File fileDir = new File(path);
        if(!fileDir.exists()){
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }

        File targetFile = new File(path, uploadFileName);
        try {
            file.transferTo(targetFile);

            //todo 将 targetfile 上传到FTP服务器上
            FTPUtils.uploadFile(Lists.newArrayList(targetFile));

            //todo 上传完成后，删除 upload 下面的文件
            targetFile.delete();

        } catch (IOException e) {
            //e.printStackTrace();
            logger.error("上传文件异常:",e);
            return null;
        }

        return targetFile.getName();
    }

}

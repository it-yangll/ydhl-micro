package com.ydhl.micro.client.admin.controller.pms.util;

import com.ydhl.micro.client.admin.controller.pms.consts.FileTypeEnum;
import com.ydhl.micro.client.admin.controller.pms.factorType.PmsAttachmentEntity;
import io.swagger.annotations.ApiModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: yangll
 * @description
 * @Date: 2019/5/7
 * @Version 1.0
 */
@ApiModel(value = "文件上传下载")
@Slf4j
@Component
public class UploadFile {

    /** 静态资源访问路径 */
    @Value("${upload.url}")
    private String fileUrl;

    /** 静态资源上传路径 */
    @Value("${upload.dir}")
    private String fileDir;

    /** 静态资源文件夹名称 */
    private static String FILE_FOLDER = new SimpleDateFormat("yyyyMM").format(new Date());

    /**
     * Upload multiple file
     *
     * @param files
     * @return
     */
    public List<PmsAttachmentEntity> uploadMultipleFileHandler(MultipartFile[] files) {
        List<PmsAttachmentEntity> fileUrls = new ArrayList();
        if (files.length == 0) {
            log.info("文件为空！", files);
            return fileUrls;
        }

        for (MultipartFile file : files) {
            /** 单个文件为空，执行下一个文件上传 */
            if (file.isEmpty()) {
                continue;
            }
            /** 获取文件类型 */
            String fileTypePath = fileType(file.getContentType());

            /** 文件上传路径 */
            String path = fileDir + File.separator + fileTypePath + File.separator + FILE_FOLDER;

            /** 资源访问路径 (全路径):fileUrl + File.separator*/
            String fileUrlPath = fileTypePath + File.separator + FILE_FOLDER;

            File targetFile = new File(path);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            PmsAttachmentEntity pmsAttachmentEntity  = getUploadName(file, path, fileUrlPath);
            log.info("Server File url = {}", fileUrls);
            fileUrls.add(pmsAttachmentEntity);
        }
        return fileUrls;
    }


    /**
     * 文件上传并返回 filename
     * @param file
     * @param path
     * @return
     */
    private static PmsAttachmentEntity getUploadName(MultipartFile file, String path, String fileUrlPath) {
        /** 获取文件基本属性 */
        String fileName = System.currentTimeMillis()+"_"+file.getOriginalFilename();
        String fileContentType = file.getContentType();
        Double fileSize = Double.valueOf(file.getSize()/1024);
        String originalFilename = file.getOriginalFilename();
        String fileDir = path+File.separator+fileName;
        String fileUrl = fileUrlPath + File.separator + fileName;

        /** 打印文件详细内容 */
        log.info("文件类型__ContentType=" + fileContentType);
        log.info("文件组件名称__Name=" + file.getName());
        log.info("文件原名称__OriginalFileName=" + originalFilename);
        log.info("文件大小__Size=" + file.getSize() + "byte or " + fileSize + "KB");
        log.info("文件路径__FileUurl="+fileUrlPath + File.separator + fileName);

        PmsAttachmentEntity pae = null;
        // 保存
        try {
            /** 属性添加 */
            pae = new PmsAttachmentEntity();
            String filetype = fileContentType.matches("image.*")? FileTypeEnum.PIC.name() : FileTypeEnum.VIDEO.name();
            pae.setFileType(filetype);
            pae.setFileSize(fileSize);
            pae.setFileName(fileName);
            pae.setOriginalName(originalFilename);
            pae.setFileUrl(fileUrl);
            /** 上传至服务器 */
            file.transferTo(new File(path, fileName));
        } catch (Exception e) {
            pae = null;
            log.error("文件上传失败！",e);
        }
        return pae != null?pae:null;
    }



    /**
      *判断文件类型 ck_file_type
      *@Author: yangll
      *@Date: 2019/5/7 11:45
      *@Version: 1.0
      */
    public static String fileType(String file){
        //图片、视频、文档的路径
        String url="";
        // 文件保存路径
        if(file.matches("image.*")){
            //图片
            url = "goods"+File.separator+"images";
        }else if(file.matches("video.*")){
            //视频
            url = "goods"+File.separator+"video";
        }else {
            //文档
            url = "goods"+File.separator+"document";
        }

        return url;

    }


}

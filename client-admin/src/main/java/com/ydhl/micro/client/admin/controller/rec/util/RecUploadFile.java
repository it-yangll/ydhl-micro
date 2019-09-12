package com.ydhl.micro.client.admin.controller.rec.util;

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
 * @ClassName RecUploadFile
 * @Description RecUploadFile
 * @Author miracle
 * @Date 2019/6/4 16:39
 * @Version 1.0
 */
@ApiModel(value = "文件上传下载")
@Slf4j
@Component
public class RecUploadFile {

    /**
     * 静态资源访问路劲
     */
    @Value("${upload.url}")
    private String fileUrl;

    /**
     * 静态资源上传路劲
     */
    @Value("${upload.dir}")
    private String fileDir;

    /**
     * 静态资源文件夹名称
     */
    private static String FILE_FOLDER = new SimpleDateFormat("yyyyMM").format(new Date());

    /**
     * Upload multiple file
     *
     * @param files
     * @return
     */
    public List<RecAttachmentEntity> uploadMultipleFileHandler(MultipartFile[] files, String modelName) {
        List<RecAttachmentEntity> fileUrls = new ArrayList();
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

            /** 文件上传路劲 */
            String path = fileDir + File.separator +  fileTypePath + File.separator + FILE_FOLDER;

            //存储路径
            String savePath = fileTypePath + File.separator + FILE_FOLDER;

            /** 资源访问路劲 */
            String fileUrlPath = fileUrl + fileTypePath + File.separator + FILE_FOLDER;

            File targetFile = new File(path);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            RecAttachmentEntity pmsAttachmentEntity = getUploadName(file, path, fileUrlPath, modelName, savePath);
            log.info("Server File url = {}", fileUrls);
            fileUrls.add(pmsAttachmentEntity);
        }
        return fileUrls;
    }


    /**
     * 文件上传并返回 filename
     *
     * @param file
     * @param path
     * @return
     */
    private static RecAttachmentEntity getUploadName(MultipartFile file, String path, String fileUrlPath, String modelName, String savePath) {
        /** 获取文件基本属性 */
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        String fileContentType = file.getContentType();
        Double fileSize = Double.valueOf(file.getSize() / 1024);
        String originalFilename = file.getOriginalFilename();
        String fileDir = savePath + File.separator + fileName;
        String fileUrl = fileUrlPath + File.separator + fileName;

        /** 打印文件详细内容 */
        log.info("附件类型__ContentType=" + fileContentType);
        log.info("附件名称__Name=" + file.getName());
        log.info("附件所属模块=" + modelName);
        log.info("附件原名称__OriginalFileName=" + originalFilename);
        log.info("附件大小__Size=" + file.getSize() + "byte or " + fileSize + "KB");
        log.info("附件__FileUurl=" + fileUrlPath + File.separator + fileName);

        RecAttachmentEntity pae = new RecAttachmentEntity();
        // 保存
        try {
            /** 属性添加 */
            pae = new RecAttachmentEntity();
            String filetype = fileContentType.matches("image.*") ? FileEnum.PIC.name() : FileEnum.VIDEO.name();
            pae.setFileType(filetype);
            pae.setFileMoudle(modelName);
            pae.setFileSize(fileSize);
            pae.setFileName(fileName);
            pae.setOriginalName(originalFilename);
            pae.setFileUrl(fileDir.replaceAll("\\\\", "/"));
            pae.setLookUrl(fileUrl.replaceAll("\\\\", "/"));
            /** 上传至服务器 */
            file.transferTo(new File(path, fileName));
        } catch (Exception e) {
            pae = null;
            log.error("文件上传失败！",e);
        }
        return pae != null ? pae : null;
    }


    /**
     * 判断文件类型 ck_file_type
     *
     * @Author: yangll
     * @Date: 2019/5/7 11:45
     * @Version: 1.0
     */
    public static String fileType(String file) {
        //图片、视频、文档的路径
        String url = "";
        // 文件保存路径
        if (file.matches("image.*")) {
            //图片
            url = "rec" + File.separator + "images";
        } else if (file.matches("video.*")) {
            //视频
            url = "rec" + File.separator + "video";
        } else {
            //文档
            url = "rec" + File.separator + "document";
        }

        return url;

    }


}

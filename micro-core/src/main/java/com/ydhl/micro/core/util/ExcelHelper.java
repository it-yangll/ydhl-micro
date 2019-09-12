package com.ydhl.micro.core.util;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName ExcelHelper
 * @Description excel工具类
 * @Author yangll
 * @Date 2019-9-10 11:50:24
 * @Version 1.0
 **/
public class ExcelHelper {

    public static ResponseEntity wirteExcel(List<? extends BaseRowModel> data, String excelFileName) throws IOException {
        return wirteExcel(data, excelFileName, "sheet1", ExcelTypeEnum.XLSX);
    }

    public static ResponseEntity wirteExcel(List<? extends BaseRowModel> data, String excelFileName, String sheetName, ExcelTypeEnum excelTypeEnum) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            //设置导出文件名，CommonUtil.encodeExportFileName 把文件名转码，防止中文乱码
            String fileName = CommonUtil.encodeExportFileName(excelFileName);

            //设置导出响应头  文件名和文件类型
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment", fileName.concat(excelTypeEnum.getValue()));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);


            com.alibaba.excel.ExcelWriter ew = new com.alibaba.excel.ExcelWriter(out, excelTypeEnum);

            Sheet sheet = null;
            if (CollectionUtil.isNotEmpty(data)) {
                sheet = new Sheet(1, 0, data.get(0).getClass());
            } else {
                sheet = new Sheet(1, 0);
            }
            sheet.setSheetName(sheetName);
            sheet.setAutoWidth(true);
            ew.write(data, sheet);
            ew.finish();

            //返回文件
            return new ResponseEntity(out.toByteArray(), headers, HttpStatus.OK);
        } finally {
            out.close();
        }
    }

}

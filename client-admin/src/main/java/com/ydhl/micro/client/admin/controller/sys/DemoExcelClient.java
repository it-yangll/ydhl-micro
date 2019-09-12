package com.ydhl.micro.client.admin.controller.sys;

import cn.hutool.poi.excel.ExcelWriter;
import com.ydhl.micro.api.dto.admin.goods.ResponseGoodsDTO;
import com.ydhl.micro.core.util.CommonUtil;
import com.ydhl.micro.core.util.ExcelHelper;
import com.ydhl.micro.core.util.NonAfterLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DemoExcelClient
 * @Description 导出excel示例代码
 * @Author yangll
 * @Date 2019-9-11 11:39:14
 * @Version 1.0
 **/
@RestController
@Slf4j
public class DemoExcelClient {

    /**
     * @param :
     * @return org.springframework.http.ResponseEntity<byte [ ]> :
     * @Description //导出excel文件。
     * 涉及到文件上传或下载的接口  client命名规则一律要加上streams前缀。
     * 如果接口不需要登录认证，client命名需要以public/开始
     * 如果不希望系统打印请求和响应日志，可以在方法上加上@NonLog注解
     * 如果不希望系统打印请求日志，可以在方法上加上@NonBeforeLog注解(比如上传文件)
     * 如果不希望系统打印响应日志，可以在方法上加上@NonAfterLog注解(比如下载文件)
     * 如果下载文件逻辑是调用（通过feignClient）服务提供者接口,返回数据并写入到excel文件中。那么服务提供者的接口正常返回HttpResultDTO或HttpPageResultDTO统一封装好的对象，
     * 在client层把对象写入到excel中，并返回ResponseEntity<byte[]>对象
     * @Author Ly
     * @Date 2019/5/16 18:03
     **/

    //该接口命名如下 public/streams/down，表示该接口不需要登录即可访问，且是关于文件上传下载方面的接口
    //该方法上添加了NonAfterLog注解，表示不打印响应日志，因为该接口的响应内容是文件内容，顾不需要打印
    //该方法返回对象类型为ResponseEntity<byte[]> 表示该方法返回的是一个字节数组，下载文件统一返回ResponseEntity<byte[]>
    @RequestMapping("public/streams/down")
    @NonAfterLog
    public ResponseEntity<byte[]> tets() throws Exception {
        //设置导出文件名，CommonUtil.encodeExportFileName 把文件名转码，防止中文乱码
        String fileName = CommonUtil.encodeExportFileName("测试.xls");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        //导出场景1.  读取excel模板并返回给前端
//        File file = new File("D:\\1.xls");
//        return new ResponseEntity(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);

        //导出场景2.  查询数据并写入到excel，返回给前端

        //实例化ExcelWriter
        ExcelWriter ew = new ExcelWriter();

        //设置excel sheet页名称
        ew.renameSheet("测试数据");

        //设置标题行
        List<String> headRow = new ArrayList<>();
        headRow.add("标题");
        ew.writeHeadRow(headRow);

        //调用服务提供者接口，得到返回数据
        // 比如  HttpPageResultDTO result = xxFeignClient.pageData(dto);  ew.write(result.getList());
        List<String> data = new ArrayList<>();
        data.add("我是一条测试数据");
        ew.write(data);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        //把数据写出到流
        ew.flush(out);
        //关闭
        ew.close();

        //返回文件
        return new ResponseEntity(out.toByteArray(), headers, HttpStatus.OK);
    }

    @RequestMapping("public/streams/down1")
    @NonAfterLog
    public ResponseEntity<byte[]> easyexcel() throws Exception {
        List<ResponseGoodsDTO> data = new ArrayList<>();
        ResponseGoodsDTO goodsDTO = new ResponseGoodsDTO();
        goodsDTO.setName("奔驰");
        goodsDTO.setCategoryName("300-CL");
        data.add(goodsDTO);
        return ExcelHelper.wirteExcel(data, "测试文件");
    }

}

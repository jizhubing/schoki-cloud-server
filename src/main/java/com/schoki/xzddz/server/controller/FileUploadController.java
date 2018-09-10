package com.schoki.xzddz.server.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author : jzb219@gmail.com
 * @description : 文件上传控制器
 * @date : 2018/9/10 10:52
 */
@RestController
@RequestMapping("/uploads")
@Slf4j
@Api(value = "文件上传相关接口", description = "文件上传相关接口")
public class FileUploadController {

    @GetMapping
    public String index() {
        return "index";
    }

    @PostMapping(value = "/upload1")
    @ResponseBody
    @ApiOperation(value = "单个文件上传", notes = "单个文件上传接口1", httpMethod = "POST")
    public Map<String, String> singelUpload(@RequestParam("file") MultipartFile file) throws IOException {
        log.info("文件类型:[{}]名称:[{}]大小:[{}]", file.getContentType(), file.getOriginalFilename(), file.getSize());
        file.transferTo(new File("E:\\github\\schoki-cloud-server" + file.getOriginalFilename()));
        Map<String, String> result = Maps.newHashMapWithExpectedSize(16);
        result.put("contentType", file.getContentType());
        result.put("fileName", file.getOriginalFilename());
        result.put("fileSize", file.getSize() + "");
        return result;
    }

    @PostMapping(value = "/upload2")
    @ResponseBody
    @ApiOperation(value = "批量文件上传", notes = "批量文件上传接口", httpMethod = "POST")
    public List<Map<String, String>> batchUpload(@RequestParam("files") MultipartFile[] files) {
        if (files == null || files.length == 0) {
            return null;
        }

        List<Map<String, String>> results = Lists.newArrayList();
        Arrays.asList(files).forEach(file -> {
            try {
                file.transferTo(new File("E:\\github\\schoki-cloud-server" + file.getOriginalFilename()));
                Map<String, String> map = Maps.newHashMapWithExpectedSize(16);
                map.put("contentType", file.getContentType());
                map.put("fileName", file.getOriginalFilename());
                map.put("fileSize", file.getSize() + "");
                results.add(map);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return results;
    }

}

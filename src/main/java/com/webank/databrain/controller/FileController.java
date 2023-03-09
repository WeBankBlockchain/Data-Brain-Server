package com.webank.databrain.controller;

import com.webank.databrain.model.resp.CommonResponse;
import com.webank.databrain.service.FileService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @ApiOperation(value = "上传文件")
    @PostMapping("/upload")
    public CommonResponse handleFileUpload(@RequestParam("file") MultipartFile file) throws Exception {
        String filename = fileService.uploadFile(file);
        return CommonResponse.success(filename);
    }

    @ApiOperation(value = "下载文件")
    @GetMapping("/download")
    public ResponseEntity<Resource> handleFileDownload(@RequestParam("filename") String filename) throws IOException {
        return fileService.downloadFile(filename);
    }
}

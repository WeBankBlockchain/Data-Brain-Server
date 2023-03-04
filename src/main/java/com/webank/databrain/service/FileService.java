package com.webank.databrain.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.HashUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import com.webank.databrain.config.SysConfig;
import com.webank.databrain.enums.ErrorEnums;
import com.webank.databrain.error.DataBrainException;
import com.webank.databrain.utils.HashUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.*;

@Service
public class FileService {

    @Autowired
    private SysConfig sysConfig;

    private File fileUploadFolder;
    @PostConstruct
    private void init(){
        this.fileUploadFolder = new File(sysConfig.getFileConfig().getFileDir());
        this.fileUploadFolder.mkdirs();
    }

    public String uploadFile(MultipartFile file) throws Exception{
        String originalFileName = file.getOriginalFilename();
        String suffix = FileUtil.getSuffix(originalFileName);
        suffix = StrUtil.isBlank(suffix)?"":"."+suffix;
        byte[] fileContent = file.getBytes();
        String filename = HexUtil.encodeHexStr(HashUtils.sha256(fileContent)) + suffix;
        File outputFile = new File(this.fileUploadFolder, filename);

        try(InputStream ins = file.getInputStream(); OutputStream os = new BufferedOutputStream(new FileOutputStream(outputFile))){
            IoUtil.copy(ins, os);
        }
        return filename;
    }

    public ResponseEntity<Resource> downloadFile(String filename) {
        File file = new File(this.fileUploadFolder, filename);
        if(!file.exists() || file.isDirectory()){
            throw new DataBrainException(ErrorEnums.FileNotExists);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        headers.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length()));

        // Set the filename header
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");

        Resource resource = new FileSystemResource(file);
        return ResponseEntity.ok().headers(headers).body(resource);
    }




}

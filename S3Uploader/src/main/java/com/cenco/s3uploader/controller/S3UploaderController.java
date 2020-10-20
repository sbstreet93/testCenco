package com.cenco.s3uploader.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cenco.s3uploader.service.AmazonClient;

@RestController
@RequestMapping("/api")
public class S3UploaderController {

    private AmazonClient amazonClient;

    @Autowired
    S3UploaderController(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }

    @PostMapping("/storage")
    public String uploadFile(@RequestPart(value = "file") MultipartFile file) {
        return this.amazonClient.uploadFile(file);
    }
}

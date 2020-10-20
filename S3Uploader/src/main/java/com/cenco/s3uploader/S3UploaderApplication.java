package com.cenco.s3uploader;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan({ "com.cenco.s3uploader", "com.cenco.platform" })
@SpringBootApplication
public class S3UploaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(S3UploaderApplication.class, args);
	}

}

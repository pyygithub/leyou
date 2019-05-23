package com.thtf.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传Service接口
 * @author: pyy
 * @date: 2019/5/23 9:29
 */
public interface UploadService {

    String uploadImage(MultipartFile file);
}

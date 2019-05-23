package com.thtf.service.impl;

import com.thtf.common.enums.ExceptionEnums;
import com.thtf.common.exception.CommonException;
import com.thtf.service.UploadService;
import com.thtf.util.FastDFSClient;
import com.thtf.util.FastDFSFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * 文件上传service实现类
 * @author: pyy
 * @date: 2019/5/23 9:32
 */
@Service
@Slf4j
public class UploadServiceImpl implements UploadService {

    private static final List<String> ALLOW_TYPES = Arrays.asList("image/jpep", "image/png", "image/bmp");

    /**
     * 删除图片
     * @author: pyy
     * @date: 2019/5/23 9:33
     * @param file
     * @return: java.lang.String
     */
    @Override
    public String uploadImage(MultipartFile file) {

        try {
            // 1 图片信息校验
            // 1.1 校验文件类型
            String contentType = file.getContentType();
            if (!ALLOW_TYPES.contains(contentType)) {
                log.error("### 上传失败,文件类型不匹配 ###");
                throw new CommonException(ExceptionEnums.INVALID_FILE_TYPE);
            }

            // 1.2 校验文件内容
            InputStream inputStream = file.getInputStream();
            BufferedImage image = ImageIO.read(inputStream);
            if (image == null) {
                log.error("### 上传失败,文件内容不符合要求 ###");
                throw new CommonException(ExceptionEnums.INVALID_FILE_CONTENT);
            }

            // 2 保存图片
            // 2.1 准备目标路径
            String[] fileAbsolutePath={};
            String fileName = file.getOriginalFilename();
            String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
            int len = file.getInputStream().available();
            byte[] content = new byte[len];
            inputStream.read(content);

            inputStream.close();

            FastDFSFile fastDFSFile = new FastDFSFile(fileName, content, ext);

            // 2.2 保存文件到FastDFS服务器
            fileAbsolutePath = FastDFSClient.upload(fastDFSFile);

            // 2.3 返回保存路径
            String path = FastDFSClient.getTrackerUrl() + fileAbsolutePath[0] + "/" + fileAbsolutePath[1];
            return path;
        } catch (Exception e) {
            log.error("### 图片上传失败 ###");
            throw new CommonException(ExceptionEnums.UPLOAD_IMAGE_FAIL);
        }
    }
}

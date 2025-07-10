package com.atguigu.daijia.driver.service.impl;

import com.atguigu.daijia.common.execption.GuiguException;
import com.atguigu.daijia.common.result.ResultCodeEnum;
import com.atguigu.daijia.driver.config.TencentCloudProperties;
import com.atguigu.daijia.driver.service.CiService;
import com.atguigu.daijia.driver.service.CosService;
import com.atguigu.daijia.model.vo.driver.CosUploadVo;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class CosServiceImpl implements CosService {

    @Autowired
    private TencentCloudProperties tencentCloudProperties;

    @Autowired
    private CiService ciService;

    @Override
    public CosUploadVo upload(MultipartFile file, String path) {
        // 获取cosClient对象
        log.info("开始获取COSClient对象");
        COSClient cosClient = this.getCosClient();
        log.info("成功获取COSClient对象");

        // 文件上传
        // 元数据信息
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentLength(file.getSize());
        meta.setContentEncoding("UTF-8");// 编码类型
        meta.setContentType(file.getContentType());

        // 向存储桶中保存文件
        String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")); // 文件后缀名
        String uploadPath = "/driver/" + path + "/" + UUID.randomUUID().toString().replaceAll("-", "") + fileType;
        // 01.jpg
        // /driver/auth/0o98754.jpg
        PutObjectRequest putObjectRequest = null;
        try {
            // 1 bucket名称
            // 2
            putObjectRequest = new PutObjectRequest(tencentCloudProperties.getBucketPrivate(),
                    uploadPath,
                    file.getInputStream(),
                    meta);
        } catch (IOException e) {
            log.error("创建PutObjectRequest时发生IOException: {}", e.getMessage());
            throw new RuntimeException(e);
        }
        putObjectRequest.setStorageClass(StorageClass.Standard);

        log.info("开始上传文件到COS，上传路径: {}", uploadPath);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest); // 上传文件
        log.info("文件上传成功，上传结果: {}", putObjectResult);

        cosClient.shutdown();// 关闭
        log.info("成功关闭COSClient");

        // 图片审核
//        log.info("开始进行图片审核，审核路径: {}", uploadPath);
//        Boolean imageAuditing = ciService.imageAuditing(uploadPath);
//        log.info("图片审核完成，审核结果: {}", imageAuditing);

//        if (!imageAuditing) {
//            // 删除违规图片
//            log.info("图片审核未通过，开始删除违规图片，删除路径: {}", uploadPath);
//            cosClient.deleteObject(tencentCloudProperties.getBucketPrivate(), uploadPath);
//            log.info("成功删除违规图片");
//            throw new GuiguException(ResultCodeEnum.IMAGE_AUDITION_FAIL);
//        }

        // 返回vo对象
        CosUploadVo cosUploadVo = new CosUploadVo();
        cosUploadVo.setUrl(uploadPath);
        // 图片临时访问url，回显使用
        String imageUrl = this.getImageUrl(uploadPath);
        cosUploadVo.setShowUrl(imageUrl);

        // 添加日志输出
        log.info("显示CosUploadVo: {}", cosUploadVo);

        return cosUploadVo;
    }

    public COSClient getCosClient() {
        String secretId = tencentCloudProperties.getSecretId();
        String secretKey = tencentCloudProperties.getSecretKey();
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置 bucket 的地域, COS 地域
        Region region = new Region(tencentCloudProperties.getRegion());
        ClientConfig clientConfig = new ClientConfig(region);
        // 这里建议设置使用 https 协议
        clientConfig.setHttpProtocol(HttpProtocol.https);
        // 3 生成 cos 客户端。
        log.info("开始生成COSClient，使用的secretId: {}, secretKey: {}", secretId, secretKey);
        COSClient cosClient = new COSClient(cred, clientConfig);
        log.info("成功生成COSClient");
        return cosClient;
    }

    // 获取临时签名URL
    @Override
    public String getImageUrl(String path) {
        if (!StringUtils.hasText(path)) {
            log.info("传入的path为空，无法生成临时签名URL");
            return "";
        }
        // 获取cosclient对象
        log.info("开始获取COSClient对象以生成临时签名URL");
        COSClient cosClient = this.getCosClient();
        log.info("成功获取COSClient对象以生成临时签名URL");

        // GeneratePresignedUrlRequest
        GeneratePresignedUrlRequest request =
                new GeneratePresignedUrlRequest(tencentCloudProperties.getBucketPrivate(),
                        path, HttpMethodName.GET);
        // 设置临时URL有效期为15分钟
        Date date = new DateTime().plusMinutes(15).toDate();
        request.setExpiration(date);

        log.info("开始生成临时签名URL，请求路径: {}", path);
        // 调用方法获取
        URL url = cosClient.generatePresignedUrl(request);
        log.info("成功生成临时签名URL: {}", url.toString());

        cosClient.shutdown();
        log.info("成功关闭COSClient");

        return url.toString();
    }
}
package com.atguigu.daijia.enterprise.client;

import com.atguigu.daijia.common.result.Result;
import com.atguigu.daijia.model.entity.enterprise.EnterpriseInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 企业信息服务FeignClient
 */
@FeignClient(name = "service-enterprise")
public interface EnterpriseInfoFeignClient {

    /**
     * 获取企业信息
     */
    @GetMapping("/enterprise/getEnterpriseInfo/{enterpriseId}")
    Result<EnterpriseInfo> getEnterpriseInfo(@PathVariable("enterpriseId") Long enterpriseId);
}

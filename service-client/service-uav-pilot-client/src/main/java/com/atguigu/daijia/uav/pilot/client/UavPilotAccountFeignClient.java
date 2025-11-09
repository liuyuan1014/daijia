package com.atguigu.daijia.uav.pilot.client;

import com.atguigu.daijia.common.result.Result;
import com.atguigu.daijia.model.form.driver.TransferForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 无人机驾驶员账户服务FeignClient
 */
@FeignClient(name = "service-uav-pilot")
public interface UavPilotAccountFeignClient {

    /**
     * 转账
     */
    @PostMapping("/uavPilotAccount/transfer")
    Result<Boolean> transfer(@RequestBody TransferForm transferForm);
}

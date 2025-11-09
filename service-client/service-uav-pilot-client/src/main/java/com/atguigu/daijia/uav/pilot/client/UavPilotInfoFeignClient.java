package com.atguigu.daijia.uav.pilot.client;

import com.atguigu.daijia.common.result.Result;
import com.atguigu.daijia.model.entity.uav_pilot.UavPilotSet;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 无人机驾驶员信息服务FeignClient
 */
@FeignClient(name = "service-uav-pilot")
public interface UavPilotInfoFeignClient {

    /**
     * 获取无人机驾驶员个性化设置
     */
    @GetMapping("/uavPilot/getUavPilotSet/{uavPilotId}")
    Result<UavPilotSet> getUavPilotSet(@PathVariable("uavPilotId") Long uavPilotId);
}

package com.atguigu.daijia.task.client;

import com.atguigu.daijia.common.result.Result;
import com.atguigu.daijia.model.entity.task.TaskInfo;
import com.atguigu.daijia.model.vo.task.TaskRewardVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 任务服务FeignClient
 */
@FeignClient(name = "service-task")
public interface TaskInfoFeignClient {

    /**
     * 获取任务信息
     */
    @GetMapping("/task/getTaskInfo/{taskId}")
    Result<TaskInfo> getTaskInfo(@PathVariable("taskId") Long taskId);

    /**
     * 获取任务状态
     */
    @GetMapping("/task/getTaskStatus/{taskId}")
    Result<Integer> getTaskStatus(@PathVariable("taskId") Long taskId);

    /**
     * 获取任务奖励费用
     */
    @GetMapping("/task/getTaskRewardFee/{taskNo}")
    Result<TaskRewardVo> getTaskRewardFee(@PathVariable("taskNo") String taskNo);

    /**
     * 更新任务支付状态
     */
    @GetMapping("/task/updateTaskPayStatus/{taskNo}")
    Result<Boolean> updateTaskPayStatus(@PathVariable("taskNo") String taskNo);
}

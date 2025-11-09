package com.atguigu.daijia.task.controller;

import com.atguigu.daijia.common.login.GuiguLogin;
import com.atguigu.daijia.common.result.Result;
import com.atguigu.daijia.common.result.ResultCodeEnum;
import com.atguigu.daijia.common.util.AuthContextHolder;
import com.atguigu.daijia.task.service.TaskService;
import com.atguigu.daijia.model.form.task.ExpectTaskForm;
import com.atguigu.daijia.model.form.task.SubmitTaskForm;
import com.atguigu.daijia.model.form.map.CalculateDrivingLineForm;
import com.atguigu.daijia.model.form.payment.CreateWxPaymentForm;
import com.atguigu.daijia.model.vo.base.PageVo;
import com.atguigu.daijia.model.vo.task.ExpectTaskVo;
import com.atguigu.daijia.model.vo.uav_pilot.UavPilotInfoVo;
import com.atguigu.daijia.model.vo.map.DrivingLineVo;
import com.atguigu.daijia.model.vo.map.TaskLocationVo;
import com.atguigu.daijia.model.vo.map.TaskServiceLastLocationVo;
import com.atguigu.daijia.model.vo.task.CurrentTaskInfoVo;
import com.atguigu.daijia.model.vo.task.TaskInfoVo;
import com.atguigu.daijia.model.vo.payment.WxPrepayVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "任务API接口管理")
@RestController
@RequestMapping("/task")
@SuppressWarnings({"unchecked", "rawtypes"})
public class TaskController {
    //TODO 后续完善，目前假设企业当前没有任务
    @Operation(summary = "查找企业端当前任务")
    @GuiguLogin
    @GetMapping("/searchEnterpriseCurrentTask")
    public Result<CurrentTaskInfoVo> searchEnterpriseCurrentTask() {
        CurrentTaskInfoVo currentTaskInfoVo = new CurrentTaskInfoVo();
        currentTaskInfoVo.setIsHasCurrentTask(false);
        return Result.ok(currentTaskInfoVo);
    }
//    @Operation(summary = "企业端查找当前任务")
//    @GuiguLogin
//    @GetMapping("/searchEnterpriseCurrentTask")
//    public Result<CurrentTaskInfoVo> searchEnterpriseCurrentTask() {
//        Long enterpriseId = AuthContextHolder.getUserId();
//        return Result.ok(taskService.searchEnterpriseCurrentTask(enterpriseId));
//    }
//
    @Autowired
    private TaskService taskService;

    @Operation(summary = "预估任务数据")
    @GuiguLogin
    @PostMapping("/expectTask")
    public Result<ExpectTaskVo> expectTask(@RequestBody ExpectTaskForm expectTaskForm) {
        return Result.ok(taskService.expectTask(expectTaskForm));
    }

    @Operation(summary = "企业下单")
    @GuiguLogin
    @PostMapping("/submitTask")
    public Result<Long> submitTask(@RequestBody SubmitTaskForm submitTaskForm) {
        submitTaskForm.setEnterpriseId(AuthContextHolder.getUserId());
        return Result.ok(taskService.submitTask(submitTaskForm));
    }

    @Operation(summary = "查询任务状态")
    @GuiguLogin
    @GetMapping("/getTaskStatus/{taskId}")
    public Result<Integer> getTaskStatus(@PathVariable Long taskId) {
        return Result.ok(taskService.getTaskStatus(taskId));
    }

    @Operation(summary = "获取任务信息")
    @GuiguLogin
    @GetMapping("/getTaskInfo/{taskId}")
    public Result<TaskInfoVo> getTaskInfo(@PathVariable Long taskId) {
        Long enterpriseId = AuthContextHolder.getUserId();
        return Result.ok(taskService.getTaskInfo(taskId, enterpriseId));
    }

    @Operation(summary = "根据任务id获取飞手基本信息")
    @GuiguLogin
    @GetMapping("/getUavPilotInfo/{taskId}")
    public Result<UavPilotInfoVo> getUavPilotInfo(@PathVariable Long taskId) {
        Long enterpriseId = AuthContextHolder.getUserId();
        return Result.ok(taskService.getUavPilotInfo(taskId, enterpriseId));
    }

    @Operation(summary = "飞手赶往任务起始点：获取任务经纬度位置")
    @GuiguLogin
    @GetMapping("/getCacheTaskLocation/{taskId}")
    public Result<TaskLocationVo> getTaskLocation(@PathVariable Long taskId) {
        return Result.ok(taskService.getCacheTaskLocation(taskId));
    }

    @Operation(summary = "计算最佳驾驶线路")
    @GuiguLogin
    @PostMapping("/calculateDrivingLine")
    public Result<DrivingLineVo> calculateDrivingLine(@RequestBody CalculateDrivingLineForm calculateDrivingLineForm) {
        return Result.ok(taskService.calculateDrivingLine(calculateDrivingLineForm));
    }

    @Operation(summary = "任务服务：获取任务服务最后一个位置信息")
    @GuiguLogin
    @GetMapping("/getTaskServiceLastLocation/{taskId}")
    public Result<TaskServiceLastLocationVo> getTaskServiceLastLocation(@PathVariable Long taskId) {
        return Result.ok(taskService.getTaskServiceLastLocation(taskId));
    }

    @Operation(summary = "获取企业任务分页列表")
    @GuiguLogin
    @GetMapping("findEnterpriseTaskPage/{page}/{limit}")
    public Result<PageVo> findEnterpriseTaskPage(
            @Parameter(name = "page", description = "当前页码", required = true)
            @PathVariable Long page,

            @Parameter(name = "limit", description = "每页记录数", required = true)
            @PathVariable Long limit) {
        Long enterpriseId = AuthContextHolder.getUserId();
        PageVo pageVo = taskService.findEnterpriseTaskPage(enterpriseId, page, limit);
        return Result.ok(pageVo);
    }

    @Operation(summary = "创建微信支付")
    @GuiguLogin
    @PostMapping("/createWxPayment")
    public Result<WxPrepayVo> createWxPayment(@RequestBody CreateWxPaymentForm createWxPaymentForm) {
        Long enterpriseId = AuthContextHolder.getUserId();
        createWxPaymentForm.setEnterpriseId(enterpriseId);
        return Result.ok(taskService.createWxPayment(createWxPaymentForm));
    }

    @Operation(summary = "支付状态查询")
    @GuiguLogin
    @GetMapping("/queryPayStatus/{taskNo}")
    public Result<Boolean> queryPayStatus(@PathVariable String taskNo) {
        return Result.ok(taskService.queryPayStatus(taskNo));
    }
}

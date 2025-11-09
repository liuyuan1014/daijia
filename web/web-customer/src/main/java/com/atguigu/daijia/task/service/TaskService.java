package com.atguigu.daijia.task.service;

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
import com.atguigu.daijia.model.vo.task.TaskInfoVo;
import com.atguigu.daijia.model.vo.payment.WxPrepayVo;

public interface TaskService {

    //预估任务
    ExpectTaskVo expectTask(ExpectTaskForm expectTaskForm);

    //提交任务
    Long submitTask(SubmitTaskForm submitTaskForm);

    //获取任务状态
    Integer getTaskStatus(Long taskId);

    //获取任务信息
    TaskInfoVo getTaskInfo(Long taskId, Long enterpriseId);

    //获取飞手信息
    UavPilotInfoVo getUavPilotInfo(Long taskId, Long enterpriseId);

    //获取任务位置缓存
    TaskLocationVo getCacheTaskLocation(Long taskId);

    //计算驾驶线路
    DrivingLineVo calculateDrivingLine(CalculateDrivingLineForm calculateDrivingLineForm);

    //获取任务服务最后位置
    TaskServiceLastLocationVo getTaskServiceLastLocation(Long taskId);

    //分页获取企业任务
    PageVo findEnterpriseTaskPage(Long enterpriseId, Long page, Long limit);

    //创建微信支付
    WxPrepayVo createWxPayment(CreateWxPaymentForm createWxPaymentForm);

    //查询支付状态
    Boolean queryPayStatus(String taskNo);
}

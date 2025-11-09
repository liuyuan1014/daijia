package com.atguigu.daijia.task.service.impl;

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
import com.atguigu.daijia.model.vo.task.TaskInfoVo;
import com.atguigu.daijia.model.vo.payment.WxPrepayVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

    @Override
    public ExpectTaskVo expectTask(ExpectTaskForm expectTaskForm) {
        return null;
    }

    @Override
    public Long submitTask(SubmitTaskForm submitTaskForm) {
        return null;
    }

    @Override
    public Integer getTaskStatus(Long taskId) {
        return null;
    }

    @Override
    public TaskInfoVo getTaskInfo(Long taskId, Long enterpriseId) {
        return null;
    }

    @Override
    public UavPilotInfoVo getUavPilotInfo(Long taskId, Long enterpriseId) {
        return null;
    }

    @Override
    public TaskLocationVo getCacheTaskLocation(Long taskId) {
        return null;
    }

    @Override
    public DrivingLineVo calculateDrivingLine(CalculateDrivingLineForm calculateDrivingLineForm) {
        return null;
    }

    @Override
    public TaskServiceLastLocationVo getTaskServiceLastLocation(Long taskId) {
        return null;
    }

    @Override
    public PageVo findEnterpriseTaskPage(Long enterpriseId, Long page, Long limit) {
        return null;
    }

    @Override
    public WxPrepayVo createWxPayment(CreateWxPaymentForm createWxPaymentForm) {
        return null;
    }

    @Override
    public Boolean queryPayStatus(String taskNo) {
        return null;
    }
}

package com.atguigu.daijia.task.service.impl;

import com.atguigu.daijia.common.result.Result;
import com.atguigu.daijia.model.entity.task.TaskInfo;
import com.atguigu.daijia.model.form.task.TaskInfoForm;
import com.atguigu.daijia.model.query.task.TaskInfoQuery;
import com.atguigu.daijia.task.mapper.TaskInfoMapper;
import com.atguigu.daijia.task.service.TaskInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * TaskInfoServiceImpl
 */
@Service
public class TaskInfoServiceImpl extends ServiceImpl<TaskInfoMapper, TaskInfo> implements TaskInfoService {

    @Override
    public Result<?> list(TaskInfoQuery query) {
        // TODO: Implement list logic
        return Result.ok();
    }

    @Override
    public Result<?> get(Long id) {
        TaskInfo info = this.getById(id);
        return Result.ok(info);
    }

    @Override
    public Result<?> save(TaskInfoForm form) {
        TaskInfo info = new TaskInfo();
        BeanUtils.copyProperties(form, info);
        this.save(info);
        return Result.ok(info);
    }

    @Override
    public Result<?> update(TaskInfoForm form) {
        TaskInfo info = new TaskInfo();
        BeanUtils.copyProperties(form, info);
        this.updateById(info);
        return Result.ok();
    }

    @Override
    public Result<?> delete(Long id) {
        this.removeById(id);
        return Result.ok();
    }
}

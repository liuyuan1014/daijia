package com.atguigu.daijia.task.service;

import com.atguigu.daijia.common.result.Result;
import com.atguigu.daijia.model.entity.task.TaskInfo;
import com.atguigu.daijia.model.form.task.TaskInfoForm;
import com.atguigu.daijia.model.query.task.TaskInfoQuery;
import com.baomidou.mybatisplus.service.IService;

/**
 * TaskInfoService
 */
public interface TaskInfoService extends IService<TaskInfo> {

    /**
     * Get list
     */
    Result<?> list(TaskInfoQuery query);

    /**
     * Get by id
     */
    Result<?> get(Long id);

    /**
     * Save
     */
    Result<?> save(TaskInfoForm form);

    /**
     * Update
     */
    Result<?> update(TaskInfoForm form);

    /**
     * Delete
     */
    Result<?> delete(Long id);
}

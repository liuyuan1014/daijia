package com.atguigu.daijia.task.controller;

import com.atguigu.daijia.common.result.Result;
import com.atguigu.daijia.model.entity.task.TaskInfo;
import com.atguigu.daijia.model.form.task.TaskInfoForm;
import com.atguigu.daijia.model.query.task.TaskInfoQuery;
import com.atguigu.daijia.task.service.TaskInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * TaskInfoController
 */
@Tag(name = "Task Info Management")
@RestController
@RequestMapping("/admin/task/info")
public class TaskInfoController {

    @Autowired
    private TaskInfoService taskInfoService;

    @Operation(summary = "Get Task Info List")
    @PostMapping("/list")
    public Result<?> list(@RequestBody TaskInfoQuery query) {
        return taskInfoService.list(query);
    }

    @Operation(summary = "Get Task Info by ID")
    @GetMapping("/{id}")
    public Result<?> get(@PathVariable Long id) {
        return taskInfoService.get(id);
    }

    @Operation(summary = "Create Task Info")
    @PostMapping("/save")
    public Result<?> save(@RequestBody TaskInfoForm form) {
        return taskInfoService.save(form);
    }

    @Operation(summary = "Update Task Info")
    @PostMapping("/update")
    public Result<?> update(@RequestBody TaskInfoForm form) {
        return taskInfoService.update(form);
    }

    @Operation(summary = "Delete Task Info")
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        return taskInfoService.delete(id);
    }
}

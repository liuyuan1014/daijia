package com.atguigu.daijia.model.entity.task;

import com.atguigu.daijia.model.entity.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "TaskMonitor")
@TableName("task_monitor")
public class TaskMonitor extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @Schema(description = "任务ID")
	@TableField("task_id")
	private Long taskId;

    @Schema(description = "文件个数")
	@TableField("file_num")
	private Integer fileNum;

    @Schema(description = "需要审核的个数")
	@TableField("audit_num")
	private Integer auditNum;

    @Schema(description = "是否报警")
	@TableField("is_alarm")
	private Integer isAlarm;

    @Schema(description = "状态")
	@TableField("status")
	private Integer status;

}

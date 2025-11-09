package com.atguigu.daijia.model.entity.task;

import com.atguigu.daijia.model.entity.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(description = "TaskStatusLog")
@TableName("task_status_log")
public class TaskStatusLog extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @Schema(description = "taskId")
	@TableField("task_id")
	private Long taskId;

    @Schema(description = "任务状态")
	@TableField("order_status")
	private Integer orderStatus;

    @Schema(description = "操作时间")
	@TableField("operate_time")
	private Date operateTime;

}

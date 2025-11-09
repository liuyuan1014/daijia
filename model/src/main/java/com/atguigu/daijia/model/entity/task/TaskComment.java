package com.atguigu.daijia.model.entity.task;

import com.atguigu.daijia.model.entity.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "TaskComment")
@TableName("task_comment")
public class TaskComment extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @Schema(description = "任务ID")
	@TableField("task_id")
	private Long taskId;

    @Schema(description = "无人机操作员ID")
	@TableField("pilot_id")
	private Long pilotId;

    @Schema(description = "企业ID")
	@TableField("enterprise_id")
	private Long enterpriseId;

    @Schema(description = "评分，1星~5星")
	@TableField("rate")
	private Integer rate;

    @Schema(description = "备注")
	@TableField("remark")
	private String remark;

    @Schema(description = "状态，1未申诉，2已申诉，3申诉失败，4申诉成功")
	@TableField("status")
	private Integer status;

    @Schema(description = "申诉工作流ID")
	@TableField("instance_id")
	private String instanceId;

}

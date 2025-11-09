package com.atguigu.daijia.model.entity.task;

import com.atguigu.daijia.model.entity.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "TaskTrack")
@TableName("task_track")
public class TaskTrack extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @Schema(description = "任务id")
	@TableField("task_id")
	private Long taskId;

    @Schema(description = "无人机操作员id")
	@TableField("pilot_id")
	private Long pilotId;

    @Schema(description = "企业id")
	@TableField("enterprise_id")
	private Long enterpriseId;

    @Schema(description = "经度")
	@TableField("longitude")
	private String longitude;

    @Schema(description = "纬度")
	@TableField("latitude")
	private String latitude;

    @Schema(description = "速度")
	@TableField("speed")
	private String speed;

}

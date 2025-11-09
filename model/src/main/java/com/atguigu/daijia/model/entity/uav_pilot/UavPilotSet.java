package com.atguigu.daijia.model.entity.uav_pilot;

import com.atguigu.daijia.model.entity.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "UavPilotSet")
@TableName("uav_pilot_set")
public class UavPilotSet extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @Schema(description = "无人机操作员ID")
	@TableField("pilot_id")
	private Long pilotId;

	@Schema(description = "服务状态 1：开始接任务 0：未接任务")
	@TableField("service_status")
	private Integer serviceStatus;

    @Schema(description = "飞行任务里程设置")
	@TableField("order_distance")
	private BigDecimal orderDistance;

    @Schema(description = "接任务里程设置")
	@TableField("accept_distance")
	private BigDecimal acceptDistance;

    @Schema(description = "是否自动接任务")
	@TableField("is_auto_accept")
	private Integer isAutoAccept;

}

package com.atguigu.daijia.model.entity.task;

import com.atguigu.daijia.model.entity.base.BaseEntity;
import com.atguigu.daijia.model.enums.OrderStatus;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Schema(description = "TaskInfo")
@TableName("task_info")
public class TaskInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @Schema(description = "企业ID")
	@TableField("enterprise_id")
	private Long enterpriseId;

    @Schema(description = "任务号")
	@TableField("task_no")
	private String taskNo;

    @Schema(description = "起始地点")
	@TableField("start_location")
	private String startLocation;

    @Schema(description = "起始地点经度")
	@TableField("start_point_longitude")
	private BigDecimal startPointLongitude;

    @Schema(description = "起始点纬度")
	@TableField("start_point_latitude")
	private BigDecimal startPointLatitude;

    @Schema(description = "结束地点")
	@TableField("end_location")
	private String endLocation;

    @Schema(description = "结束地点经度")
	@TableField("end_point_longitude")
	private BigDecimal endPointLongitude;

    @Schema(description = "结束地点纬度")
	@TableField("end_point_latitude")
	private BigDecimal endPointLatitude;

    @Schema(description = "预估里程")
	@TableField("expect_distance")
	private BigDecimal expectDistance;

    @Schema(description = "实际里程")
	@TableField("real_distance")
	private BigDecimal realDistance;

    @Schema(description = "预估任务金额")
	@TableField("expect_amount")
	private BigDecimal expectAmount;

    @Schema(description = "实际任务金额")
	@TableField("real_amount")
	private BigDecimal realAmount;

    @Schema(description = "补贴费")
	@TableField("favour_fee")
	private BigDecimal favourFee;

    @Schema(description = "无人机操作员ID")
	@TableField("pilot_id")
	private Long pilotId;

    @Schema(description = "操作员接任务时间")
	@TableField("accept_time")
	private Date acceptTime;

    @Schema(description = "操作员到达时间")
	@TableField("arrive_time")
	private Date arriveTime;

    @Schema(description = "开始执行任务时间")
	@TableField("start_service_time")
	private Date startServiceTime;

    @Schema(description = "结束执行任务时间")
	@TableField("end_service_time")
	private Date endServiceTime;

    @Schema(description = "支付时间")
	@TableField("pay_time")
	private Date payTime;

    @Schema(description = "任务取消规则ID")
	@TableField("cancel_rule_id")
	private Long cancelRuleId;

    @Schema(description = "无人机编号")
	@TableField("car_license")
	private String carLicense;

    @Schema(description = "无人机型号")
	@TableField("car_type")
	private String carType;

	@Schema(description = "起点拍照：无人机前视图")
	@TableField("car_front_url")
	private String carFrontUrl;

	@Schema(description = "终点拍照：无人机后视图")
	@TableField("car_back_url")
	private String carBackUrl;

	@Schema(description = "支付订单号")
	@TableField("transaction_id")
	private String transactionId;

    @Schema(description = "任务状态：1等待接任务，2已接任务，3操作员已到达，4开始执行，5结束执行，6未付款，7已付款，8任务已结束，9企业撤单，10操作员撤单，11事故关闭，12其他")
	@TableField("status")
	private Integer status;

    @Schema(description = "任务备注信息")
	@TableField("remark")
	private String remark;

}

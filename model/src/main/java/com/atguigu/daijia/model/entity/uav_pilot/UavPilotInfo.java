package com.atguigu.daijia.model.entity.uav_pilot;

import com.atguigu.daijia.model.entity.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Schema(description = "UavPilotInfo")
@TableName("uav_pilot_info")
public class UavPilotInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @Schema(description = "微信openId")
	@TableField("wx_open_id")
	private String wxOpenId;

    @Schema(description = "昵称")
	@TableField("nickname")
	private String nickname;

    @Schema(description = "头像")
	@TableField("avatar_url")
	private String avatarUrl;

    @Schema(description = "电话")
	@TableField("phone")
	private String phone;

    @Schema(description = "姓名")
	@TableField("name")
	private String name;

    @Schema(description = "性别")
	@TableField("gender")
	private String gender;

    @Schema(description = "生日")
	@TableField("birthday")
	private Date birthday;

    @Schema(description = "身份证号码")
	@TableField("idcard_no")
	private String idcardNo;

    @Schema(description = "身份证地址")
	@TableField("idcard_address")
	private String idcardAddress;

    @Schema(description = "身份证有效期")
	@TableField("idcard_expire")
	private Date idcardExpire;

    @Schema(description = "身份证正面")
	@TableField("idcard_front_url")
	private String idcardFrontUrl;

    @Schema(description = "身份证背面")
	@TableField("idcard_back_url")
	private String idcardBackUrl;

    @Schema(description = "手持身份证")
	@TableField("idcard_hand_url")
	private String idcardHandUrl;

    @Schema(description = "准驾车型")
	@TableField("pilot_license_class")
	private String pilotLicenseClazz;

	@Schema(description = "飞行执照证件号")
	@TableField("pilot_license_no")
	private String pilotLicenseNo;

    @Schema(description = "飞行执照有效期")
	@TableField("pilot_license_expire")
	private Date pilotLicenseExpire;

    @Schema(description = "飞行执照初次领证日期")
	@TableField("pilot_license_issue_date")
	private Date pilotLicenseIssueDate;

    @Schema(description = "飞行执照正面")
	@TableField("pilot_license_front_url")
	private String pilotLicenseFrontUrl;

    @Schema(description = "飞行执照背面")
	@TableField("pilot_license_back_url")
	private String pilotLicenseBackUrl;

    @Schema(description = "手持飞行执照")
	@TableField("pilot_license_hand_url")
	private String pilotLicenseHandUrl;

    @Schema(description = "紧急联系人")
	@TableField("contact_name")
	private String contactName;

    @Schema(description = "紧急联系人电话")
	@TableField("contact_phone")
	private String contactPhone;

    @Schema(description = "紧急联系人关系")
	@TableField("contact_relationship")
	private String contactRelationship;

    @Schema(description = "腾讯云人脸模型id")
	@TableField("face_model_id")
	private String faceModelId;

	@Schema(description = "无人机操作员工号")
	@TableField("job_no")
	private String jobNo;

	@Schema(description = "飞行任务量统计")
	@TableField("order_count")
	private Integer orderCount;

	@Schema(description = "评分")
	@TableField("score")
	private BigDecimal score;

    @Schema(description = "认证状态")
	@TableField("auth_status")
	private Integer authStatus;

    @Schema(description = "状态，1正常，2禁用")
	@TableField("status")
	private Integer status;

}

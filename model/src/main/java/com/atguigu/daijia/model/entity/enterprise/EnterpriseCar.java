package com.atguigu.daijia.model.entity.enterprise;

import com.atguigu.daijia.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "EnterpriseCar")
public class EnterpriseCar extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Schema(description = "企业ID")
	private Long enterpriseId;

	@Schema(description = "无人机编号")
	private String license;

	@Schema(description = "无人机型号")
	private String brand;

}

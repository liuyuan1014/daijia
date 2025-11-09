package com.atguigu.daijia.coupon.controller;

import com.atguigu.daijia.common.login.GuiguLogin;
import com.atguigu.daijia.common.result.Result;
import com.atguigu.daijia.coupon.service.CouponService;
import com.atguigu.daijia.model.vo.base.PageVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "优惠券API接口管理")
@RestController
@RequestMapping("/coupon")
@SuppressWarnings({"unchecked", "rawtypes"})
public class CouponController {

    @Autowired
    private CouponService couponService;

    @Operation(summary = "获取优惠券分页列表")
    @GuiguLogin
    @GetMapping("/findCouponPage/{page}/{limit}")
    public Result<PageVo> findCouponPage(
            @Parameter(name = "page", description = "当前页码", required = true)
            @PathVariable Long page,

            @Parameter(name = "limit", description = "每页记录数", required = true)
            @PathVariable Long limit) {
        PageVo pageVo = couponService.findCouponPage(page, limit);
        return Result.ok(pageVo);
    }

}

package com.atguigu.daijia.coupon.service;

import com.atguigu.daijia.model.vo.base.PageVo;

public interface CouponService {

    //分页获取优惠券
    PageVo findCouponPage(Long page, Long limit);

}

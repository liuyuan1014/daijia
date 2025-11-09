package com.atguigu.daijia.enterprise.service;

import com.atguigu.daijia.model.form.enterprise.UpdateWxPhoneForm;
import com.atguigu.daijia.model.vo.enterprise.EnterpriseLoginVo;

public interface EnterpriseService {

    //微信登录
    String login(String code);

    //获取用户信息
    EnterpriseLoginVo getEnterpriseLoginInfo(String token);

    //获取用户信息
    EnterpriseLoginVo getEnterpriseInfo(Long enterpriseId);

    //更新用户微信手机号
    Boolean updateWxPhoneNumber(UpdateWxPhoneForm updateWxPhoneForm);
}

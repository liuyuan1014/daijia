package com.atguigu.daijia.enterprise.service.impl;

import com.atguigu.daijia.enterprise.service.EnterpriseService;
import com.atguigu.daijia.model.form.enterprise.UpdateWxPhoneForm;
import com.atguigu.daijia.model.vo.enterprise.EnterpriseLoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EnterpriseServiceImpl implements EnterpriseService {

    @Override
    public String login(String code) {
        return null;
    }

    @Override
    public EnterpriseLoginVo getEnterpriseLoginInfo(String token) {
        return null;
    }

    @Override
    public EnterpriseLoginVo getEnterpriseInfo(Long enterpriseId) {
        return null;
    }

    @Override
    public Boolean updateWxPhoneNumber(UpdateWxPhoneForm updateWxPhoneForm) {
        return null;
    }
}

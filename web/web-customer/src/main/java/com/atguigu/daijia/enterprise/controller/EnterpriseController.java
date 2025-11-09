package com.atguigu.daijia.enterprise.controller;

import com.atguigu.daijia.common.constant.RedisConstant;
import com.atguigu.daijia.common.execption.GuiguException;
import com.atguigu.daijia.common.login.GuiguLogin;
import com.atguigu.daijia.common.result.Result;
import com.atguigu.daijia.common.result.ResultCodeEnum;
import com.atguigu.daijia.common.util.AuthContextHolder;
import com.atguigu.daijia.enterprise.client.EnterpriseInfoFeignClient;
import com.atguigu.daijia.enterprise.service.EnterpriseService;
import com.atguigu.daijia.model.form.enterprise.UpdateWxPhoneForm;
import com.atguigu.daijia.model.vo.enterprise.EnterpriseLoginVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "企业API接口管理")
@RestController
@RequestMapping("/enterprise")
@SuppressWarnings({"unchecked", "rawtypes"})
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseInfoService;

    @Operation(summary = "获取企业登录信息")
    @GuiguLogin//自定义注解，需要登录校验的方法上面添加
    @GetMapping("/getEnterpriseLoginInfo")
    public Result<EnterpriseLoginVo> getEnterpriseLoginInfo() {

        //1 从ThreadLocal获取用户id
        Long enterpriseId = AuthContextHolder.getUserId();

        //调用service
        EnterpriseLoginVo enterpriseLoginVo = enterpriseInfoService.getEnterpriseInfo(enterpriseId);

        return Result.ok(enterpriseLoginVo);
    }

//    @Operation(summary = "获取企业登录信息")
//    @GetMapping("/getEnterpriseLoginInfo")
//    public Result<EnterpriseLoginVo>
//                    getEnterpriseLoginInfo(@RequestHeader(value = "token") String token) {
//
//        //1 从请求头获取token字符串
////        HttpServletRequest request
////        String token = request.getHeader("token");
//
//        //调用service
//        EnterpriseLoginVo enterpriseLoginVo = enterpriseInfoService.getEnterpriseLoginInfo(token);
//
//        return Result.ok(enterpriseLoginVo);
//    }

    @Operation(summary = "小程序授权登录")
    @GetMapping("/login/{code}")
    public Result<String> wxLogin(@PathVariable String code) {
        return Result.ok(enterpriseInfoService.login(code));
    }

    @Operation(summary = "更新用户微信手机号")
    @GuiguLogin
    @PostMapping("/updateWxPhone")
    public Result updateWxPhone(@RequestBody UpdateWxPhoneForm updateWxPhoneForm) {
        updateWxPhoneForm.setEnterpriseId(AuthContextHolder.getUserId());
        return Result.ok(enterpriseInfoService.updateWxPhoneNumber(updateWxPhoneForm));
    }
}

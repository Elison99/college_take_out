package org.example.college.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.qcloudsms.httpclient.HTTPException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.example.college.common.Result;
import org.example.college.common.SMSUtils;
import org.example.college.common.ValidateCodeUtils;
import org.example.college.entity.User;
import org.example.college.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     *发送短信验证码
     * @param user
     * @param httpSession
     * @return
     * @throws HTTPException
     * @throws IOException
     */
    @PostMapping("/sendMsg")
    public Result<String> sendMsg(@RequestBody User user, HttpSession httpSession) throws HTTPException, IOException {
        //获取手机号
        String phone = user.getPhone();
        if (StringUtils.isNotEmpty(phone)){
            //生成随机的4位验证码
            Integer code = ValidateCodeUtils.generateValidateCode(4);
            log.info("code={}",code);

            //调用腾讯云的SMS服务发送短信
//            SMSUtils.sendSMS(phone,SMSUtils.VALIDATE_CODE,String.valueOf(code));

            //需要将生成的验证码保存到session
            httpSession.setAttribute(phone,code);
            return Result.success("手机验证码短信发送成功");
        }
        return Result.error("手机验证码短信发送成功");
    }

    @PostMapping("/login")
    public Result<User> login(@RequestBody Map map,HttpSession httpSession){
        log.info(map.toString());

        //获取手机验证码
        String phone = map.get("phone").toString();
        String code = map.get("code").toString();
        //从session中获取保存的验证码
        Object codeInSession = String.valueOf(httpSession.getAttribute(phone));

        //进行验证码比较
        if (codeInSession != null &&codeInSession.equals(code)){
            //比对成功

            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone,phone);

            User user = userService.getOne(queryWrapper);
            if (user==null){
                //改用户为新用户
                user = new User();
                user.setPhone(phone);
                user.setStatus(1);
                userService.save(user);
            }

            httpSession.setAttribute("user",user.getId());
            return Result.success(user);
        }

        return Result.error("登录失败");
    }

}

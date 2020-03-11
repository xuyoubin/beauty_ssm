package com.muma.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.muma.common.Session;
import com.muma.controller.base.BaseResult;
import com.muma.dto.UserInfoDto;
import com.muma.enums.base.ResultEnum;
import com.muma.exception.BizException;
import com.muma.util.Authenticate;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import static com.muma.common.HttpContext.getResponse;

import java.io.PrintWriter;
import java.util.List;

/**
 * @author xuyb
 *
 * 采用AOP的方式处理权限。
 */
@Component
@Aspect
public class BindingResultAop {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.muma.controller.*.*(..)) && !execution(* com.muma.controller.UserController.login()) && " +
            "!execution(* com.muma.controller.UserController.register())&& !execution(* com.muma.controller.UserController.checkCode())")
    public void aopMethod(){}

    @Around("aopMethod()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        UserInfoDto userInfoDto= (UserInfoDto) Session.getSessionAttribute();
        MethodSignature methodSignature = MethodSignature.class.cast(joinPoint.getSignature());
        Authenticate auth = fetchAuth(methodSignature);
        if (!handleAuth(auth, userInfoDto)) {
            logger.info("您没有权限进行该操作，方法名：{}，用户手机号：{}", methodSignature, userInfoDto.getRegPhone());
            getResponse().setCharacterEncoding("UTF-8");
            getResponse().setContentType("application/json;charset=UTF-8");
            PrintWriter pw = getResponse().getWriter();
            pw.write(JSONObject.toJSONString(new BaseResult(false,"权限不足!")));
            pw.flush();
            pw.close();
            return null;
        }
        return joinPoint.proceed();
    }

    private Authenticate fetchAuth(MethodSignature methodSignature) {
        return methodSignature.getMethod().getAnnotation(Authenticate.class);
    }

    private Boolean handleAuth(Authenticate auth, UserInfoDto info) {
        if(auth == null){
            return false;
        }
        String[] permissionsStr = auth.permissions().split(",");
        List<String> permissions = Lists.newArrayList(permissionsStr);
        String permission = info.getRoalId().getValue().toString();
        if (permissions.contains(permission)) {
            return true;
        }
        return false;
    }
}

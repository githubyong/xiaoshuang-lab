package com.ss.lab.sprdemo.aop;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.stereotype.Component;


/**
 * aop的一种用法，用来拦截service层中的方法，在方法开始前和结束时打印日志，并且记录方法耗时情况<br>
 * 如果采用硬编码(不使用aop)的话时，这些日志代码需要加到每一个需要记录日志的方法体里面
 * <p>
 * Created by yong on 2018/2/23.
 */
@Aspect
@Component
public class LogHelper {
    private static final Logger logger = LogManager.getLogger(LogHelper.class);


    //定义切点
    @Pointcut("execution(* com.ss.lab.sprdemo.service.*.*(..))")
    public void servicePoint() {
    }

    @Around("servicePoint()")
    public Object logAround(ProceedingJoinPoint joinPoint) {
        MethodInvocationProceedingJoinPoint mpj = (MethodInvocationProceedingJoinPoint) joinPoint;
        Signature signature = mpj.getSignature();
        String content = signature.toString();
        long t0 = System.currentTimeMillis();
        logger.debug(content + " start -------->>");
        Object obj = null;
        try {
            obj = joinPoint.proceed();//执行 service 中的方法
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        long t1 = System.currentTimeMillis();
        logger.debug(String.format(content + " end <<-------- take time=%s ms", (t1 - t0)));
        return obj;
    }
}

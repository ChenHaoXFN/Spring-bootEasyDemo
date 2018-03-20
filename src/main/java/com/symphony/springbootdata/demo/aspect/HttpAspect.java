package com.symphony.springbootdata.demo.aspect;

import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * AOP.
 *
 * @User: ch
 * @Date: 2017-11-27
 * @Time: 上午9:42
 */
@Aspect //aop标签
@Component //引入到spirng容器
public class HttpAspect {

  //使用logger记录打印日志.
  private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

  //将重复的代码提取出来.使用注解Pointcut.
  @Pointcut("execution(public * com.symphony.springbootdata.demo.controller.GirlController.*(..))")
  public void log() {
  }

  /**
   * 方法前执行.
   */
  @Before("log()")
  public void before(JoinPoint joinPoint) {
    //
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();
    //url
    logger.info("url={}", request.getRequestURL());
    //method
    logger.info("method={}",request.getMethod());
    //ip
    logger.info("ip={}", request.getRemoteAddr());
    //类方法
    logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
    //参数
    logger.info("args={}", joinPoint.getArgs());
  }

  /**
   * 方法后执行.
   */
  @After("log()")
  public void after() {
    logger.info("2222222222");
  }

  /**
   * 返回参数.
   * @param object
   */
  @AfterReturning(returning = "object", pointcut = "log()")
  public void afterReturn(Object object){
    logger.info("response={}", object.toString());

  }

}

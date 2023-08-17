package com.bitc.common.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class AspectAdvice {

	@Around("execution(* com.bitc.*.controller.*.*(..))")
	public Object checkControllerObj(ProceedingJoinPoint pjp) throws Throwable {
		log.info("-------- Advice CheckController START");
		log.info("target : {}" , pjp.getTarget());
		log.info("args : " + Arrays.toString(pjp.getArgs()));
		Object o = pjp.proceed();
		log.info("returns : " + o);
		log.info("-------- Advice CheckController END");
		return o;
	}
	
	@Around("execution(* com.bitc.*.service.*.*(..))")
	public Object checkServiceObject(ProceedingJoinPoint pjp) throws Throwable{
		log.info("-------- Advice CheckServiceObject START");
		log.info("target : {}" , pjp.getTarget());
		log.info("args : " + Arrays.toString(pjp.getArgs()));
		Object o = pjp.proceed();
		log.info("returns : " + o);
		log.info("-------- Advice CheckServiceObject END");
		return o;
	}
	
}

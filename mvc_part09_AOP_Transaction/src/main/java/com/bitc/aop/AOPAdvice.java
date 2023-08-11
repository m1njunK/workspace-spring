package com.bitc.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class AOPAdvice {
	
	public AOPAdvice() {
		System.out.println("AOP Advice 생성");
	}
	// target joinPoint(method) 가 실행되기 전 호출
	@Before("execution(* com.bitc.service.*.*(..))")
	public void startLog(JoinPoint jp) {
		log.info("---------------------------------");
		log.info("---------------------------------");
		log.info("---------- START LOG ------------");
		log.info("target : " + jp.getTarget());
		log.info("type : " + jp.getKind());
		log.info("parameters : " + Arrays.toString(jp.getArgs()));
		log.info("name : " + jp.getSignature().getName());
		log.info("---------- START LOG END --------");
		log.info("---------------------------------");
		log.info("---------------------------------");
		
	}
	
}









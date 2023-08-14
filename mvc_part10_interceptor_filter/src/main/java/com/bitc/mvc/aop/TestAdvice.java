package com.bitc.mvc.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAdvice {
	
	
	@Around("execution(* com.bitc.mvc.controller.HomeController.*(..))")
	public Object controllerLog(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("-----------------------------------------");
		System.out.println("----------Around Controller START-------------");
		System.out.println("target : " + pjp.getTarget());
		System.out.println("method : " + pjp.getSignature().getName());
		System.out.println("args : " + Arrays.toString(pjp.getArgs()));
		Object o = pjp.proceed();
		if(o != null) {
			System.out.println("Around : " + o);
		}
		System.out.println("----------Around Controller END-------------");
		System.out.println("-----------------------------------------");
		return o;
	}
}












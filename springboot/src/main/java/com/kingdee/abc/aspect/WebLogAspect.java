package com.kingdee.abc.aspect;

import java.util.Arrays;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.kingdee.abc.advice.EncryptResponseBodyAdvice;

/**
 * @author malcolmszx
 * @date 2018/7/10
 * @desc 统一日子处理
 */
@Aspect
@Component
public class WebLogAspect {
	
	private Logger logger = LoggerFactory.getLogger(EncryptResponseBodyAdvice.class);

	@Around(value = "(@within(org.springframework.web.bind.annotation.RestController) "
			+ "|| @within(org.springframework.stereotype.Controller)) " +
	"&& (@annotation(org.springframework.web.bind.annotation.RequestMapping) "
	+ "|| @annotation(org.springframework.web.bind.annotation.GetMapping)) "
	+ "|| @annotation(org.springframework.web.bind.annotation.PostMapping) "
	+ "|| @annotation(org.springframework.web.bind.annotation.PutMapping) "
	+ "|| @annotation(org.springframework.web.bind.annotation.DeleteMapping) "
	+ "|| @annotation(org.springframework.web.bind.annotation.PatchMapping)")
	public Object interceptor(ProceedingJoinPoint pjp) throws Throwable {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
		HttpServletRequest request = servletRequestAttributes.getRequest();
		
		String url = request.getRequestURI();
		Object[] objects = pjp.getArgs();
		Optional<Object> optional = Arrays.stream(objects).filter(item -> item instanceof HttpServletRequest
				|| item instanceof HttpServletResponse).findAny();
		if (!optional.isPresent()) {
			logger.info("request-> url: {} params: {}", url, JSON.toJSONString(objects));
		}
		Object result = pjp.proceed();
		Optional.ofNullable(result).ifPresent(r -> logger.info("response-> {} result: {}", url, JSON.toJSONString(r)));
		return result;
	}
	

}

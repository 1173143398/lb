package com.execpt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class WxHandlerExceptionResolver implements HandlerExceptionResolver {

	private static Log log = LogFactory.getLog(WxHandlerExceptionResolver.class);
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		log.error("统一处理异常",ex);
		ExceptionMessage em = new ExceptionMessage();
		if(ex instanceof WxException){
			WxException exe = (WxException)ex;
			em.setErrorCode(exe.getErrorCode());
		}
		em.setErrorMsg(ex.getMessage());
		ModelAndView mv = new ModelAndView();
		mv.addObject(em);
		return mv;
	}

}

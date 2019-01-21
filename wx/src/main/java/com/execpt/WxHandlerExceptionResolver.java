package com.execpt;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;

public class WxHandlerExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		ExceptionMessage em = new ExceptionMessage();
		if(ex instanceof WxException){
			WxException exe = (WxException)ex;
			em.setErrorCode(exe.getErrorCode());
		}
		em.setErrorMsg(ex.getMessage());
		
		FastJsonJsonView view = new FastJsonJsonView();
		Map<String,String> rs = new HashMap<String,String>();
		rs.put("ERROR_CODE", em.getErrorCode());
		rs.put("ERROR_MSG", em.getErrorMsg());
		view.setAttributesMap(rs);
		ModelAndView mv = new ModelAndView();
		mv.setView(view);
		return mv;
	}

}

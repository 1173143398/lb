package com.wechat;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

/**
 * 上传素材servlet
 */
//@WebServlet(urlPatterns = "/adminServlet",loadOnStartup = 1)
public class AdminServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		super.init();
		//System.out.println("==========获取AccessToken===========");
		//WxApiUtil.getNetAccessToken();
	}
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String option = request.getParameter("option");
    	String rs = "option invalid";
    	if("menu".equals(option)){
    		InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("menu.json");
    		BufferedReader br = new BufferedReader(new InputStreamReader(resourceAsStream));
    		String line = null;
    		StringBuilder sb = new StringBuilder();
    		while((line = br.readLine()) != null){
    			sb.append(line);
    		}
    		br.close();
    		resourceAsStream.close();
    		rs = WxApiUtil.createMenu(sb.toString());
    	}else if("media".equals(option)){
    		String url = request.getParameter("url");
    		if(url == null || "".equals(url)){
    			response.getWriter().write("URL不能为空");
    			return;
    		}
    		String type = request.getParameter("type");
    		if(type == null || "".equals(type)){
    			response.getWriter().write("type不能为空");
    			return;
    		}
    		String filePath = url;
    		File file = new File(filePath);
    		JSONObject jsonObject = WxApiUtil.uploadMedia(file,type);
    		System.out.println(jsonObject.toString());
    		rs = jsonObject.toString();
    	}else if("token".equals(option)){
    		rs = WxApiUtil.getNetAccessToken();
    	}
    	response.getWriter().write(rs);
    }
}

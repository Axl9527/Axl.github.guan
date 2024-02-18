package com.servlet;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.easy.util.Sys;

import java.io.IOException;

@WebServlet("/vercode/*")
public class VercodeServlet extends FenxiServlet {
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	super.service(req, resp);
    	Object action=req.getAttribute(Sys.SYS_ACTION);
    	if("creat".equals(action)) {
    		//定义图形验证码的长和宽
            LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(100,38);
            //生成随机验证码
            String baseStr = "0123456789";
            RandomGenerator randomGenerator = new RandomGenerator(baseStr,4);
            lineCaptcha.setGenerator(randomGenerator);
            //得到验证码
            String code = lineCaptcha.getCode();
           
            req.getSession().setAttribute("code",code);
            String usercode=req.getParameter("vercode");
            System.out.println("code1" + code);
            System.out.println("code2" + usercode);
             //返回
            lineCaptcha.write(resp.getOutputStream());
            //关闭流
            resp.getOutputStream().close();
    	}else if("check".equals(action)){
    		
            Object code = req.getSession().getAttribute("code");
            String usercode=req.getParameter("vercode");
            System.out.println("code1" + code);
            System.out.println("code2" + usercode);

            if(code.equals(usercode)) {
            	resp.getWriter().write("success");
            }else {
            	resp.getWriter().write("fail");
            }

    	}
    	
    	
    }

  
}

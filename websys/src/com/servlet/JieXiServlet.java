package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/jx/*")
public class JieXiServlet extends HttpServlet {
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri=	req.getRequestURI();
		System.out.println(uri);
		//�����ֵ�λ��/jsp/* //public int lastIndexOf(String str): ����ָ�����ַ����ڴ��ַ��������ұ߳��ִ�������,��ȡ��URI��������"/"��λ�õ�������
		int index=uri.lastIndexOf("/");
		System.out.println(index);
		//�ļ���,����URI�л�ȡ�����һ��"/"��ʼ�Ĳ���,��Ϊ�ļ���
		String pagename=uri.substring(index+1);
		System.out.println(pagename);
		//ת��·��,����һ���µ�URL����ָ����"../WEB-INF/page/"Ŀ¼�µ��Ի�ȡ���ļ�����ҳ����Ϊ���Ƶ�JSP�ļ���
		String url="../WEB-INF/page/"+pagename+".jsp";
		System.out.println(url);
		//ת��
		req.getRequestDispatcher(url).forward(req, resp);
		}
}

package kr.co.fastcampus.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//-> web.xml 처럼 xml로 설정하는 서블릿 설정을 3.0 이상부터는 어노테이션형식으로도 작성가능하다.
@WebServlet(
		name = "simple",
		urlPatterns = "/simple"
) 
public class SimpleServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();
		writer.println("Hello World");
		
	}
	
}

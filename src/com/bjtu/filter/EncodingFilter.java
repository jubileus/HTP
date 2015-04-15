package com.bjtu.filter;
import java.io.*;
import javax.servlet.*;

public class EncodingFilter implements Filter{
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
	FilterChain chain) throws IOException, ServletException {
	try {
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html;charset=UTF-8");
	} catch (Exception e) {
	}

	chain.doFilter(request, response);
	}

	public void destroy() {

	}
}

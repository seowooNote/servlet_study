package com.study.array.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class EncodingFilter
 */
@WebFilter("/*")
public class EncodingFilter extends HttpFilter implements Filter {

	public void destroy() {

	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 전처리 영역
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		httpServletResponse.setHeader("Access-Control-Allow-Headers", "Content-Type");
		httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
		// httpServletResponse.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500/array_study/*");
		// 인코딩 request, response 둘다 UTF-8 설정 (전처리 영역에서 설정)
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		chain.doFilter(request, response);
		
		// 후처리 영역
	}


	public void init(FilterConfig fConfig) throws ServletException {

	}

}

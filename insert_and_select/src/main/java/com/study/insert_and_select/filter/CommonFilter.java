package com.study.insert_and_select.filter;

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


@WebFilter("/*")
public class CommonFilter extends HttpFilter implements Filter {
       

    public CommonFilter() {
        super();
    }


	public void destroy() {
		
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 전처리 영역
		// CORS 세팅
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		httpServletResponse.setHeader("Access-Control-Allow-Headers", "Content-Type");
		httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
		// 인코딩 세팅
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		chain.doFilter(request, response);
		// 후처리 영역
		
	}


	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}

package com.study.servlet_study.filter;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;


@WebFilter("/*")
public class RequestCharactorEncodingFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;


	public RequestCharactorEncodingFilter() {
        super();
        
    }


	public void destroy() {
		
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 전처리 영역(chain.doFilter(request, response); 기준으로 위쪽 영역)
		HttpServletRequest httpRequest = (HttpServletRequest) request; // 다운캐스팅
		// httpRequest.setCharacterEncoding("UTF-8");
		
		String[] methods = new String[] {"POST", "PUT"};
		
		// System.out.println(httpRequest.getMethod());
	
		if(Arrays.asList(methods).contains(httpRequest.getMethod().toUpperCase())) {
			httpRequest.setCharacterEncoding("UTF-8");
		}
		
		chain.doFilter(request, response);
		// 후처리 영역(chain.doFilter(request, response); 기준으로 아래쪽 영역, 제일 마지막에 실행됨)
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}

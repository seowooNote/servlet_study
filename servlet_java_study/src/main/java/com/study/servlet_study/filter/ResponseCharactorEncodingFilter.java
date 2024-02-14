package com.study.servlet_study.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 전처리 과정 (filter)
@WebFilter("/*")
public class ResponseCharactorEncodingFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;


	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request; // 다운캐스팅(실제로는 filter에서 request 설정 할 필요 없음)
		HttpServletResponse httpResponse = (HttpServletResponse) response; // 다운캐스팅
		
		httpResponse.setCharacterEncoding("UTF-8");
		
		// 전처리 영역(chain.doFilter(request, response); 기준으로 위쪽 영역)
		
		
		chain.doFilter(request, response); // 최종 filter 처리후 servlet 호출(doGet()으로 감 / @WebServlet)
		
		// System.out.println(response.getCharacterEncoding());
		// 후처리 영역(chain.doFilter(request, response); 기준으로 아래쪽 영역, 제일 마지막에 실행됨)
		// httpServletResponse.getWriter().println("무조건 후처리함");
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}

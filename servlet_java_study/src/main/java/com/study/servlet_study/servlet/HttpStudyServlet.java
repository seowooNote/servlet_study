package com.study.servlet_study.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.servlet_study.utils.ParamsConverter;


@WebServlet("/http")
public class HttpStudyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HttpStudyServlet() {
        super();
    }

    // HTTP 메소드 (CRUD)
    // POST 요청 	-> C Create(추가)
    // GET 요청 	-> R Read(조회)
    // PUT 요청 	-> U Update(수정)
    // DELETE 요청 	-> D Delete(삭제)
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// request.setCharacterEncoding("UTF-8"); // 인코딩 꼭 .setCharacterEncoding("UTF-8"); 로 설정해야 한글깨짐이 없음 -> filter로 빼주자
    	
//    	Map<String, String[]> map = request.getParameterMap();
    	Map<String, String> paramsMap = new HashMap<>();
    	
    	request.getParameterMap().forEach((k, v) -> {
    		// StringBuilder 문자열 관련 작업을 할때 // builder.toString() : 문자 각각 하나하나를 합쳐서 문자열로 반환
    		StringBuilder builder = new StringBuilder();
    		Arrays.asList(v).forEach(value -> builder.append(value));
//    		System.out.println(k + ": " + builder.toString());
//    		for(String value : v) {
//    			System.out.print(value);
//    		}
//    		System.out.println();
    		
    		paramsMap.put(k, builder.toString());
    	});
    	
    	System.out.println(paramsMap);
    	
//    	String nameParams = request.getParameter("name");
//    	String phoneParams = request.getParameter("phone");
//    	String emailParams = request.getParameter("email");
//    	String addressParams = request.getParameter("address");
//    	
//    	System.out.println(nameParams);
//    	System.out.println(phoneParams);
//    	System.out.println(emailParams);
//    	System.out.println(addressParams);
    	
    	
    	Map<String, String> paramsMap2 = new HashMap<>();
    	// Iterator 사용 (반복 작업)
    	Iterator<String> ir = request.getParameterNames().asIterator(); // getParameterNames() : key 값 몰라도 쓰는 방법(key 값 자동 찾기)
    	while(ir.hasNext()) {
    		String key = ir.next();
    		paramsMap2.put(key, request.getParameter(key));
//    		System.out.println(ir.next());
    	}
    	
	}
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    	Map<String, String> paramsMap = new HashMap<>();
//    	
//    	request.getParameterMap().forEach((k, v) -> {
//    		StringBuilder builder = new StringBuilder();
//    		Arrays.asList(v).forEach(value -> builder.append(value));
//    		paramsMap.put(k, builder.toString());
//    	});
//    	
//    	System.out.println(paramsMap);
    	
    	// 위의 코드를 따로 빼서 static 으로 변환해서 사용하기 ParamsConverter.java 파일 참고 (static 은 공유 가능)
    	Map<String, String> paramsMap = ParamsConverter.convertParamsMapToMap(request.getParameterMap());
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


}

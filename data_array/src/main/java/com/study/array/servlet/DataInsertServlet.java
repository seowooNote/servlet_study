package com.study.array.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.study.array.entity.DataObj;
import com.study.array.service.DataService;


@WebServlet("/data/addition")
public class DataInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataService dataService;
	
	public DataInsertServlet() {
		dataService = DataService.getInstance();
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 인코딩 세팅(UTF-8 한글) 설정안해주면 한글 문자 깨져서 출력됨 => 필터에 걸어서 전역으로 UTF-8 인코딩화 !!!
		// request.setCharacterEncoding("UTF-8");
		StringBuilder json = new StringBuilder();
		String requestData = null;
		
		//  JSON 데이터 예시
		/*
		 * "{
		 * 	"id" : 1,
		 * 	"content" : "내용"
		 * }"
		 */
		
		// JSON 은 문자열형 데이터라 스트림으로 받아야함(문자열 -> 스트림) / JSON 파일을 받아서 조작
		BufferedReader reader = request.getReader();
		// .lines() 문자열을 하나하나 잘라서 들어온 것을 가지고 하나하나 쌓아버림(해당 JSON 파일의 문자열 / 줄바꿈을 기준으로 데이터를 저장함 -> 한줄단위로 나눠서 들어옴)
		while((requestData = reader.readLine()) != null) {
			json.append(requestData); // .append() 해당 데이터를 쌓아줌(추가)
		}
		
		// System.out.println(json.toString());
		
		
		Gson gson = new Gson();
		DataObj dataObj = gson.fromJson(json.toString(), DataObj.class); // JSON을 JAVA의 객체(Object)로 바꿈 // JavaScript 에서는 JSON.parse()
		
		// System.out.println(dataObj); // JSON -> JAVA Object / JAVA 객체(Object) 형태
		// System.out.println(gson.toJson(dataObj)); //  JAVA Object -> JSON / JSON 문자열 테이터 형태
		
		int responseBody = dataService.addData(dataObj);
		
		Map<String, Object> responseMap = new HashMap();
		responseMap.put("data", responseBody);
		responseMap.put("responseMessage", "데이터 추가 완료");
		
		response.setContentType("application/json");
		response.setStatus(201);
		response.getWriter().println(gson.toJson(responseMap));
		
	}

}

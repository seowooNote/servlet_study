package com.study.insert_and_select.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.study.insert_and_select.dao.StudentDao;
import com.study.insert_and_select.entity.Student;


@WebServlet("/data/addition")
public class DataInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DataInsertServlet() {
        super();
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder builder = new StringBuilder(); // 문자열을 합쳐주는 용도
		String readData = null;
		
		// String builder2 = "";
		
		BufferedReader reader = request.getReader();
		
		while((readData = reader.readLine()) != null) {
			builder.append(readData); // StringBuilder은 기존 메모리 공간이 할당되어 그 해당하는 공간에 새로운 문자열을 추가하기 때문에 메모리를 계속해서 할당하지 않아도 됨 => 효율적
			// builder2 += readData; // String의 경우 기존 메모리 공간에 새로운 문자열이 합쳐지는 것이 아니라, 새로운 문자열 메모리 공간을 계속해서 할당하기 때문에 효율적이지 않음
		}
		
		// System.out.println(builder.toString());
		
		// Student student = Student.builder()
		//		.name("홍길동")
		//		.age(20)
		//		.build();
		
		// Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		// String studentJson = gson.toJson(student);
		// System.out.println(studentJson);
		
		Gson gson = new Gson();
		
		// 1) JSON -> Map
		// Map<String, Object> map = gson.fromJson(builder.toString(), Map.class); // gson.fromJson(builder.toString(), Map.class) 이 Map<String, Object> 형태로 알아서 자동 저장됨
		// System.out.println(map);
		// System.out.println(map.get("name"));
		// System.out.println(map.get("age"));
		
		// 2) JSON -> Object(Entity 객체) / 2번 방법을 추천
		Student student = gson.fromJson(builder.toString(), Student.class);
		System.out.println(student);
		System.out.println(student.getName());
		System.out.println(student.getAge());
		
		StudentDao studentDao = StudentDao.getInstance();
		
		Student findStudent = studentDao.findStudentbyName(student.getName());
		
		if(findStudent != null) {
			Map<String, Object> errorMap = new HashMap<>();
			errorMap.put("errorMessage", "이미 등록된 이름입니다.");
			response.setStatus(400);
			response.setContentType("application/json");
			response.getWriter().println(gson.toJson(errorMap));
			return;
		}
		
		int successCount = studentDao.saveStudent(student);
		
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("status", 201);
		responseMap.put("data", "응답데이터");
		responseMap.put("successCount", successCount);
		
		// 응답을 JSON으로 인지하게 해야됨 -> 그래야 프론트에서 JSON을 인지
		response.setStatus(201);
		response.setContentType("application/json");
		
		PrintWriter writer = response.getWriter();
		writer.println(gson.toJson(responseMap));
		
	}

}

package com.study.insert_and_select.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
		Map<String, Object> map = gson.fromJson(builder.toString(), Map.class); // gson.fromJson(builder.toString(), Map.class) 이 Map<String, Object> 형태로 알아서 자동 저장됨
		System.out.println(map);
		System.out.println(map.get("name"));
		System.out.println(map.get("age"));
		
		// 2) JSON -> Object(Entity 객체) / 2번 방법을 추천
		Student student = gson.fromJson(builder.toString(), Student.class);
		System.out.println(student);
		System.out.println(student.getName());
		System.out.println(student.getAge());
		
		// DBConnectionMgr.java 파일 없이 데이터베이스 연결하는 방법
		Connection con = null; // 데이터베이스 연결
		String sql = null; // SQL 쿼리문 작성
		PreparedStatement pstmt = null; // 작성한 SQL 쿼리문 입력(DML)
		int successCount = 0; // SQL insert, update, delete 에 따른 실행 결과(성공 횟수)
		
		try {	
			// Driver.class 연결
			Class.forName("com.mysql.cj.jdbc.Driver"); // 데이터베이스 커넥터 드라이브 클래스 이름 forName() 의 매개변수 안에 문자열 (Libraries -> Maven Dependncies -> mysql-connector-j-8.0.33.jar -> com.mysql -> cj -> jdbc -> Driver.class 파일 우클릭 -> Copy Qualified Name -> 복사 붙여넣기 -> .class 지우기
			
			String url = "jdbc:mysql://mysql-db.cin5nw0tb1i7.ap-northeast-2.rds.amazonaws.com/db_study";
			String username = "aws";
			String password = "1q2w3e4r!!";
			
			con = DriverManager.getConnection(url, username, password);
			sql = "insert into servlet_student_tb(servlet_student_name, servlet_student_age) values(?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, student.getName());
			pstmt.setInt(2, student.getAge());
			successCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// try 문에서 error 가 발생하면 con 과 pstmt는 null 이기 때문에 null.close()는 실행불가 이기 때문에 if 문으로 처리해야 됨
				if(pstmt != null) {
					pstmt.close();
				}
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("status", 201);
		responseMap.put("data", "응답데이터");
		responseMap.put("successCount", successCount);
		
		// 응답을 JSON으로 인지하게 해야됨 -> 그래야 프론트에서 JSON을 인지
		response.setContentType("application/json");
		
		PrintWriter writer = response.getWriter();
		writer.println(gson.toJson(responseMap));
		
	}

}

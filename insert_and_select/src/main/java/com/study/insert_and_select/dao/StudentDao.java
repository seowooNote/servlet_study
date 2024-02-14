package com.study.insert_and_select.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.study.insert_and_select.config.DBConfig;
import com.study.insert_and_select.entity.Student;

public class StudentDao {
	
	private static StudentDao instance;
	
	private StudentDao() {}
	
	public static StudentDao getInstance() {
		if(instance == null) {
			instance = new StudentDao();
		}
		return instance;
	}
	
	// select
	public Student findStudentbyName(String name) {
		// DBConnectionMgr.java 파일 없이 데이터베이스 연결하는 방법
		Connection con = null; // 데이터베이스 연결
		PreparedStatement pstmt = null; // 작성한 SQL 쿼리문 입력(DML)
		ResultSet rs = null; // 작성한 SQL 쿼리문 결과
		Student student = null;

		try {
			// Driver.class 연결
			Class.forName("com.mysql.cj.jdbc.Driver"); // 데이터베이스 커넥터 드라이브 클래스 이름 forName() 의 매개변수 안에 문자열 (Libraries -> Maven Dependncies -> mysql-connector-j-8.0.33.jar -> com.mysql -> cj -> jdbc -> Driver.class 파일 우클릭 -> Copy Qualified Name -> 복사 붙여넣기 -> .class 지우기	
			con = DriverManager.getConnection(DBConfig.URL, DBConfig.USERNAME, DBConfig.PASSWORD);
			String sql = "select * from servlet_student_tb where servlet_student_name = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				student = Student.builder()
						.id(rs.getInt(1))
						.name(rs.getString(2))
						.age(rs.getInt(3))
						.build();
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
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
			
		return student;
	}
	
	
	// insert
	public int saveStudent(Student student) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int successCount = 0; // SQL insert, update, delete 에 따른 실행 결과(성공 횟수)
		
		try {	
			// Driver.class 연결
			Class.forName("com.mysql.cj.jdbc.Driver"); // 데이터베이스 커넥터 드라이브 클래스 이름 forName() 의 매개변수 안에 문자열 (Libraries -> Maven Dependncies -> mysql-connector-j-8.0.33.jar -> com.mysql -> cj -> jdbc -> Driver.class 파일 우클릭 -> Copy Qualified Name -> 복사 붙여넣기 -> .class 지우기
			con = DriverManager.getConnection(DBConfig.URL, DBConfig.USERNAME, DBConfig.PASSWORD);
			String sql = "insert into servlet_student_tb(servlet_student_name, servlet_student_age) values(?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, student.getName());
			pstmt.setInt(2, student.getAge());
			successCount = pstmt.executeUpdate(); // 실행횟수
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
		
		return successCount;
	}
	
	public List<Student> getStudentListAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Student> students = new ArrayList<>();
		
		try {
			// Driver.class 연결
			Class.forName("com.mysql.cj.jdbc.Driver"); // 데이터베이스 커넥터 드라이브 클래스 이름 forName() 의 매개변수 안에 문자열 (Libraries -> Maven Dependncies -> mysql-connector-j-8.0.33.jar -> com.mysql -> cj -> jdbc -> Driver.class 파일 우클릭 -> Copy Qualified Name -> 복사 붙여넣기 -> .class 지우기	
			con = DriverManager.getConnection(DBConfig.URL, DBConfig.USERNAME, DBConfig.PASSWORD);
			String sql = "select * from servlet_student_tb";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Student student = Student.builder()
						.id(rs.getInt(1))
						.name(rs.getString(2))
						.age(rs.getInt(3))
						.build();
				students.add(student);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
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
		
		return students;
	}
}

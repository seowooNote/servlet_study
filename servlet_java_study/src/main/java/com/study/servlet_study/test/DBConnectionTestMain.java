package com.study.servlet_study.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.study.servlet_study.config.DBConnectionMgr;
import com.study.servlet_study.entity.Author;

public class DBConnectionTestMain {

	public static void main(String[] args) {
		// singleton
		DBConnectionMgr pool = DBConnectionMgr.getInstance();
		
		Connection con = null; // 연결
		PreparedStatement pstmt = null; // SQL 쿼리(DML)
		ResultSet rs = null; // 결과 (mySQL 에서의 결과 출력 ctrl + enter)
		
		
		try {
			con = pool.getConnection();
			String name = "홍길동";
			// String sql = "select * from author_tb where author_name = \'" + name + "\'";
			String sql = "select * from author_tb where author_name = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name); // .setString() : ? 를 찾아감 1 번째 물음표, 해당하는 값(name) / 쿼리문의 where 역할하는 메소드
			rs = pstmt.executeQuery(); // .executeQuery() : 쿼리 실행 / rs : 커서(cursor) 깜빡깜빡... (다음이 있는가?)
			
			// sql -> java List
			List<Author> authorList = new ArrayList<>();
			
			while(rs.next()) {
				authorList.add(Author
						.builder()
						.authorId(rs.getInt(1))
						.authorName(rs.getString(2))
						.build());
			}
			
			// select 조회 for java
			// forEach & Lamda
			authorList.forEach(author -> System.out.println(author));
			// 향상된 for문
//			for(Author author : authorList) {
//				System.out.println(author);
//			}
			// for문
//			for(int i = 0; i < authorList.size(); i++) {
//				Author author = authorList.get(i);
//				System.out.println(author);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 객체 소멸
			pool.freeConnection(con, pstmt, rs);
		}

	}

}

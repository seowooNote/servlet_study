package com.study.servlet_study.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.study.servlet_study.config.DBConnectionMgr;
import com.study.servlet_study.entity.Author;
import com.study.servlet_study.entity.Book;

public class BookSearchMain2 {

	public static void main(String[] args) {
		DBConnectionMgr pool = DBConnectionMgr.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Scanner scanner = new Scanner(System.in);
		try {
			con = pool.getConnection();
			System.out.print("검색할 도서명을 입력하세요 >>> ");
			String searchStr = scanner.nextLine();
			String searchQuery = "select\r\n"
					+ "    bt.book_name,\r\n"
					+ "    at.author_name,\r\n"
					+ "    pt.publisher_name\r\n"
					+ "from\r\n"
					+ "	book_tb bt\r\n"
					+ "    left outer join author_tb at on(at.author_id = bt.author_id)\r\n"
					+ "    left outer join publisher_tb pt on(pt.publisher_id = bt.publisher_id)\r\n"
					+ "where \r\n"
					+ "	bt.book_name like"
					+ "\'%" + searchStr + "%\'";
			pstmt = con.prepareStatement(searchQuery);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.println("도서명 :" + rs.getString(1) + ", 저자명 : " + rs.getString(2) + ", 출판사 : " + rs.getString(3));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(null, null, null);
		}

	}

}

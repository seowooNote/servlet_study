package com.study.servlet_study.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.study.servlet_study.config.DBConnectionMgr;
import com.study.servlet_study.entity.Author;
import com.study.servlet_study.entity.Book;
import com.study.servlet_study.entity.Publisher;

public class BookInsertMain {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String bookName = null;
		String authorName = null;
		String publisherName = null;
		
		System.out.print("도서명 >>> ");
		bookName = scanner.nextLine();
		System.out.print("저자명 >>> ");
		authorName = scanner.nextLine();
		System.out.print("출판사 >>> ");
		publisherName = scanner.nextLine();
		
		Book book = Book.builder()
				.bookName(bookName)
				.author(Author.builder().authorName(authorName).build())
				.publisher(Publisher.builder().publisherName(publisherName).build())
				.build();

		
		DBConnectionMgr pool = DBConnectionMgr.getInstance();
		Connection con = null;
		PreparedStatement pstmt = null;
		// ResultSet 없음 -> select 쿼리에서 사용
		int count = 0;
		
		try {
			con = pool.getConnection();
			String sql = "insert into author_tb values (0, ?)";
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // Statement -> import java.sql // Statement.RETURN_GENERATED_KEYS -> insert, update, delete 할때 사용
			pstmt.setString(1, book.getAuthor().getAuthorName());
			pstmt.executeUpdate(); // .executeUpdate() : insert, update, delete 할때 사용 // return 으로 몇건이 실행됬는지 int 갯수로 나옴
			ResultSet rs = pstmt.getGeneratedKeys(); //.getGeneratedKeys() : ai(auto_increment) 된 key 값을 가져옴
			
			if(rs.next()) {
				book.getAuthor().setAuthorId(rs.getInt(1));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		
		try {
			con = pool.getConnection();
			String sql = "insert into publisher_tb values (0, ?)";
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // Statement -> import java.sql // Statement.RETURN_GENERATED_KEYS -> insert, update, delete 할때 사용
			pstmt.setString(1, book.getPublisher().getPublisherName());
			pstmt.executeUpdate(); // .executeUpdate() : insert, update, delete 할때 사용 // return 으로 몇건이 실행됬는지 int 갯수로 나옴
			ResultSet rs = pstmt.getGeneratedKeys(); //.getGeneratedKeys() : ai(auto_increment) 된 key 값을 가져옴
			
			if(rs.next()) {
				book.getPublisher().setPublisherId(rs.getInt(1));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		
		try {
			con = pool.getConnection();
			String sql = "insert into book_tb values (0, ?, ?, ?)";
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // Statement -> import java.sql // Statement.RETURN_GENERATED_KEYS -> insert, update, delete 할때 사용
			pstmt.setString(1, book.getBookName());
			pstmt.setInt(2, book.getAuthor().getAuthorId());
			pstmt.setInt(3, book.getPublisher().getPublisherId());
			pstmt.executeUpdate(); // .executeUpdate() : insert, update, delete 할때 사용 // return 으로 몇건이 실행됬는지 int 자료형의 갯수로 나옴
			ResultSet rs = pstmt.getGeneratedKeys(); //.getGeneratedKeys() : ai(auto_increment) 된 key 값을 가져옴
			
			if(rs.next()) {
				book.setBookId(rs.getInt(1));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		
		System.out.println("[추가된 도서 정보]");
		System.out.println(book);
		
		
	}

}

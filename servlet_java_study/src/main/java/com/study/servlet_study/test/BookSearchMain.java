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
import com.study.servlet_study.entity.Publisher;

public class BookSearchMain {

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
			String searchQuery = 
					  "select\r\n"
					+ "	   bt.book_id,\r\n"
					+ "    bt.book_name,\r\n"
					+ "    bt.author_id,\r\n"
					+ "    at.author_name,\r\n"
					+ "    bt.publisher_id,\r\n"
					+ "    pt.publisher_name\r\n"
					+ "from\r\n"
					+ "	   book_tb bt\r\n"
					+ "    left outer join author_tb at on(at.author_id = bt.author_id)\r\n"
					+ "    left outer join publisher_tb pt on(pt.publisher_id = bt.publisher_id)"
					+ "where \r\n"
					+ "	bt.book_name like"
					+ "\'%" + searchStr + "%\'";
			pstmt = con.prepareStatement(searchQuery);
			rs = pstmt.executeQuery();
			
			List<Book> bookList = new ArrayList<>();
			
			while(rs.next()) {
				bookList.add(Book.builder().
						bookId(rs.getInt(1)).
						bookName(rs.getString(2)).
						author(Author.builder().authorId(rs.getInt(3)).authorName(rs.getString(4)).build()).
						publisher(Publisher.builder().publisherId(rs.getInt(5)).publisherName(rs.getString(6)).build()).
						build()
						);
			}
			
			bookList.forEach(book -> System.out.println("도서명: " + book.getBookName() + " / 저자명: " + book.getAuthor().getAuthorName() + " / 출판사: " + book.getPublisher().getPublisherName()));
			for(Book book : bookList) {
				System.out.println("도서명: " + book.getBookName() + " / 저자명: " + book.getAuthor().getAuthorName() + " / 출판사: " + book.getPublisher().getPublisherName());
			}
			for(int i = 0; i < bookList.size(); i++) {
				System.out.println("도서명: " + bookList.get(i).getBookName() + " / 저자명: " + bookList.get(i).getAuthor().getAuthorName() + " / 출판사: " + bookList.get(i).getPublisher().getPublisherName());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(null, null, null);
		}

	}

}

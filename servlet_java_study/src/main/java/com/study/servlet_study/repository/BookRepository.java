package com.study.servlet_study.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.study.servlet_study.config.DBConnectionMgr;
import com.study.servlet_study.entity.Author;
import com.study.servlet_study.entity.Book;
import com.study.servlet_study.entity.Publisher;

//singleton Pattern
public class BookRepository {
	private static BookRepository instance;
	private DBConnectionMgr pool;
	
	private BookRepository() {
		pool = DBConnectionMgr.getInstance();
	}
	
	public static BookRepository getInstance() {
		if(instance == null) {
			instance = new BookRepository();
		}
		return instance;
	}
	
	public int saveBook(Book book) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
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
		
		return 1;
	}
	
	public Book findBookByBookId(int bookId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Book findBook = null;
		
		try {
			con = pool.getConnection();
			String sql = "select * from book_view where book_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bookId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				findBook = Book.builder()
						.bookId(rs.getInt(1))
						.bookName(rs.getString(2))
						.author(Author.builder()
								.authorId(rs.getInt(3))
								.authorName(rs.getString(4))
								.build())
						.publisher(Publisher.builder()
								.publisherId(rs.getInt(5))
								.publisherName(rs.getString(6))
								.build())
						.build();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		return findBook;
	}
	
	public List<Book> searchBookList(Map<String, String> params) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Book findbook = null;
		
		List<Book> bookList = new ArrayList<>();
		
		if(params.size() == 0) {
			try {
				con = pool.getConnection();
				String sql = "select * from book_view";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					bookList.add(Book.builder()
							.bookId(rs.getInt(1))
							.bookName(rs.getString(2))
							.author(Author.builder()
									.authorId(rs.getInt(3))
									.authorName(rs.getString(4))
									.build())
							.publisher(Publisher.builder()
									.publisherId(rs.getInt(5))
									.publisherName(rs.getString(6))
									.build())
							.build());
				}
				
				for(Book book : bookList) {
					System.out.println(book);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
		} else if(params.size()!=0) {
			try {
				con = pool.getConnection();
				String sql = "select * from book_view where ? = 1";
				for(String key : params.keySet()) {
					sql += " or " + key + " like ?";
				}
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, 0);
				int i = 2;
				for(String key : params.keySet()) {
					pstmt.setString(i,"%" + params.get(key) + "%");
					i++;
				}
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					bookList.add(Book.builder()
							.bookId(rs.getInt(1))
							.bookName(rs.getString(2))
							.author(Author.builder()
									.authorId(rs.getInt(3))
									.authorName(rs.getString(4))
									.build())
							.publisher(Publisher.builder()
									.publisherId(rs.getInt(5))
									.publisherName(rs.getString(6))
									.build())
							.build());
				}
				
				for(Book book : bookList) {
					System.out.println(book);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
		}
	
		return bookList;	
		
	}
	
}

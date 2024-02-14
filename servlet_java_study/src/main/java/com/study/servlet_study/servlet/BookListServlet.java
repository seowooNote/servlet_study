package com.study.servlet_study.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.servlet_study.entity.Book;
import com.study.servlet_study.service.BookService;

@WebServlet("/books")
public class BookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BookService bookService;
    
    public BookListServlet() {
        super();
        bookService = BookService.getInstance();    
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// params
		// bookName 으로 찾을 수 있게 -> like 조회
		// authorName 으로 찾을 수 있게 -> like 조회
		// publisherName 으로 찾을 수 있게 -> like 조회
		String bookName = request.getParameter("bookName");
		String authorName = request.getParameter("authorName");
		String publisherName = request.getParameter("publisherName");
		
		Map<String, String> params = new HashMap<>();
		if(bookName != null) {
			params.put("book_name", bookName);
		}
		if(authorName != null) {
			params.put("author_name", authorName);
		}
		if(bookName != null) {
			params.put("publisher_name", publisherName);
		}
		
		List<Book> bookList = bookService.getBookList(params);
		
		response.setContentType("text/plain");
		response.setStatus(201);
		response.getWriter().println(bookList);
	}

}

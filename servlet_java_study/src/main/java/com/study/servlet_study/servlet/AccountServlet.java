package com.study.servlet_study.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.servlet_study.entity.Account;
import com.study.servlet_study.repository.AccountRepository;
import com.study.servlet_study.service.AccountService;


@WebServlet("/account")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccountService accountService;
       
    public AccountServlet() {
        super();
        accountService = AccountService.getInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("username");
		Account account = accountService.getAccount(userName);
		response.setStatus(200);
		response.getWriter().println(account);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		// Builder Pattern 으로 객체 생성
		Account account = Account.builder()
				.userName(userName)
				.password(password)
				.name(name)
				.email(email)
				.build();
		
		int body = accountService.addAccount(account);
		response.setStatus(201);
		response.getWriter().println(body);
	}

}

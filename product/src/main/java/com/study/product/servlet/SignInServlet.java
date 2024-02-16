package com.study.product.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.product.dto.UserDto;
import com.study.product.utils.RequestUtil;
import com.study.product.utils.ResponseEntity;

@WebServlet("/auth/signin")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SignInServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DB 에서 User 정보 꺼내기
		UserDto dbUser = (UserDto) request.getServletContext().getAttribute("dbUser");
		// front 에서 입력한 User 정보 받기
		UserDto signinUserDto = RequestUtil.convertJsonData(request, UserDto.class);
		
		boolean isMatchUsername = Objects.equals(dbUser.getUsername(), signinUserDto.getUsername());		
		boolean isMatchPassword = Objects.equals(dbUser.getPassword(), signinUserDto.getPassword());
		
		if(!(isMatchUsername && isMatchPassword)) {
			Map<String, String> errorMap = new HashMap<>();
			errorMap.put("errorMessage", "사용자 정보를 다시 확인하세요");
			ResponseEntity.ofJson(response, 403, errorMap);
			return;
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("principalUser", dbUser);
		ResponseEntity.ofJson(response, 200, dbUser);
		
	}

}

package com.study.product.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.product.dto.InsertUserRequsetDto;
import com.study.product.utils.RequestUtil;


@WebServlet("/user")
public class InsertUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public InsertUserServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InsertUserRequsetDto dto = RequestUtil.convertJsonData(request, InsertUserRequsetDto.class);
		System.out.println(dto);
	}

}

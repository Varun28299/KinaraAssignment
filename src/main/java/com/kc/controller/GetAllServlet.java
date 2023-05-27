package com.kc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kc.dto.Student;
import com.kc.dao.StudentCRUD;

@WebServlet(value = "/studentdata")
public class GetAllServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int page = Integer.parseInt(req.getParameter("page") == null ? "1" : req.getParameter("page"));

		Integer min = req.getParameter("minMarks") == null ? null : Integer.parseInt(req.getParameter("minMarks"));
		Integer max = req.getParameter("maxMarks") == null ? null : Integer.parseInt(req.getParameter("maxMarks"));

		System.out.println("min value =" + min);
		System.out.println("max value =" + max);

		StudentCRUD sc = new StudentCRUD();
		ArrayList<Student> students = sc.getAllStudents(page, min, max);
		RequestDispatcher rd = req.getRequestDispatcher("student.jsp");

		req.setAttribute("data", students);
		rd.forward(req, resp);

	}

}

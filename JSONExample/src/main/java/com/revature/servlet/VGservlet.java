package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.VideoGame;
import com.revature.dao.VgDaoImpl;

/**
 * Servlet implementation class VGservlet
 */
public class VGservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In do get");
		ObjectMapper mapper = new ObjectMapper();
		VgDaoImpl vgdi = new VgDaoImpl();
		int id = mapper.readValue(request.getParameter("vgid"),Integer.class);
		PrintWriter pw = response.getWriter();
		String vgJSON;
		try {
			vgJSON = mapper.writeValueAsString(vgdi.getVGbyID(id));
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			pw.print(vgJSON);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pw.flush();
	}

	//get a JSON from our AJAX call and save to the database
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In do post");
		VideoGame vg=null;
		ObjectMapper mapper = new ObjectMapper();
		//convert JSON to Java Object
		vg = mapper.readValue(request.getInputStream(), VideoGame.class);
		System.out.println(vg);
		VgDaoImpl vgdi = new VgDaoImpl();
		try {
			vgdi.insertVG(vg);
			PrintWriter pw = response.getWriter();
			pw.write("<h3> Added Video Game</h3>");
			pw.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

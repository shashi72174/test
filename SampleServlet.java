package com.sampleservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class SampleServlet
 */
public class SampleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			String url = "jdbc:mysql://localhost:3306/test";
		    String user = "root";
		    String password = "root";
		    String driverClass = "com.mysql.jdbc.Driver";
		    try {
	            Class.forName(driverClass);
	            Connection connection = DriverManager.getConnection(url, user, password);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		    
		    
		    String query = "select SQL_CALC_FOUND_ROWS * from employee limit "
	                 + 0 + ", " + 10;
		    
		    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("came here");
		String action = request.getParameter("action");
		Map<String,String> citiesMap = new HashMap<String, String>();
		if("getCityDetails".equals(action)) {
			String state = request.getParameter("stateId");
			if((state.equals("Karnataka"))) {
				citiesMap.put("Bangalore", "Bangalore");
				citiesMap.put("Mysuru", "Mysuru");
				citiesMap.put("Mangalore", "Mangalore");
			}else if(state.equals("Andhra Pradesh")) {
				citiesMap.put("Guntur", "Guntur");
				citiesMap.put("Prakasam", "Prakasam");
				citiesMap.put("Ongole", "Ongole");
			}else if(state.equals("Tamil Nadu")) {
				citiesMap.put("Coimbatore", "Coimbatore");
				citiesMap.put("Salem", "Salem");
				citiesMap.put("Erode", "Erode");
			}
			System.out.println(citiesMap.size());
			response.setContentType("application/json");
			PrintWriter writer = response.getWriter();
			writer.write(new Gson().toJson(citiesMap));
		}else if("test".equals(action)) {
			String city = request.getParameter("cityId");
			response.setContentType("application/json");
			PrintWriter writer = response.getWriter();
			writer.write(new Gson().toJson("city : "+city));
		}
	}

}

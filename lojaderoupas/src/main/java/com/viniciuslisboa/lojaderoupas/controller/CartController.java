package com.viniciuslisboa.lojaderoupas.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.viniciuslisboa.lojaderoupas.business.bean.RoupaBean;

/**
 * Servlet implementation class CartController
 */
@WebServlet("/cart")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CartController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();
		
		
		if (session.getAttribute("cart") == null) {
			
			
			List<RoupaBean> cart = new ArrayList<>();
			session.setAttribute("cart", cart);
		}
		
		
		List<RoupaBean> cart = (List<RoupaBean>) session.getAttribute("cart");
		
		
		request.setAttribute("cart", cart);
		
		
		Float total = 0f;
		for (RoupaBean roupa : cart) {
			total += roupa.getPreco();
		}
		request.setAttribute("total", total);
		
		
		request.getRequestDispatcher("/cart.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		String indexString = request.getParameter("remover");
		int index = Integer.parseInt(indexString) - 1; // Count e 1-based
		
		
		HttpSession session = request.getSession();
		List<RoupaBean> cart = (List<RoupaBean>) session.getAttribute("cart");
		
		
		cart.remove(index);
		
		doGet(request, response);
	}

}

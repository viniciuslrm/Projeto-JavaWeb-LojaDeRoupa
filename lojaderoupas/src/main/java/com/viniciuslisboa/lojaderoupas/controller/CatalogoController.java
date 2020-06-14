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

import com.viniciuslisboa.lojaderoupas.business.bean.CatalogoBean;
import com.viniciuslisboa.lojaderoupas.business.bean.RoupaBean;
import com.viniciuslisboa.lojaderoupas.business.enums.CategoriaEnum;

/**
 * Servlet implementation class CatalogoController
 */
@WebServlet("/catalogo")
public class CatalogoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CatalogoBean catalogoBean;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CatalogoController() {
		super();

		
		catalogoBean = new CatalogoBean();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		String[] codigosCategoria = request.getParameterValues("categoria");

		
		request.setAttribute("roupas", catalogoBean.getRoupasFiltradas(codigosCategoria));
		request.setAttribute("categorias", CategoriaEnum.values());
		
		
		if (request.getParameter("adicionar") != null) {
			
			HttpSession session = request.getSession();
	
			
			if (session.getAttribute("cart") == null) {
				
				
				List<RoupaBean> cart = new ArrayList<>();
				session.setAttribute("cart", cart);
			}
			
		
			List<RoupaBean> cart = (List<RoupaBean>) session.getAttribute("cart");
			
			
			String codigoString = request.getParameter("adicionar");
			Integer codigo = Integer.parseInt(codigoString);
			
			
			List<RoupaBean> todasAsRoupas = catalogoBean.getRoupas();
			for (RoupaBean roupa : todasAsRoupas) {
				if (roupa.getCodigo().equals(codigo)) {
					cart.add(roupa);
				}
			}
			
		}

		
		request.getRequestDispatcher("/catalogo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

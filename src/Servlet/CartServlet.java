package Servlet;

import DAO.ItemsDao;
import Entity.Cart;
import Entity.Items;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by CiCi on 2017/3/1.
 * Servlet层主要是处理和前端页面交互的部分，从前端页面获取参数，并对其进行操作
 */
@WebServlet(name = "CartServlet",urlPatterns = {"/Servlet/CartServlet"})
public class CartServlet extends HttpServlet {
	private String action;
	ItemsDao itemsDao = new ItemsDao();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("action") != null)  {
			this.action = request.getParameter("action");
			if (action.equals("add")) {
				System.out.println("add");
				if (addToCart(request,response)) {
					request.getRequestDispatcher("/success.jsp").forward(request,response);
				}
				else {
					request.getRequestDispatcher("/failure.jsp").forward(request,response);
				}
			}
			if (action.equals("delete")) {
				System.out.println("delete");
				if (deleteFromCart(request,response)) {
					request.getRequestDispatcher("/cart.jsp").forward(request,response);
				}
				else {
					request.getRequestDispatcher("/cart.jsp").forward(request,response);
				}
			}
			if (action.equals("show")) {
				request.getRequestDispatcher("/cart.jsp").forward(request,response);
			}
		}
	}
	
	private boolean addToCart(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String num = request.getParameter("num");
		
		Items items = itemsDao.getItemByID(Integer.parseInt(id));
		if (request.getSession().getAttribute("cart") == null) {
			Cart cart = new Cart();
			request.getSession().setAttribute("cart",cart);
		}
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		
		return cart.addIntoCart(items,Integer.parseInt(num));
	}
	
	private boolean deleteFromCart(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
//		Items items = itemsDao.getItemByID(Integer.parseInt(id));
		
		if (request.getSession().getAttribute("cart") == null) {
			Cart cart = new Cart();
			request.getSession().setAttribute("cart",cart);
		}
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		System.out.println("要删除的商品id" + id);
		
		cart.removeGoodsFromCart(Integer.parseInt(id));
		
		request.getSession().setAttribute("cart",cart);
		Iterator<Items> itemsIterator = cart.getGoods().keySet().iterator();
		while (itemsIterator.hasNext()) {
			Items it = itemsIterator.next();
			System.out.println(it.getName());
		}
		
		System.out.println("deleteFromCart");
		return true;
		
		
	}
}

package com.dainc.controller.web;

import java.io.IOException;

import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dainc.model.MstUserModel;
import com.dainc.service.IMstUserService;
import com.dainc.utils.FormUtil;
import com.dainc.utils.SessionUtil;

@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
	
	
	
	@Inject
	private IMstUserService mstUserService;
	
	private static final long serialVersionUID = 2686801510274002166L;

	ResourceBundle resourceBundle = ResourceBundle.getBundle("message");
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null && action.equals("login")) {
			String alert = request.getParameter("alert");
			String message = request.getParameter("message");
			if (message != null && alert != null) {
				request.setAttribute("message", resourceBundle.getString(message));
				request.setAttribute("alert", alert);
			}
			RequestDispatcher rd = request.getRequestDispatcher("/views/login.jsp");
			rd.forward(request, response);
		} 
		else if (action != null && action.equals("logout")) {
			SessionUtil.getInstance().removeValue(request, "USERMODEL");
			response.sendRedirect(request.getContextPath()+"/login?action=login");
		} 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null && action.equals("login")) {
			MstUserModel mstModel = FormUtil.toModel(MstUserModel.class, request);
			mstModel = mstUserService.findByUserNameAndPassword(mstModel.getUserId(), mstModel.getPassword());
			if (mstModel != null) {
				SessionUtil.getInstance().putValue(request, "USERMODEL", mstModel);
				response.sendRedirect(request.getContextPath()+"/admin-user?type=list");
			} 
			else {
				response.sendRedirect(request.getContextPath()+"/login?action=login&message=username_password_invalid&alert=danger");
			}
		}
	}
}
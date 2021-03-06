package com.dainc.controller.admin.mobileapi;

import java.io.IOException;

import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.dainc.model.MstUserModel;
import com.dainc.service.IMstUserService;
import com.dainc.utils.FormUtil;
import com.dainc.utils.HttpUtil;
import com.dainc.utils.SessionUtil;

@WebServlet(urlPatterns = {"/api-user"})
public class UserAPI extends HttpServlet {
	
	@Inject
	private IMstUserService mstUserService;

	private static final long serialVersionUID = -915988021506484384L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		MstUserModel userModel =  FormUtil.toModel(MstUserModel.class, request);
		mapper.writeValue(response.getOutputStream(), mstUserService.findOne(userModel.getUserId()));
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.createObjectNode();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		MstUserModel userModel =  FormUtil.toModel(MstUserModel.class, request);
		userModel.setCreatedBy(((MstUserModel) request.getAttribute("loginUser")).getUserId());
		if(mstUserService.save(userModel)) {		//登録してみる
			((ObjectNode) rootNode).put("status", "add_success");
			mapper.writeValue(response.getOutputStream(), rootNode);
			return;
		}
		((ObjectNode) rootNode).put("status", "user_haved");
		mapper.writeValue(response.getOutputStream(), rootNode);
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.createObjectNode();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		MstUserModel userModel =  FormUtil.toModel(MstUserModel.class, request);
		userModel.setModifiedBy(((MstUserModel) request.getAttribute("loginUser")).getUserId());
		if(mstUserService.update(userModel)) {
			((ObjectNode) rootNode).put("status", "edit_success");
			mapper.writeValue(response.getOutputStream(), rootNode);
			return;
		}
		((ObjectNode) rootNode).put("status", "not_haved");
		mapper.writeValue(response.getOutputStream(), rootNode);
	}
	

}

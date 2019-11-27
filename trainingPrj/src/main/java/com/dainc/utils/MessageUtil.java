package com.dainc.utils;

import javax.servlet.http.HttpServletRequest;

public class MessageUtil {
	
	public static void showMessage(HttpServletRequest request) {
		if (request.getParameter("message") != null) {
			String messageResponse = "";
			String alert = "";
			String message = request.getParameter("message");
			if (message.equals("userid_haved")) {
				messageResponse = "���[�U�[ID������܂����B";
				alert = "danger";
			} else if (message.equals("false")) {
				messageResponse = "���s���܂����B";
				alert = "danger";
			} else if (message.equals("not_haved")) {
				messageResponse = "���ʂ�����܂���B";
				alert = "danger";
			} else if (message.equals("add_success")) {
				messageResponse = "�o�^�ł��܂����B";
				alert = "success";
			} else if (message.equals("edit_success")) {
				messageResponse = "�X�V�ł��܂����B";
				alert = "success";
			} else if (message.equals("delete_success")) {
				messageResponse = "�폜�ł��܂����B";
				alert = "success";
			} 
			request.setAttribute("message", messageResponse);
			request.setAttribute("alert", alert);
		}
	}
}

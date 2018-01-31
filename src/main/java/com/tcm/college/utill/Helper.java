package com.tcm.college.utill;

import java.util.Calendar;
import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class Helper {

	public static String getRoleDefaultUrl(Authentication authentication) {
		String targetUrl = "/";

		if (authentication != null) {
			for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
				String role = grantedAuthority.getAuthority();

				if (Constant.ROLE.SUPER_ADMIN.equals(role)) {
					targetUrl = Constant.URL.MANAGE_CONSUMER;
				} else if (Constant.ROLE.INSTRUCTOR.equals(role)) {
					targetUrl = Constant.URL.CREATE_CONTENT;
				} else if (Constant.ROLE.STUDENT.equals(role)) {
					targetUrl = Constant.URL.VIEW_CONTENT;
				}
			}
		}
		return targetUrl;
	}

	public static Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	public static Date getCurrentDate() {
		return Calendar.getInstance().getTime();
	}

}

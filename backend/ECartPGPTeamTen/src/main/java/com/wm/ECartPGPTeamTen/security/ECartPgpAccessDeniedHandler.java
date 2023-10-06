package com.wm.ECartPGPTeamTen.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Raju
 *
 */
@Component
public class ECartPgpAccessDeniedHandler implements AccessDeniedHandler
{

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			org.springframework.security.access.AccessDeniedException accessDeniedException)
			throws IOException, ServletException {
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		final Map<String, Object> body = new HashMap<>();
		body.put("code", HttpServletResponse.SC_FORBIDDEN);
		body.put("status", "Unauthorized");
		body.put("body", "Not an Authorized Token, Unauthorized Request");
		body.put("path", request.getServletPath());
		final ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(response.getOutputStream(), body);
		
	}

}

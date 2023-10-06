package com.wm.ECartPGPTeamTen.security;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Raju
 *
 */
@Component
public class ECartPgpJwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = -7858869558953243875L;

	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

		String com = "";
		final Map<String, Object> body = new HashMap<>();
		if (request.getAttribute("authecart") != null) {
			com = (String) request.getAttribute("authecart");
			if ("expired".equalsIgnoreCase(com)) {
				//body.put("status", HttpServletResponse.SC_REQUEST_TIMEOUT);
				body.put("code", 0000);
				body.put("status", "Expired");
				body.put("data", "Your Session Expired");
			} else if ("invalid".equalsIgnoreCase(com)) {
				body.put("code", HttpServletResponse.SC_BAD_REQUEST);
				body.put("status", "InvalidToken");
				body.put("data", "Not an Authorized Token, Unauthorized Request");
			} else {
				body.put("code", HttpServletResponse.SC_UNAUTHORIZED);
				body.put("status", "Unauthenticated");
				body.put("data", authException.getMessage());
			}
		} else {
			
			body.put("code", HttpServletResponse.SC_UNAUTHORIZED);
			body.put("status", "Unauthenticated");
			body.put("data", authException.getMessage());
		}
		body.put("path", request.getServletPath());
		final ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(response.getOutputStream(), body);
	}
}

package com.wm.ECartPGPTeamTen.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.wm.ECartPGPTeamTen.service.ECartPgpJwtUserDetailsService;
import com.wm.ECartPGPTeamTen.util.ECartPGPJwtTokenUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Raju
 *
 */
@Component
@Slf4j
public class ECartPgpJwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private ECartPgpJwtUserDetailsService jwtUserDetailsService;

	@Autowired
	private ECartPGPJwtTokenUtil jwtTokenUtil;


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		final String requestTokenHeader = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;

		if (requestTokenHeader != null && requestTokenHeader.startsWith("ecart")) {
			jwtToken = requestTokenHeader.substring(5);
			try {
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);

				// Once we get the token validate it.
				if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
					UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

					// if token is valid configure Spring Security to manually set authentication
					if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
						UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
								userDetails, null, userDetails.getAuthorities());
						usernamePasswordAuthenticationToken
								.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						// After setting the Authentication in the context, we specify
						// that the current user is authenticated. So it passes the Spring Security
						// Configurations successfully.
						SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
					}
				}
			} catch (SignatureException e) {
				log.error("Unable to get JWT Token");
				request.setAttribute("authecart", "invalid");
			} catch (IllegalArgumentException e) {
				log.error("Unable to get JWT Token");
				request.setAttribute("authtecart", "invalid");
			} catch (ExpiredJwtException e) {
				log.error("JWT Token has expired");
				request.setAttribute("authtecart", "expired");
			}catch (Exception e) {
				log.error(" JWT Token has expired");
				request.setAttribute("authtecart", "expireds");
			}
		} else {
			logger.warn("JWT Token does not begin with ecart String");
			request.setAttribute("authtecart", "invalid");
		}
		chain.doFilter(request, response);
	}

}

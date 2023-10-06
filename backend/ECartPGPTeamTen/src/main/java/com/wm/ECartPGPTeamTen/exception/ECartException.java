package com.wm.ECartPGPTeamTen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * r0m09yu
 */

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ECartException extends Exception {

	private static final long serialVersionUID = 1L;

	public ECartException(String message) {
		super(message);
	}
}

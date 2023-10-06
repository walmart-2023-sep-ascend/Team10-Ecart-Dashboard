package com.wm.ECartPGPTeamTen.service;


import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;


/**
 * r0m09yu
 */
@Component
public class MessageService {

	@Resource
	private MessageSource messageSource;

	public String getMessage(String code) {
		return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
	}
}
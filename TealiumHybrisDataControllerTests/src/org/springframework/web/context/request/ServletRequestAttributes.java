package org.springframework.web.context.request;

import javax.servlet.http.HttpServletRequest;

public class ServletRequestAttributes {

	public HttpServletRequest getRequest() {

		return new HttpServletRequest();
	}

}

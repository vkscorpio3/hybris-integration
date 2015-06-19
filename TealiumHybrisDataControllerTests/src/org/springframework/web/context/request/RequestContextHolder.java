package org.springframework.web.context.request;

public class RequestContextHolder {

	public static ServletRequestAttributes currentRequestAttributes() {
		return new ServletRequestAttributes();
	}

}

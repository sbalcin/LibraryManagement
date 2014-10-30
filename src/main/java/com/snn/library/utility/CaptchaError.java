package com.snn.library.utility;

public class CaptchaError extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CaptchaError() {
		System.out.println("Invalid Security Code");
	}
}

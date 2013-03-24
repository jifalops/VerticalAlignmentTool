package com.verticalalignmenttool.token;

import com.verticalalignmenttool.Token;

public class JavaToken extends Token {


	public JavaToken(String text) {
		this(0, text);
	}

	public JavaToken(int offset, String text) {
		super(offset, text);
	}

}

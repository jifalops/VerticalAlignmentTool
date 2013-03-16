package com.verticalalignmenttool.elements;

public class BasicToken extends Token {
	protected String mText;

	public BasicToken(String text) {
		this(1, text);
	}

	public BasicToken(int offset, String text) {
		super(offset);
		mText = text;
	}

	public final String getText() {
		return mText;
	}

	public final void setText(String text) {
		mText = text;
	}
}

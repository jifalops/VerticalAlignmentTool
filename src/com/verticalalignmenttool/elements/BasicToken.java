package com.verticalalignmenttool.elements;

public class BasicToken extends Token {
	String mText;

	public BasicToken() {
		super();
	}

	public BasicToken(double offset) {
		super(offset);
	}

	public BasicToken(String text) {
		super();
		mText = text;
	}

	public BasicToken(double offset, String text) {
		super(offset);
		mText = text;
	}

	public String getText() {
		return mText;
	}

	public void setText(String text) {
		mText = text;
	}
}

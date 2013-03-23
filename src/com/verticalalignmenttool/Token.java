package com.verticalalignmenttool;

public class Token extends AbstractToken {
	protected String mText;

	public Token(String text) {
		this(0, text);
	}

	public Token(int offset, String text) {
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

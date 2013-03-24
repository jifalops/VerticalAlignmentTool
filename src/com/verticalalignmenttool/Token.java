package com.verticalalignmenttool;

public class Token extends AbstractToken {
	public static final int WHITE_SPACE = 1;
	public static final int COMMENT = 2;
	public static final int IDENTIFIER = 3;
	public static final int KEYWORD = 4;
	public static final int LITERAL = 5;
	public static final int SEPARATOR = 6;
	public static final int OPERATOR = 7;

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

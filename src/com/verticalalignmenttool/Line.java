package com.verticalalignmenttool;

/** A line of text, not including the newline character. */
public class Line extends AbstractLine {
	protected String mText;
	protected final TokenGroup mTokens;

	public Line(String text) {
		this(0, text);
	}

	public Line(int offset, String text) {
		super(offset);
		mText = text;
		mTokens = new TokenGroup(0);
	}


	public final String getText() {
		return mText;
	}

	public final void setText(String text) {
		mText = text;
	}

	public final TokenGroup getTokens() {
		return mTokens;
	}
}

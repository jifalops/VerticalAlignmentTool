package com.verticalalignmenttool;

import java.util.List;

public final class Line {
	private final String mText;
	private List<Token> mTokens;

	// positive = excess open, negative = excess close
	private int mParens, mSqareBrackets, mCurlyBraces;

	public Line(String text) {
		mText = text;
	}

	public String getText() {
		return mText;
	}
}
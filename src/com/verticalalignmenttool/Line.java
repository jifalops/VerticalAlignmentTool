package com.verticalalignmenttool;

import java.util.List;

public final class Line {
	private final String mText;
	private List<Token> mTokens;
	private List<TokenGroup> mGroups;

	private int mNumOpenParens, mNumOpenSqareBrackets, mNumOpenCurlyBraces;

	public Line(String text) {
		mText = text;
	}

	public String getText() {
		return mText;
	}
}
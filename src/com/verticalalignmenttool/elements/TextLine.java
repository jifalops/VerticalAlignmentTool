package com.verticalalignmenttool.elements;


public class TextLine extends Line {
	protected String mText;


	public TextLine(String text) {
		this(0, text);
	}

	public TextLine(int offset, String text) {
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

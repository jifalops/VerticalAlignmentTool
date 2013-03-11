package com.verticalalignmenttool.elements;


public class TextLine extends Line {
	private String mText;

	public TextLine() {

	}

	public TextLine(String text) {
		mText = text;
	}


	public String getText() {
		return mText;
	}

	public void setText(String text) {
		mText = text;
	}

}

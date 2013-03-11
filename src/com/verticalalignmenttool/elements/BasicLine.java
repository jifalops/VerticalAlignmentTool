package com.verticalalignmenttool.elements;


public class BasicLine extends Line {
	private String mText;

	public BasicLine() {

	}

	public BasicLine(String text) {
		mText = text;
	}


	public String getText() {
		return mText;
	}

	public void setText(String text) {
		mText = text;
	}

}

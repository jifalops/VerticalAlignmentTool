package com.verticalalignmenttool.elements;

import java.util.LinkedList;

public class TokenGroup extends Token {
	private LinkedList<Token> mChildren;

	public TokenGroup() {
		super();
	}

	public TokenGroup(double offset) {
		super(offset);
		mChildren = new LinkedList<Token>();
	}

	public LinkedList<Token> getChildren() {
		return mChildren;
	}
}

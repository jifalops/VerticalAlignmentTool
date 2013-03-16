package com.verticalalignmenttool.elements;

import java.util.LinkedList;

public class TokenGroup extends Token {
	protected LinkedList<Token> mChildren;

	public TokenGroup() {
		this(1);
	}

	public TokenGroup(int offset) {
		super(offset);
		mChildren = new LinkedList<Token>();
	}

	public final LinkedList<Token> getChildren() {
		return mChildren;
	}
}

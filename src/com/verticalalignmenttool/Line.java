package com.verticalalignmenttool;

/** A line of text, not including the newline character. */
public class Line extends AbstractLine {
	public static enum Type {
		NONE,
		MIXED,
		OTHER,
		BLANK,
		COMMENT,
		STRING,
		PACKAGE,
		IMPORT,
		VARIABLE_READ,
		VARIABLE_WRITE,
		CLASS_DEF,
		METHOD_DEF,
		METHOD_CALL,
		CONSTRUCTOR,
		SPECIAL_METHOD_CALL, //super
		LOOP,
		BEGIN_GROUP, // {
		END_GROUP, // }
		IF_ELSE,
		SWITCH,
		CASE,
		TRY_CATCH,

	}

	protected Type mType;
	protected String mText;
	protected final TokenGroup mTokens;

	public Line(String text) {
		this(0, text);
	}

	public Line(int offset, String text) {
		super(offset);
		mText = text;
		mTokens = new TokenGroup(0);
		mType = Type.NONE;
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

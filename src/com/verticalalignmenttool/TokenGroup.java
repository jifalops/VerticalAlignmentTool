package com.verticalalignmenttool;


public class TokenGroup {

	public static final int TYPE_MODIFIERS = 1;

	public final int type;
	public final int startIndex;
	public final int endIndex;

	/**
	 * Represents information about a group of similar tokens that are
	 * consecutive in a list/array of tokens.
	 * @param type - the type of this TokenGroup
	 * @param startIndex - the index in the list/array for the first token in this group.
	 * @param endIndex - the index in the list/array for the last token in this group.
	 */
	public TokenGroup(int type, int startIndex, int endIndex) {
		this.type = type;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}
}

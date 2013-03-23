package com.verticalalignmenttool;


public abstract class AbstractElement {
	protected int mOffset;

	public AbstractElement() {
		this(0);
	}

	public AbstractElement(int offset) {
		mOffset = offset;
	}

	/** Number of spaces before the first printable character. */
	public final int getOffset() {
		return mOffset;
	}

	public final void setOffset(int offset) {
		mOffset = offset;
	}

	/**
	 * The number of characters before this element, beginning with its root.
	 * Note: does not include the relative offset of this element.
	 * It does include the relative offset of elements before it.
	 */
	public abstract int getCharactersBefore();
}

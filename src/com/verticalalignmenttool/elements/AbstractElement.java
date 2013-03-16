package com.verticalalignmenttool.elements;


public abstract class AbstractElement {
//	public interface OnChangedListener {
//		void onLineChanged(AbstractElement line);
//	}
//
//	private static final OnChangedListener sDummyListener = new OnChangedListener() {
//		@Override public void onLineChanged(AbstractElement line) {}
//	};

//	protected OnChangedListener mListener;
	protected int mOffset;	// Char offset


	public AbstractElement() {
		this(0);
	}

	public AbstractElement(int offset) {
		mOffset = offset;
//		mListener = sDummyListener;
	}

//	public final void setOnLineChangedListener(OnChangedListener listener) {
//		mListener = listener;
//	}

	public final int getOffset() {
		return mOffset;
	}

	public final void setOffset(int offset) {
		mOffset = offset;
	}

	/** The number of characters before this element in the file. */
	public abstract int getAbsoluteOffset();
}

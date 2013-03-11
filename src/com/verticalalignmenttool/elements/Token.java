package com.verticalalignmenttool.elements;


public class Token {
	public interface onTokenChangedListener {
		void onTokenChanged(Token token);
	}

	private static final onTokenChangedListener sDummyListener = new onTokenChangedListener() {
		@Override public void onTokenChanged(Token token) {}
	};

	private static double sDefaultOffset = 1.0; // from parent/previous


	protected onTokenChangedListener mListener;
	protected double mOffset;


	public Token() {
		this(sDefaultOffset);
	}

	public Token(double offset) {
		mOffset = offset;
		mListener = sDummyListener;
	}

	public void setOnTokenChangedListener(onTokenChangedListener listener) {
		mListener = listener;
	}

	public double getOffset() {
		return mOffset;
	}

	public void setOffset(double offset) {
		mOffset = offset;
	}


	public static void setDefaultOffset(double offset) {
		sDefaultOffset = offset;
	}

	public static double getDefaultOffset() {
		return sDefaultOffset;
	}
}

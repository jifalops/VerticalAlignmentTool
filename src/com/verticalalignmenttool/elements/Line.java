package com.verticalalignmenttool.elements;


public class Line {
	public interface onLineChangedListener {
		void onLineChanged(Line line);
	}

	private static final onLineChangedListener sDummyListener = new onLineChangedListener() {
		@Override public void onLineChanged(Line line) {}
	};

	protected onLineChangedListener mListener;
	protected double mOffset;


	public Line() {
		this(0.0);
	}

	public Line(double offset) {
		mOffset = offset;
		mListener = sDummyListener;
	}

	public void setOnLineChangedListener(onLineChangedListener listener) {
		mListener = listener;
	}

	public double getOffset() {
		return mOffset;
	}

	public void setOffset(double offset) {
		mOffset = offset;
	}
}

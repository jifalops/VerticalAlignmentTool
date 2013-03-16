package com.verticalalignmenttool.elements;


public class Line extends AbstractElement {
//	public interface OnLineChangedListener {
//		void onLineChanged(Line line);
//	}

//	private static final OnLineChangedListener sDummyListener = new OnLineChangedListener() {
//		@Override public void onLineChanged(Line line) {}
//	};

//	protected OnLineChangedListener mListener;

	protected LineGroup mParent;

	public Line() {
		this(0);
	}

	public Line(int offset) {
		super(offset);
		mParent = null;
//		mListener = sDummyListener;
	}

	public final LineGroup getParent() {
		return mParent;
	}

//	public final void setOnLineChangedListener(OnLineChangedListener listener) {
//		mListener = listener;
//	}


	// TODO these probably belong in LineGroup (or replaced by a method in LineGroup).
	public final boolean moveTo(int index) {
		return moveTo(mParent, index);
	}

	public final boolean moveTo(LineGroup group, int index) {
		if (group == null) return false;

		if (group == mParent) {
			int currentIndex = mParent.mChildren.indexOf(this);
			if (index == currentIndex) return false;
			if (index > currentIndex) --index;
		}

		if (mParent != null) mParent.mChildren.remove(this);
		group.mChildren.add(index, this);
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public int getAbsoluteOffset() {
		// TODO Auto-generated method stub
		return 0;
	}

	/** The absolute line number of this line. The same as its line number in a file. */
	public final int getLineNumber() {
		LineGroup parent = mParent;
		while (parent != null) {
			//TODO
		}
	}



}

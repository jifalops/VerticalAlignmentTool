package com.verticalalignmenttool.elements;


public class Token extends AbstractElement {
//	public interface onTokenChangedListener {
//		void onTokenChanged(Token token);
//	}

//	private static final onTokenChangedListener sDummyListener = new onTokenChangedListener() {
//		@Override public void onTokenChanged(Token token) {}
//	};


//	protected onTokenChangedListener mListener;

	protected TokenGroup mParent;

	public Token() {
		this(1);
	}

	public Token(int offset) {
		super(offset);
		mParent = null;
//		mListener = sDummyListener;
	}

	public final TokenGroup getParent() {
		return mParent;
	}

//	public void setOnTokenChangedListener(onTokenChangedListener listener) {
//		mListener = listener;
//	}

	public final boolean moveTo(int index) {
		return moveTo(mParent, index);
	}

	public final boolean moveTo(TokenGroup group, int index) {
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

}

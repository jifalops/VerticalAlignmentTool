package com.verticalalignmenttool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public abstract class AbstractToken extends AbstractElement {
	protected TokenGroup mParent;

	public AbstractToken() {
		this(0);
	}

	public AbstractToken(int offset) {
		super(offset);
		mParent = null;
//		mParentLine = null;
	}

	public final TokenGroup getParent() {
		return mParent;
	}


	/**
	 * Get the highest TokenGroup this Line descends from.
	 * If there is no ancestor of the current Token, return null.
	 */
	public final TokenGroup getRoot() {
		TokenGroup parent = mParent;
		TokenGroup root = null;
		while (parent != null) {
			root = parent;
			parent = parent.mParent;
		}
		return root;
	}

	/**
	 * The number of ancestors this Line has.
	 */
	public final int getDepth() {
		int depth = 0;
		TokenGroup parent = mParent;
		while (parent != null) {
			++depth;
			parent = parent.mParent;
		}
		return depth;
	}

	/** Return a list of ancestors with the Root node as the first element. */
	public final List<TokenGroup> getAncestors() {
		List<TokenGroup> ancestors = new ArrayList<TokenGroup>();
		TokenGroup parent = mParent;
		while (parent != null) {
			ancestors.add(parent);
			parent = parent.mParent;
		}
		Collections.reverse(ancestors);
		return ancestors;
	}

	/**
	 * Get the index of this Token as a child of its parent.
	 * The same as calling getParent().indexOf(Token).
	 * Returns -1 if parent is null.
	 */
	public final int getIndex() {
		return mParent == null
				? -1
				: mParent.indexOf(this);
	}


	/** {@inheritDoc} */
	@Override
	public final int countCharactersBefore() {
		int offset = 0;
		AbstractToken child = this;
		TokenGroup parent = mParent;

		int index;
		while (parent != null) {
			index = parent.indexOf(child);
			for (int i = 0; i < index; ++i) {
				offset += parent.get(i).getTotalLength();
			}
			child = parent;
			parent = parent.mParent;
		}
		return offset;
	}

	/**
	 * This token's absolute index from the flattened root.
	 */
	public final int getAbsoluteIndex() {
		int index = 0;
		AbstractToken child = this;
		TokenGroup parent = mParent;

		int currentIndex;
		while (parent != null) {
			currentIndex = parent.indexOf(child);
			for (int i = 0; i < currentIndex; ++i) {
				child = parent.get(i);
				if (child instanceof TokenGroup) {
					index += ((TokenGroup) child).flatten().size();
				}
				else {
					++index;
				}
			}
			child = parent;
			parent = parent.mParent;
		}
		return index;
	}

	/**
	 * The length of the text within this Token, not including offsets.
	 * To get length with offsets, use getTotalLength().
	 */
	public final int length() {
		int length = 0;
		if (this instanceof TokenGroup) {
			List<AbstractToken> children = ((TokenGroup) this).mChildren;
			for (AbstractToken child : children) {
				length += child.length();
			}
		}
		else {
			length += ((Token) this).mText.length();
		}
		return length;
	}

	/**
	 * Get the total number of characters contained within this Token
	 * including offsets. To get length without offsets, use length();
	 */
	public final int getTotalLength() {
		int length = mOffset;
		if (this instanceof TokenGroup) {
			List<AbstractToken> children = ((TokenGroup) this).mChildren;
			for (AbstractToken child : children) {
				length += child.getTotalLength();
			}
		}
		else {
			length += ((Token) this).mText.length();
		}
		return length;
	}

}

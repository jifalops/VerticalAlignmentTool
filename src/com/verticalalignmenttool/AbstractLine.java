package com.verticalalignmenttool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public abstract class AbstractLine extends AbstractElement {
	protected LineGroup mParent;

	public AbstractLine() {
		this(0);
	}

	public AbstractLine(int offset) {
		super(offset);
		mParent = null;
	}

	public final LineGroup getParent() {
		return mParent;
	}

	/**
	 * Get the highest LineGroup this Line descends from.
	 * If there is no ancestor of the current Line, return null.
	 */
	public final LineGroup getRoot() {
		LineGroup parent = mParent;
		LineGroup root = null;
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
		LineGroup parent = mParent;
		while (parent != null) {
			++depth;
			parent = parent.mParent;
		}
		return depth;
	}

	/** Return a list of ancestors with the Root node as the first element. */
	public final List<LineGroup> getAncestors() {
		List<LineGroup> ancestors = new ArrayList<LineGroup>();
		LineGroup parent = mParent;
		while (parent != null) {
			ancestors.add(parent);
			parent = parent.mParent;
		}
		Collections.reverse(ancestors);
		return ancestors;
	}

	/**
	 * Get the index of this Line as a child of its parent.
	 * The same as calling getParent().indexOf(line).
	 * Returns -1 if parent is null.
	 */
	public final int getIndex() {
		return mParent == null
				? -1
				: mParent.indexOf(this);
	}


	/** {@inheritDoc} */
	@Override
	public final int getCharactersBefore() {
		int offset = 0;
		AbstractLine child = this;
		LineGroup parent = mParent;

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
	 * The absolute line number of the beginning of this line.
	 * The same as its line number in a file.
	 */
	public final int getLineNumber() {
		int lineNum = 1;
		AbstractLine child = this;
		LineGroup parent = mParent;

		int index;
		while (parent != null) {
			index = parent.indexOf(child);
			for (int i = 0; i < index; ++i) {
				child = parent.get(i);
				if (child instanceof LineGroup) {
					lineNum += ((LineGroup) child).flatten().size();
				}
				else {
					++lineNum;
				}
			}
			child = parent;
			parent = parent.mParent;
		}
		return lineNum;
	}


	// TODO make these two function abstract
	/**
	 * The length of the text within this line, not including offsets or newlines.
	 * To get length with offsets, use getTotalLength().
	 */
	public final int length() {
		int length = 0;
		if (this instanceof LineGroup) {
			List<AbstractLine> children = ((LineGroup) this).mChildren;
			for (AbstractLine child : children) {
				length += child.length();
			}
		}
		else {
			length += ((Line) this).mText.length();
		}
		return length;
	}

	/**
	 * Get the total number of characters contained within this line
	 * including offsets and newlines. To get length without offsets, use length();
	 */
	public final int getTotalLength() {
		int length = mOffset;
		if (this instanceof LineGroup) {
			List<AbstractLine> children = ((LineGroup) this).mChildren;
			for (AbstractLine child : children) {
				length += child.getTotalLength();
			}
		}
		else {
			length += ((Line) this).mText.length() + 1;
		}
		return length;
	}
}

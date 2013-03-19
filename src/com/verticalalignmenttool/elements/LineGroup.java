package com.verticalalignmenttool.elements;

import java.util.LinkedList;

/**
 * A group of lines that have the same left indent.
 * Works similarly to a LinkedList where each element can have a parent.
 */
public class LineGroup extends Line {
	protected final LinkedList<Line> mChildren;

	public LineGroup() {
		this(4);
	}

	public LineGroup(int offset) {
		super(offset);
		mChildren = new LinkedList<Line>();
	}

	public final LinkedList<Line> getChildren() {
		return mChildren;
	}

	/** Add a line to this group, removing it from its current group if possible. */
	public final boolean add(Line child) {
		return add(child, mChildren.size());
	}

	/** Add a line to this group, removing it from its current group if possible. */
	public final boolean add(Line child, int index) {
		if (child == null || index < 0) return false;

		if (child.mParent == this) {
			int currentIndex = mChildren.indexOf(child);
			if (index == currentIndex) return false;
			if (index > currentIndex) --index;
		}

		if (child.mParent != null) child.mParent.remove(child);
		mChildren.add(index, child);
		child.mParent = this;
		return true;
	}

	public final boolean remove(Line child) {
		if (mChildren.remove(child)) {
			child.mParent = null;
			return true;
		}
		return false;
	}

	public final Line remove(int index) {
		Line removed = mChildren.remove(index);
		removed.mParent = null;
		return removed;
	}

	/** Move a child within this group. */
	public final boolean move(int from, int to) {
		return move(mChildren.get(from), to);
	}

	/** Similar to add(), except child must be part of this group. */
	public final boolean move(Line child, int index) {
		if (child.mParent != this) return false;
		return add(child, index);
	}

	public final int indexOf(Line child) {
		return mChildren.indexOf(child);
	}

	public final int size() {
		return mChildren.size();
	}

	public final Line get(int index) {
		return mChildren.get(index);
	}

	public final boolean isEmpty() {
		return mChildren.isEmpty();
	}

	public final Line set(int index, Line child) {
		Line replaced = mChildren.set(index, child);
		child.mParent = this;
		if (replaced != null) replaced.mParent = null;
		return replaced;
	}
}

package com.verticalalignmenttool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * A group of lines that have the same left indent.
 * Works similarly to a LinkedList where each element can have a parent.
 */
public class LineGroup extends AbstractLine {
	protected final LinkedList<AbstractLine> mChildren;

	public LineGroup() {
		this(0);
	}

	public LineGroup(int offset) {
		super(offset);
		mChildren = new LinkedList<AbstractLine>();
	}

	public final LinkedList<AbstractLine> getChildren() {
		return mChildren;
	}

	/** Add a line to this group, removing it from its current group if possible. */
	public final boolean add(AbstractLine child) {
		return add(child, mChildren.size());
	}

	/** Add a line to this group, removing it from its current group if possible. */
	public final boolean add(AbstractLine child, int index) {
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

	public final boolean remove(AbstractLine child) {
		if (mChildren.remove(child)) {
			child.mParent = null;
			return true;
		}
		return false;
	}

	public final AbstractLine remove(int index) {
		AbstractLine removed = mChildren.remove(index);
		removed.mParent = null;
		return removed;
	}

	/** Move a child within this group. */
	public final boolean move(int from, int to) {
		return move(mChildren.get(from), to);
	}

	/** Similar to add(), except child must be part of this group already. */
	public final boolean move(AbstractLine child, int index) {
		if (child.mParent != this) return false;
		return add(child, index);
	}

	/** The index of the given child in this group, or -1 if not found. */
	public final int indexOf(AbstractLine child) {
		return mChildren.indexOf(child);
	}

	/** The number of direct children of this LineGroup. */
	public final int size() {
		return mChildren.size();
	}

	/**
	 * Get the contents of this group as a flat list of text lines.
	 */
	public final List<Line> flatten() {
		List<Line> lines = new ArrayList<Line>();
		for (AbstractLine child : mChildren) {
			if (child instanceof LineGroup) {
				lines.addAll(((LineGroup) child).flatten());
			}
			else {
				lines.add((Line) child);
			}
		}
		return lines;
	}

	/** Return the child at the given index. */
	public final AbstractLine get(int index) {
		return mChildren.get(index);
	}


	/**
	 * Get the line residing at the given character offset
	 * from the beginning of this line. Note if the offset
	 * falls within this line's relative offset, the current
	 * line will be returned. Returns null if charOffset is
	 * out of bounds.
	 * Binary search is used to find the resultant line.
	 */
	public final AbstractLine getLineAt(int charOffset) { // TODO (much later): this should find the Element at charOffset
		if (charOffset < 0) return null;
		if (charOffset < mOffset) return this;
		int lo = 0;
        int hi = mChildren.size() - 1;
        AbstractLine child;
        while (lo <= hi) {
            // Key is in lo..hi or not present.
            int mid = lo + (hi - lo) / 2;
            child = mChildren.get(mid);
            int offset = child.countCharactersBefore();
            if	(charOffset < offset) {
            	hi = mid - 1;
            }
            else if (charOffset > offset + child.getTotalLength()) {
            	lo = mid + 1;
            }
            else if (child instanceof LineGroup) {
            	return ((LineGroup) child).getLineAt(charOffset - offset);
            }
            else return child;
        }
        return null;
	}

	/** Determine if this LineGroup has no children. */
	public final boolean isEmpty() {
		return mChildren.isEmpty();
	}

	/** Over-write the child at index */
	public final AbstractLine set(int index, AbstractLine child) {
		AbstractLine replaced = mChildren.set(index, child);
		child.mParent = this;
		if (replaced != null) replaced.mParent = null;
		return replaced;
	}
}

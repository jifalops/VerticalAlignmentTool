package com.verticalalignmenttool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TokenGroup extends AbstractToken {
	protected LinkedList<AbstractToken> mChildren;

	public TokenGroup() {
		this(0);
	}

	public TokenGroup(int offset) {
		super(offset);
		mChildren = new LinkedList<AbstractToken>();
	}

	public final LinkedList<AbstractToken> getChildren() {
		return mChildren;
	}


	/** Add a Token to this group, removing it from its current group if possible. */
	public final boolean add(AbstractToken child) {
		return add(child, mChildren.size());
	}

	/** Add a Token to this group, removing it from its current group if possible. */
	public final boolean add(AbstractToken child, int index) {
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

	public final boolean remove(AbstractToken child) {
		if (mChildren.remove(child)) {
			child.mParent = null;
			return true;
		}
		return false;
	}

	public final AbstractToken remove(int index) {
		AbstractToken removed = mChildren.remove(index);
		removed.mParent = null;
		return removed;
	}

	/** Move a child within this group. */
	public final boolean move(int from, int to) {
		return move(mChildren.get(from), to);
	}

	/** Similar to add(), except child must be part of this group already. */
	public final boolean move(AbstractToken child, int index) {
		if (child.mParent != this) return false;
		return add(child, index);
	}

	/** The index of the given child in this group, or -1 if not found. */
	public final int indexOf(AbstractToken child) {
		return mChildren.indexOf(child);
	}

	/** The number of direct children of this TokenGroup. */
	public final int size() {
		return mChildren.size();
	}

	/**
	 * Get the contents of this group as a flat list of tokens.
	 */
	public final List<Token> flatten() {
		List<Token> tokens = new ArrayList<Token>();
		for (AbstractToken child : mChildren) {
			if (child instanceof TokenGroup) {
				tokens.addAll(((TokenGroup) child).flatten());
			}
			else {
				tokens.add((Token) child);
			}
		}
		return tokens;
	}

	/** Return the child at the given index. */
	public final AbstractToken get(int index) {
		return mChildren.get(index);
	}

	/** Determine if this TokenGroup has no children. */
	public final boolean isEmpty() {
		return mChildren.isEmpty();
	}

	/** Over-write the child at index */
	public final AbstractToken set(int index, AbstractToken child) {
		AbstractToken replaced = mChildren.set(index, child);
		child.mParent = this;
		if (replaced != null) replaced.mParent = null;
		return replaced;
	}
}

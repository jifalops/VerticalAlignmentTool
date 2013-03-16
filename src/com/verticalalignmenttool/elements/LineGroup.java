package com.verticalalignmenttool.elements;

import java.util.LinkedList;

public class LineGroup extends Line {
	protected LinkedList<Line> mChildren;

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
}

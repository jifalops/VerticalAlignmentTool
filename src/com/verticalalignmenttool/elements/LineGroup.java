package com.verticalalignmenttool.elements;

import java.util.LinkedList;

public class LineGroup extends Line {
	private static double sDefaultOffset = 4.0; // from parent

	private LinkedList<Line> mChildren;

	public LineGroup() {
		this(sDefaultOffset);
	}
	public LineGroup(double offset) {
		super(offset);
		mChildren = new LinkedList<Line>();
	}

	public LinkedList<Line> getChildren() {
		return mChildren;
	}

	public static void setDefaultOffset(double offset) {
		sDefaultOffset = offset;
	}

	public static double getDefaultOffset() {
		return sDefaultOffset;
	}
}

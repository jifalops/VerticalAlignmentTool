package com.verticalalignmenttool;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

public class AlignedDocument {
	protected final Line[] mLines;


	public AlignedDocument(File file) throws IOException {
		List<Line> lines = new ArrayList<Line>();
		try {
			LineNumberReader lnr = new LineNumberReader(new FileReader (file));
			String s = lnr.readLine();
			while (s != null) {
				lines.add(new Line(s));
				s = lnr.readLine();
			}
		}
		catch (IOException e) {
			mLines = null;
			throw e;
		}

		mLines = lines.toArray(new Line[lines.size()]);
	}

	public AlignedDocument(String multilineString) {
		this(multilineString.split("\\n"));
	}

	public AlignedDocument(String[] lines) {
		int len = lines.length;
		mLines = new Line[len];
		for (int i = 0; i < len; ++i) {
			mLines[i] = new Line(lines[i]);
		}
	}
}

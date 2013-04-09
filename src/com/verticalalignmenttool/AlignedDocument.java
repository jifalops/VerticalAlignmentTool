package com.verticalalignmenttool;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
// TODO whitespace can be removed as long as it's not between two consecutive keyword/identifier or two consecutive operators
// 1. comments/strings
// 2. whitespace
// 3. "words" (keyword/identifier), lists (alternating seperator/word or operator/word),
public class AlignedDocument {
	private final String mDoc;
	private final int[][] m


	public AlignedDocument(File aFile) throws IOException {
		mDoc = readFile(aFile);
	}

	public AlignedDocument(String multilineString) {
		mDoc = multilineString;
	}


	// http://nadeausoftware.com/articles/2008/02/java_tip_how_read_files_quickly
	// http://stackoverflow.com/questions/4716503/best-way-to-read-a-text-file
	// http://www.coderanch.com/t/403914/java/java/read-entire-file
	// http://www.coderanch.com/how-to/java/ReadDoesntDoWhatYouThinkItDoes
	private String readFile(File aFile) throws IOException {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		InputStream is = new FileInputStream(aFile);
		byte[] temp = new byte[1024 * 8];
		int read;

		while((read = is.read(temp)) >= 0){
		   buffer.write(temp, 0, read);
		}

		return new String(buffer.toByteArray());
	}
}

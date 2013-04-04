package samplecode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class SampleClass {
	public static final String CONSTANT_ONE = "some constant";
	private static final String SECRET = "uuddlrlrbabas";
	protected final float mVersion = 1.0f;
	protected int mCount;
	private String mInput;
	private String mOutput;
	int z;
	private String mFilename;
	public SampleClass() {
		mFilename = "test.txt";
	}
	public String read() throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter some text to be remembered, which can then be" +
				" written to a file as well as displayed in the default output stream."
				+ "by calling this class's write() method.");
		mInput = br.readLine();
		return mInput;
	}
	public void write(String text) throws IOException {
		write(text, 0, text.length() - 1,
				null, null);
	}
	public void write(String text, int start, int end) throws IOException {
		write(text, start, end,
				null, null);
	}
	public void write(String text, String search, String replace) throws IOException {
		write(text, 0, text.length() - 1,
				search, replace);
	}
	public void write(String text, int start, int end,
			String search, String replace) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(mFilename));
		try {
			text = text.substring(start, end);
			text = text.replaceAll(search, replace);
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
		}
		catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
		catch (Throwable ignored) {}
		bw.write(text);
		System.out.println(text);
	}
	public void setFilename(String filename) {
		mFilename = filename;
	}
}

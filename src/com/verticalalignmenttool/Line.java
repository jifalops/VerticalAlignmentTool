package com.verticalalignmenttool;

import java.util.ArrayList;
import java.util.List;

public final class Line {
	public final String text;
	public final Token[] tokens;

	// positive = excess open, negative = excess close
	public final int parens, sqareBrackets, curlyBraces;

	public final boolean endsInSemicolon, isComplete;

	public Line(String text) {
		this.text = text;
		tokens = tokenize();
		endsInSemicolon = endsInSemicolon();
		parens = countParens();
		sqareBrackets = countSquareBrackets();
		curlyBraces = countCurlyBraces();
		isComplete = endsInSemicolon && parens <= 0 && sqareBrackets <= 0 && curlyBraces <= 0;
	}

	private Token[] tokenize() {
		List<Token> tokens = new ArrayList<Token>();
		// TODO
		// 1. find comments & strings
		// 2. find identifier chains or
		//	  find operators or
		// 	  find (nonstring) literals or
		//    find keywords
		// 3. find separators
		//
		// come up with an efficient way to search parts of the string that haven't been identified
		//
		//
		return tokens.toArray(new Token[tokens.size()]);
	}

	private boolean endsInSemicolon() {
		for (int i = tokens.length - 1; i >= 0; --i) {
			if (tokens[i].type == Token.TYPE_SEPARATOR) {
					return text.charAt(tokens[i].start) == ';';
			}
			else if (tokens[i].type == Token.TYPE_COMMENT) {
				continue;
			}
			else {
				return false;
			}
		}
		return false;
	}

	private int countParens() {
		int count = 0;
		for (Token t : tokens) {
			if (t.type == Token.TYPE_SEPARATOR) {
				if (text.charAt(t.start) == '(') ++count;
				else if (text.charAt(t.start) == ')') --count;
			}
		}
		return count;
	}

	private int countSquareBrackets() {
		int count = 0;
		for (Token t : tokens) {
			if (t.type == Token.TYPE_SEPARATOR) {
				if (text.charAt(t.start) == '[') ++count;
				else if (text.charAt(t.start) == ']') --count;
			}
		}
		return count;
	}

	private int countCurlyBraces() {
		int count = 0;
		for (Token t : tokens) {
			if (t.type == Token.TYPE_SEPARATOR) {
				if (text.charAt(t.start) == '{') ++count;
				else if (text.charAt(t.start) == '}') --count;
			}
		}
		return count;
	}
}
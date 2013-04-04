package com.verticalalignmenttool;

import java.util.regex.Pattern;

// Contains items specific to Java

// http://docs.oracle.com/javase/specs/jls/se7/html/jls-3.html
// http://docs.oracle.com/javase/6/docs/api/java/util/regex/Pattern.html
// http://www.regular-expressions.info/lookaround.html

public class Token {
	public static final int TYPE_UNKNOWN     = 0;
	public static final int TYPE_WHITE_SPACE = 1;
	public static final int TYPE_COMMENT_SINGLE_LINE= 2;
	public static final int TYPE_COMMENT_MULTI_LINE	 = 3;
	public static final int TYPE_IDENTIFIER  = 4;
	public static final int TYPE_KEYWORD 	 = 5;
	public static final int TYPE_LITERAL_STRING = 6;
	public static final int TYPE_LITERAL_CHAR = 7;
	public static final int TYPE_LITERAL_BOOLEAN = 8;
	public static final int TYPE_LITERAL_NULL 	 = 9;
	public static final int TYPE_LITERAL_NUMBER	 = 10;
//	public static final int TYPE_LITERAL_INTEGER = 10;
//	public static final int TYPE_LITERAL_FLOAT = 11;
	public static final int TYPE_SEPARATOR 	 = 11;
	public static final int TYPE_BLOCK_START = 12;
	public static final int TYPE_BLOCK_END = 13;
	public static final int TYPE_OPERATOR 	 = 14;
	public static final int TYPE_COMMENT_OR_STRING = 15;
	public static final int TYPE_KEYWORD_MODIFIER_PREFIX 	 = 16;
	public static final int TYPE_KEYWORD_MODIFIER_POSTFIX 	 = 17;
	public static final int TYPE_KEYWORD_TYPE 	 = 18;
	public static final int TYPE_IDENTIFIER_CHAIN  = 19;
	public static final int TYPE_COMMENT = 20;


// TODO blocks, keyword subtypes (modifier, etc.), handle unclosed blocks
	// identifier chains (identifier.identifier...)


	public static final Pattern[] PATTERN;
	static {
		PATTERN = new Pattern[21];

		PATTERN[TYPE_UNKNOWN] = Pattern.compile(
				".*");

		PATTERN[TYPE_WHITE_SPACE] = Pattern.compile(
				"\\s+");

		PATTERN[TYPE_COMMENT_OR_STRING] = Pattern.compile(
				"//.*             					|" +	// Single line
				"/\\*.*?\\*/             			|" +	// Multi-line closed
				"/\\*.*(?:\\*/)?         			|" +	// Multi-line open
				"\".*?(?<!\\\\)((?:\\\\\\\\)*)\"",			// String
				Pattern.COMMENTS);

		PATTERN[TYPE_COMMENT] = Pattern.compile(
				"//.*             					|" +	// Single line
				"/\\*.*?\\*/             			|" +	// Multi-line closed
				"/\\*.*(?:\\*/)?         			 " ,	// Multi-line open
				Pattern.COMMENTS);

		PATTERN[TYPE_COMMENT_SINGLE_LINE] = Pattern.compile(
				"//.*");

		PATTERN[TYPE_COMMENT_MULTI_LINE] = Pattern.compile(
				"/\\*.*?\\*/", Pattern.DOTALL);

		PATTERN[TYPE_IDENTIFIER] = Pattern.compile(
				"(?<![A-Za-z0-9_$])[A-Za-z_$][A-Za-z0-9_$]*");

		PATTERN[TYPE_IDENTIFIER_CHAIN] = Pattern.compile(
				"(?<![A-Za-z0-9_$])(?:\\.?[A-Za-z_$][A-Za-z0-9_$]*)+");

		PATTERN[TYPE_KEYWORD] = Pattern.compile(
				"(?<![A-Za-z0-9_$])(?:" +
				"abstract  | continue | for        | new       | switch		  |" +
			    "assert    | default  | if         | package   | synchronized |" +
			    "boolean   | do       | goto       | private   | this		  |" +
			    "break     | double   | implements | protected | throw		  |" +
			    "byte      | else     | import     | public    | throws		  |" +
			    "case      | enum     | 			 return    | transient	  |" + 	// moved "instanceof" to operators
			    "catch     | extends  | int        | short     | try	   	  |" +
			    "char      | final    | interface  | static    | void		  |" +
			    "class     | finally  | long       | strictfp  | volatile	  |" +
			    "const     | float    | native     | super     | while"		     +
			    ")(?![A-Za-z0-9_$])", Pattern.COMMENTS);

		PATTERN[TYPE_KEYWORD_MODIFIER_PREFIX] = Pattern.compile(
				"(?<![A-Za-z0-9_$])(?:" +
				"public|protected|private|static|final|const|abstract|synchronized|volatile|native|strictfp|transient" +
				")(?![A-Za-z0-9_$])");

		PATTERN[TYPE_KEYWORD_MODIFIER_POSTFIX] = Pattern.compile(
				"(?<![A-Za-z0-9_$])(?:" +
				"extends|implements|throws" +
				")(?![A-Za-z0-9_$])");

		PATTERN[TYPE_KEYWORD_TYPE] = Pattern.compile(
				"(?<![A-Za-z0-9_$])(?:" +
				"boolean|byte|char|double|float|int|long|short|void|class|enum|interface" +
				")(?![A-Za-z0-9_$])");

		PATTERN[TYPE_LITERAL_STRING] = Pattern.compile(
				"\".*?(?<!\\\\)((?:\\\\\\\\)*)\"");

		PATTERN[TYPE_LITERAL_CHAR] = Pattern.compile(
				"'(?:[^'\\\\]|\\\\.+?)'");

		PATTERN[TYPE_LITERAL_BOOLEAN] = Pattern.compile(
				"(?<![A-Za-z0-9_$])(?:true|false)(?![A-Za-z0-9_$])");

		PATTERN[TYPE_LITERAL_NULL] = Pattern.compile(
				"(?<![A-Za-z0-9_$])null(?![A-Za-z0-9_$])");

		PATTERN[TYPE_LITERAL_NUMBER] = Pattern.compile(
				"(?<![A-Za-z0-9_$])(?:\\+|-|\\.)?[0-9].*?(?![0-9_A-FLPX.+-])", Pattern.CASE_INSENSITIVE);

//				PATTERN[TYPE_LITERAL_INTEGER] = Pattern.compile(
//				"(?:-?" +
//				"(?:(?:0|[1-9](?:_*[0-9])*)L?)		| "	+ // Decimal
//				"(?:0x[0-9A-F](?:_*[0-9A-F])*L?)	| "	+ // Hex
//				"(?:0(?:_*[0-7])*L?)				| "	+ // Octal
//				"(?:0B[01](?:_*[01])*L?)			| "	+ // Binary
//				")\\s", Pattern.COMMENTS | Pattern.CASE_INSENSITIVE);

		PATTERN[TYPE_SEPARATOR] = Pattern.compile(
				"[][(){};,.]");

		PATTERN[TYPE_BLOCK_START] = Pattern.compile(
				"[[({]");

		PATTERN[TYPE_BLOCK_END] = Pattern.compile(
				"[])}]");

		PATTERN[TYPE_OPERATOR] = Pattern.compile(
				"instanceof|=|>|<|!|~|\\?|:|" +
				"==|<=|>=|!=|&&|\\|\\||++|\\-\\-|" +
				"+|\\-|*|/|&|\\||^|%|<<|>>|>>>" +
				"+=|\\-=|*=|/=|&=|\\|=|^=|%=|<<=|>>=|>>>=");




	}


	public final int type, start, end, length;

	/**
	 * Represents information about a token found within a string.
	 * @param type - the type of this Token
	 * @param start - the offset in the string where this Token begins.
	 * @param end - the offset in the string after this Token ends.
	 * @param length - the length of this Token.
	 */
	public Token(int type, int start, int end) {
		this.type = type;
		this.start = start;
		this.end = end;
		this.length = start - end;
	}
}

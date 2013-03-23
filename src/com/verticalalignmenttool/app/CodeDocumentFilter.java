package com.verticalalignmenttool.app;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.StyledDocument;

import com.verticalalignmenttool.AlignedDocument;

public class CodeDocumentFilter extends DocumentFilter {
	private AlignedDocument mDocument;

	@Override
	public void insertString(FilterBypass fb, int offs, String str,
			AttributeSet a) throws BadLocationException {
		super.insertString(fb, offs, str, a);
		stretchTabstops((StyledDocument) fb.getDocument());
	}

	@Override
	public void remove(FilterBypass fb, int offs, int length)
			throws BadLocationException {
		super.remove(fb, offs, length);
		stretchTabstops((StyledDocument) fb.getDocument());
	}

	@Override
	public void replace(FilterBypass fb, int offs, int length, String str,
			AttributeSet a) throws BadLocationException {
		super.replace(fb, offs, length, str, a);
		stretchTabstops((StyledDocument) fb.getDocument());
	}
}

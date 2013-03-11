package com.verticalalignmenttool;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.FontMetrics;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.RootPaneContainer;
import javax.swing.UIManager;
import javax.swing.text.AbstractDocument;
import javax.swing.text.StyledDocument;

import com.verticalalignmenttool.util.DisplayMetrics;


/**
 * Code for this class (not the entire app) was taken from
 * {@link http://nickgravgaard.com/elastictabstops/}
 * with minor modifications.
 */
public class VerticalAlignmentTool extends JApplet {
	private static final long serialVersionUID = 20081005L;
	private static final int appWidth = 640;
	private static final int appHeight = 480;

	private JTextPane mTextPane;
	private RootPaneContainer mContainer;

	private DisplayMetrics mDisplayMetrics;


	public static void main(String[] args) {
		JFrame frame = new JFrame();
		final VerticalAlignmentTool app = new VerticalAlignmentTool(frame);

		app.initialize();

		frame.setTitle("Vertical Alignment Tool");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(appWidth, appHeight);
		frame.setVisible(true);

		app.start();
	}

    // Constructor used when run as an applet
    public VerticalAlignmentTool() {
        mContainer = this;
    }

    // Constructor used when run as an application
    public VerticalAlignmentTool(JFrame frame) {
    	mContainer = frame;
    }

	public void initialize() {
		mTextPane = new JTextPane();



		try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
		catch (Exception ignored) {}

		// layout main content panel
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(new JScrollPane(mTextPane), BorderLayout.CENTER);

		mContainer.setContentPane(panel);
		this.setSize(appWidth, appHeight);

		mDisplayMetrics = new DisplayMetrics(panel);




		mTextPane.setFont(new Font("Verdana", java.awt.Font.PLAIN, 12));
		StyledDocument styledDoc = mTextPane.getStyledDocument();
		AbstractDocument doc = (AbstractDocument) styledDoc;
		FontMetrics fm = mTextPane.getFontMetrics(mTextPane.getFont());
		doc.setDocumentFilter(new DocFilter(fm));
		String initialText = ""
			+ "/* Hopefully this Java program should demonstrate how elastic tabstops work.\t*/\n"
			+ "/* Try inserting and deleting different parts of the text and watch as the tabstops move.\t*/\n"
			+ "/* If you like this, please ask the writers of your text editor to implement it.\t*/\n"
			+ "\n"
			+ "#include <stdio.h>\n"
			+ "\n"
			+ "struct ipc_perm\n"
			+ "{\n"
			+ "\tkey_t\tkey;\n"
			+ "\tushort\tuid;\t/* owner euid and egid\t*/\n"
			+ "\tushort\tgid;\t/* group id\t*/\n"
			+ "\tushort\tcuid;\t/* creator euid and egid\t*/\n"
			+ "\tcell-missing\t\t/* for test purposes\t*/\n"
			+ "\tushort\tmode;\t/* access modes\t*/\n"
			+ "\tushort\tseq;\t/* sequence number\t*/\n"
			+ "};\n"
			+ "\n"
			+ "int someDemoCode(\tint fred,\n"
			+ "\tint wilma)\n"
			+ "{\n"
			+ "\tx();\t/* try making\t*/\n"
			+ "\tprintf(\"hello!\\n\");\t/* this comment\t*/\n"
			+ "\tdoSomethingComplicated();\t/* a bit longer\t*/\n"
			+ "\tfor (i = start; i < end; ++i)\n"
			+ "\t{\n"
			+ "\t\tif (isPrime(i))\n"
			+ "\t\t{\n"
			+ "\t\t\t++numPrimes;\n"
			+ "\t\t}\n"
			+ "\t}\n"
			+ "\treturn numPrimes;\n"
			+ "}\n"
			+ "\n"
			+ "---- and now for something completely different: a table ----\n"
			+ "\n"
			+ "Title\tAuthor\tPublisher\tYear\n"
			+ "Generation X\tDouglas Coupland\tAbacus\t1995\n"
			+ "Informagic\tJean-Pierre Petit\tJohn Murray Ltd\t1982\n"
			+ "The Cyberiad\tStanislaw Lem\tHarcourt Publishers Ltd\t1985\n"
			+ "The Selfish Gene\tRichard Dawkins\tOxford University Press\t2006\n"
			+ "";

		mTextPane.setText(initialText);
	}
}

package com.verticalalignmenttool.util;

import java.awt.AWTError;
import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;


// Some metrics ideas were taken from
// http://stackoverflow.com/questions/3680221/screen-resolution-java
// http://stackoverflow.com/questions/2234476/how-to-detect-the-current-display-with-java
// http://docs.oracle.com/javase/1.5.0/docs/api/java/awt/Window.html
// http://docs.oracle.com/javase/7/docs/api/java/awt/GraphicsConfiguration.html#getNormalizingTransform()
public class DisplayMetrics {
	private static final String LOG_TAG = DisplayMetrics.class.getSimpleName();

	public static final double MILLIMETERS_PER_INCH = 25.4;

	public static final int INVALID = -1;
	public static final int INVALID_DIMENSION = Integer.MIN_VALUE;

	private static Toolkit			   sToolkit;
	private static GraphicsEnvironment sGraphicsEnvironment;
	private static GraphicsDevice[]    sDevices;
	private static GraphicsDevice      sDefaultDevice;

	private  	   GraphicsDevice	   mCurrentDevice;
	private  	   Component	  	   mReferenceComponent;
	private		   FontMetrics		   mFontMetrics;


	public DisplayMetrics(Component component) {
		setComponent(component);
	}

	public void setComponent(Component component) {
		mReferenceComponent = component;
		mCurrentDevice = component.getGraphicsConfiguration().getDevice();

		/** Attempts to get FontMetrics in which 72 points = 1 inch */
		Graphics2D g = (Graphics2D) mReferenceComponent.getGraphics();
		GraphicsConfiguration gc = g.getDeviceConfiguration();

		g.setTransform(gc.getDefaultTransform());
	    g.transform(gc.getNormalizingTransform());

	    mFontMetrics = g.getFontMetrics();
	}


	/**
	 * Updates the static fields of this class.
	 * Possibly needs to be called if screens are added/dropped or resolution changes.
	 * Returns true if any static objects changed.
	 */
	public static boolean scanForChanges() {
		boolean result = false;

		Toolkit tk = null;
		try 			   { tk = Toolkit.getDefaultToolkit(); }
		catch (AWTError e) { Log.e(LOG_TAG, e.getMessage()); }
		if (tk != sToolkit) result = true;
		sToolkit = tk;

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		if (ge != sGraphicsEnvironment) result = true;
		sGraphicsEnvironment = ge;

		// TODO ensure devices are returned in the same order.
		GraphicsDevice[] devices = sGraphicsEnvironment.getScreenDevices();
		for (int i = 0; i < devices.length; ++i) {
			if (devices[i] != sDevices[i]) result = true;
		}
		sDevices = devices;

		GraphicsDevice defaultDevice = sGraphicsEnvironment.getDefaultScreenDevice();
		if (defaultDevice != sDefaultDevice) result = true;
		sDefaultDevice = defaultDevice;

		return result;
	}

	public static int getNumDevices() {
		return sDevices.length;
	}

//	public static GraphicsDevice getDevice(int index) {
//		return mDevices[index];
//	}
//
//	public int getDeviceIndex(GraphicsDevice device) {
//		int index = INVALID;
//		for (int i = 0; i < mDevices.length; ++i) {
//			if (mDevices[i] == device) {
//				index = i;
//				break;
//			}
//		}
//		return index;
//	}

	public GraphicsDevice getCurrentDevice() {
		return mCurrentDevice;
	}

	public static GraphicsDevice getDefaultDevice() {
		return sDefaultDevice;
	}

	public boolean isCurrentDevice(GraphicsDevice device) {
		return device == mCurrentDevice;
	}

	public static boolean isDefaultDevice(GraphicsDevice device) {
		return device == sDefaultDevice;
	}

	/** Width of screen in pixels */
	public int getScreenWidth() {
		return getScreenWidth(mCurrentDevice);
	}

	/** Width of screen in pixels */
	public static int getScreenWidth(GraphicsDevice device) {
		return device.getDisplayMode().getWidth();
	}

	/** Height of screen in pixels */
	public int getScreenHeight() {
		return getScreenHeight(mCurrentDevice);
	}

	/** Height of screen in pixels */
	public static int getScreenHeight(GraphicsDevice device) {
		return device.getDisplayMode().getHeight();
	}

	/** Convert to relative coordinates of the current screen. */
	public Point toRelative(Point point) {
		return toRelative(point, mCurrentDevice.getDefaultConfiguration());
	}

	/** Convert to relative coordinates of the current screen. */
	public static Point toRelative(Point point, GraphicsConfiguration gc) {
		Rectangle bounds = gc.getBounds();
		return new Point(point.x + bounds.x, point.y + bounds.y);
	}

	/** Convert to absolute coordinates of the current system. */
	public Point toAbsolute(Point point) {
		return toAbsolute(point, mCurrentDevice.getDefaultConfiguration());
	}

	/** Convert to absolute coordinates of the current system. */
	public static Point toAbsolute(Point point, GraphicsConfiguration gc) {
		Rectangle bounds = gc.getBounds();
		return new Point(point.x - bounds.x, point.y - bounds.y);
	}


	public static int getScreenWidthDpi() {
		// Screen width in dpi. Measurement is shared between all monitors according to
		// http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4074958
		return sToolkit.getScreenResolution();
	}

//	/**
//	 * Platform dependent.
//	 */
//	public static int getScreenWidthDpi2() {
//		// http://www.java2s.com/Code/JavaAPI/org.eclipse.swt.graphics/DevicegetDPI.htm
//		return ((Device) new Display()).getDPI();
//	}


	public double getScreenWidthMm() {
		return getScreenWidthMm(mCurrentDevice);
	}

	public static double getScreenWidthMm(GraphicsDevice device) {
		// Screen width in dpi. Measurement is shared between all monitors according to
		// http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4074958
		int dpi = getScreenWidthDpi();
		int width = getScreenWidth(device);
		return width / dpi * MILLIMETERS_PER_INCH;
	}



	/** Attempts to get FontMetrics in which 72 points = 1 inch */
	public FontMetrics getFontMetrics() {
		return mFontMetrics;
	}
}

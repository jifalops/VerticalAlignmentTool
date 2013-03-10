package com.verticalalignmenttool;

import java.awt.AWTError;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.Window;

// TODO make things dependent on the display configuration (count, resolution)
// static (or in another class) and figure out how to update them when necessary

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

	private final Toolkit			  mToolkit;
	private final GraphicsEnvironment mGraphicsEnvironment;
	private final GraphicsDevice[]    mDevices;
	private final GraphicsDevice      mDefaultDevice;
	private  	  GraphicsDevice	  mCurrentDevice;
	private  	  Window	  		  mWindow;

	/**
	 * Window is not strictly necessary initially.
	 * If passing null, be sure to call setWindow(window)
	 * before checking most metrics.
	 */
	public DisplayMetrics(Window window) {
		if (window != null) setWindow(window);

		Toolkit tk = null;
		try 			   { tk = Toolkit.getDefaultToolkit(); }
		catch (AWTError e) { Log.e(LOG_TAG, e.getMessage()); }
		mToolkit = tk;

		mGraphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		if (mGraphicsEnvironment == null) Log.e(LOG_TAG, "No graphics environment available");

		mDevices = mGraphicsEnvironment.getScreenDevices();
		mDefaultDevice = mGraphicsEnvironment.getDefaultScreenDevice();
	}

	public int getNumDevices() {
		return mDevices.length;
	}

	public void setWindow(Window window) {
		mWindow = window;
		mCurrentDevice = getDevice(window);
	}

	public GraphicsDevice getDevice(Window window) {
		return window.getGraphicsConfiguration().getDevice();
	}

	public GraphicsDevice getDevice(int index) {
		return mDevices[index];
	}

	public int getDeviceIndex(GraphicsDevice device) {
		int index = INVALID;
		for (int i = 0; i < mDevices.length; ++i) {
			if (mDevices[i] == device) {
				index = i;
				break;
			}
		}
		return index;
	}

	public GraphicsDevice getCurrentDevice() {
		return mCurrentDevice;
	}

	public GraphicsDevice getDefaultDevice() {
		return mDefaultDevice;
	}

	public boolean isCurrentDevice(GraphicsDevice device) {
		return device == mCurrentDevice;
	}

	public boolean isDefaultDevice(GraphicsDevice device) {
		return device == mDefaultDevice;
	}

	/** Width of screen in pixels */
	public int getScreenWidth() {
		return getScreenWidth(mCurrentDevice);
	}

	/** Width of screen in pixels */
	public int getScreenWidth(GraphicsDevice device) {
		return device.getDisplayMode().getWidth();
	}

	/** Height of screen in pixels */
	public int getScreenHeight() {
		return getScreenHeight(mCurrentDevice);
	}

	/** Height of screen in pixels */
	public int getScreenHeight(GraphicsDevice device) {
		return device.getDisplayMode().getHeight();
	}

	// TODO convert to/from absolute and relative coordinates.
	// absolute = all monitors, relative = current monitor.

	/*
	 * Attempts at getting physical size. Need to be tested.
	 */

	/** Attempts to get FontMetrics in which 72 points = 1 inch */
	public FontMetrics getFontMetrics() {
		Graphics2D g = (Graphics2D) mWindow.getGraphics();
		GraphicsConfiguration gc = g.getDeviceConfiguration();

		g.setTransform(gc.getDefaultTransform());
	    g.transform(gc.getNormalizingTransform());

	    return g.getFontMetrics();
	}

	/**
	 * Attempts to get the width of the screen in millimeters.
	 * Assumes multiple screens share the same DPI.
	 */
	public double getScreenWidthMm() {
		// Screen width in dpi. Measurement is shared between all monitors according to
		// http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4074958
		int dpi = mToolkit.getScreenResolution();
		int width = getScreenWidth();
		return width / dpi * MILLIMETERS_PER_INCH;
	}

//	/**
//	 * Platform dependent.
//	 */
//	public double getScreenWidthMm2() {
//		// http://www.java2s.com/Code/JavaAPI/org.eclipse.swt.graphics/DevicegetDPI.htm
//		return ((Device) new Display()).getDPI();
//	}
}

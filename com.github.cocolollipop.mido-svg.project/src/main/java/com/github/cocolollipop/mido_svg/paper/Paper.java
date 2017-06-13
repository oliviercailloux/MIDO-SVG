package com.github.cocolollipop.mido_svg.paper;

/**
 * This class corresponds to the settings of a paper
 *
 */
public class Paper {

	private int dimXCanvas;
	private int dimYCanvas;
	private int canevasX;
	private int canevasY;
	private Enum format;

	/**
	 * Current possibilities for format's settings
	 */
	public enum TypeFormat {
		A4, A3, Other

	}

	// CONSTRUCTORS
	public Paper() {
		this.dimYCanvas = 0;
		this.dimXCanvas = 0;
		this.canevasX = 0;
		this.canevasY = 0;

	}

	public Paper(int x, int y) {
		this.setDimXCanvas(x);
		this.setDimYCanvas(y);
		this.setCanevasX(x);
		this.setCanevasY(y);
	}

	public Paper(Enum settings, int x, int y) {
		Paper newPaper = null;
		if (settings == Paper.TypeFormat.A3) {
			newPaper = new PaperA3();
		} else if (settings == Paper.TypeFormat.A4) {
			newPaper = new PaperA4();
		} else if (settings == Paper.TypeFormat.Other) {
			newPaper = new PaperOther(x, y);

		}
	}
	// GETTERS

	public int getCanevasX() {
		return canevasX;
	}

	public int getCanevasY() {
		return canevasY;
	}

	// SETTERS

	public void setCanevasX(int canevasX) {
		this.canevasX = canevasX;
	}

	public void setCanevasY(int canevasY) {
		this.canevasY = canevasY;
	}

	public Enum getFormat() {
		return format;
	}

	public void setFormat(Enum format) {
		this.format = format;
	}

	public int getDimXCanvas() {
		return dimXCanvas;
	}

	public void setDimXCanvas(int dimXCanvas) {
		this.dimXCanvas = dimXCanvas;
	}

	public int getDimYCanvas() {
		return dimYCanvas;
	}

	public void setDimYCanvas(int dimYCanvas) {
		this.dimYCanvas = dimYCanvas;
	}
}

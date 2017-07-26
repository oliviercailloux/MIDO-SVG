package com.github.cocolollipop.mido_svg.paper;

/**
 * This class corresponds to the settings of a paper
 *
 */
public class Paper {

	private int dimXCanvas;

	private int dimYCanvas;

	public Paper() {
		this.dimYCanvas = 0;
		this.dimXCanvas = 0;

	}

	public Paper(int x, int y) {
		this.setDimXCanvas(x);
		this.setDimYCanvas(y);

	}

	public int getDimXCanvas() {
		return dimXCanvas;
	}

	public int getDimYCanvas() {
		return dimYCanvas;
	}

	public void setDimXCanvas(int dimXCanvas) {
		this.dimXCanvas = dimXCanvas;
	}

	public void setDimYCanvas(int dimYCanvas) {
		this.dimYCanvas = dimYCanvas;
	}
}

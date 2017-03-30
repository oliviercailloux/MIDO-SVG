package com.github.cocolollipop.univ;

/**
 * This class is corresponding to the subject students could attend
 *
 */
public class Subject {

	private String title;
	private Teacher responsible;
	private int credit;
	private int posX;
	private int posY;

	public int getPosX() {
		return posX;
	}

	public void setPosX(int x) {
		this.posX = x;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int y) {
		this.posY = y;
	}
}

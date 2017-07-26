package com.github.cocolollipop.mido_svg.university.components;

/**
 * A Master is a french degree, equivalent to Master's degree.
 * 
 * @author Romano
 *
 */
public class Master extends Formation {

	/**
	 * 
	 * @param name
	 *            is the name of the licence
	 * @param i
	 *            is the year : 4 or 5. Example M1 -> 4, M2 -> 5
	 * @param x
	 *            is is the abscissa position you want to set
	 * @param y
	 *            is the intercept position you want to set
	 */
	public Master(String name, int i, int x, int y) {
		super(name, i, x, y);
		this.category = Category.MASTER;

	}

	/**
	 * 
	 * @param name
	 *            is the name of the licence
	 * @param i
	 *            is the year : 4 or 5. Example M1 -> 4, M2 -> 5
	 */
	public Master(String name, int i) {
		super(name, i, 0, 0);
		this.category = Category.MASTER;

	}

}

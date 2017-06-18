package com.github.cocolollipop.mido_svg.university.components;

/**
 * A licence is a french degree, equivaloent to Bachelor's degree.
 * 
 * @author Romano
 *
 */
public class Licence extends Formation {

	/**
	 * 
	 * @param string
	 *            is the name of the licence
	 * @param k
	 *            is the year : 1 2 or 3. Example L1 -> 1, L3 -> 3
	 * @param posX
	 *            is the abscissa position you want to set
	 * @param posY
	 *            is the intercept position you want to set
	 */
	public Licence(String string, int k, int posX, int posY) {
		super(string, k, posX, posY);
		this.category = Category.LICENCE;
	}

	public Licence(String string, int k) {
		super(string, k, 0, 0);
		this.category = Category.LICENCE;
	}

}

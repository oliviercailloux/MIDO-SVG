package com.github.cocolollipop.mido_svg.paper;

import com.github.cocolollipop.mido_svg.svg_generator.TypeFormat;

public class Format {

	Format type;
	private int dimXCanvas = 1920;
	private int dimYCanvas = 1080;
	private int canevasX = 0;
	private int canevasY = 0;

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

	/**
	 * changeFormat method change the format of the canevas to A3 or A4 and
	 * throws an exception if it's neither A4 nor A3
	 * 
	 * @param settings.
	 *            would be equal to A3/a3 or A4/a4
	 * @throws Exception
	 */

	public void changeFormat(Enum settings) {

		if (settings == TypeFormat.A3 || settings == TypeFormat.A4) {
			setCanevasX(2480);
			setCanevasY(3508);
		}

		else if (settings == TypeFormat.A4 || settings == TypeFormat.A3) {
			setCanevasX(3508); // 3508
			setCanevasY(4961); // 4961
		}
		// else throw new Exception("This size isn't availaible, please choose
		// between A3 or A4");
	}

}

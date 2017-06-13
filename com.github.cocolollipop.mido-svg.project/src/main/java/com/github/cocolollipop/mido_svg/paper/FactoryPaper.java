package com.github.cocolollipop.mido_svg.paper;

/**
 * This class is used to create different format of paper. It's based on the
 * pattern factory
 * 
 * @author Cocolollipop
 *
 */
public class FactoryPaper {

	/**
	 * Current possibilities for format's settings
	 */
	private Enum format;

	public enum TypeFormat {
		A4, A3, Other

	}

	public FactoryPaper() {

	}

	public Paper getPaper(Enum settings) {
		if (settings == TypeFormat.A3) {
			return new PaperA3();
		} else if (settings == TypeFormat.A4) {
			return new PaperA4();
		}
		return null;
	}

	public Paper getPaper(Enum settings, int x, int y) {
		if (settings == TypeFormat.Other) {
			return new PaperOther(x, y);
		}

		return null;

	}

}

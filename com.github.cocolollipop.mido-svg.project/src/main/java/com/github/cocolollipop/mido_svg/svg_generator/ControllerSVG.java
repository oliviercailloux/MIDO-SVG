package com.github.cocolollipop.mido_svg.svg_generator;

import com.github.cocolollipop.mido_svg.model.DataBase;

/**
 * Main Controller
 *
 */

public class ControllerSVG {

	public static void main(String[] args) throws Exception {
		/** We initialize the drawer, the settings and the datas */
		DrawerSVGGen test = new DrawerSVGGen();
		Settings settings = new Settings(false, false, false, false, false, false, false, "A3");
		// Settings settings = new Settings(false, false, false, false, false,
		// false, false, 1000, 1000);

		DataBase datas = new DataBase();
		/** We adapt the drawing according to the settings */
		ResponsiveSVG responsive = new ResponsiveSVG();
		responsive.defineObjectsPosition(datas.getFormations(), 1920, 1080);

		/** We paint the result */
		test.paint(settings, datas);
	}

}

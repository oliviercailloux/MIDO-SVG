package com.github.cocolollipop.mido_svg.svg_generator;

import com.github.cocolollipop.mido_svg.model.DataBase;

public class ManagerSVG {

	public static void main(String[] args) throws Exception {
		DrawerSVGGen test = new DrawerSVGGen();
		Settings settings = new Settings(false, true, true, true, true, true, "A3");
		DataBase datas = new DataBase();
		ResponsiveSVG responsive = new ResponsiveSVG();
		responsive.defineObjectsPosition(datas.getFormations(), 1920, 1080);
		test.paint(settings, datas);
	}

}

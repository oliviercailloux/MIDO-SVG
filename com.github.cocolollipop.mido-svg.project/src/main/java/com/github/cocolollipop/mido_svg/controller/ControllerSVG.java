package com.github.cocolollipop.mido_svg.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.github.cocolollipop.mido_svg.model.DataBase;
import com.github.cocolollipop.mido_svg.svg_generator.DrawerSVGGen;
import com.github.cocolollipop.mido_svg.svg_generator.ResponsiveSVG;
import com.github.cocolollipop.mido_svg.svg_generator.Settings;
import com.github.cocolollipop.mido_svg.university.components.Subject;
import com.github.cocolollipop.mido_svg.xml.jaxb.model.Tag;

/**
 * Main Controller
 *
 */
public class ControllerSVG {

	public static void main(String[] args) throws Exception {
		ControllerJAXB jaxb = new ControllerJAXB();

		/** We initialize the drawer, the settings and the datas */
		DrawerSVGGen test = new DrawerSVGGen();
		
		Settings settings = new Settings(false, false, false, false, false, false, false, "A3");

		/** Tags **/
		jaxb.createTagsFileXML();
		List<Tag> listOfTags = jaxb.readTagsFileXML();
		/** we create the DataBase **/
		DataBase datas = new DataBase(settings);
		/** we add the tags **/
		datas.setTags(listOfTags);
		/** We adapt the drawing according to the settings */
		ResponsiveSVG responsive = new ResponsiveSVG();
		responsive.defineObjectsPosition(datas.getFormations(), settings.getWidth(), settings.getHeight());

		/** Just to print results **/
		Map<String, Subject> map = datas.getSubjects();
		for (String name : map.keySet()) {

			String key = name.toString();
			String value = map.get(name).getTitle();
			System.out.println("Liste de tags pour " + key + " :" + value);
			Set<Tag> tags = map.get(name).getTags();
			for (Tag tag : tags) {
				System.out.println(tag.getName());
			}
		}
		/** We paint the result */
		test.paint(settings, datas);
	}

}

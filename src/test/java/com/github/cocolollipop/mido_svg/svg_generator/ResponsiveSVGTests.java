package com.github.cocolollipop.mido_svg.svg_generator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import com.github.cocolollipop.mido_svg.university.components.Formation;

import java.awt.Point;
import java.util.List;

import com.github.cocolollipop.mido_svg.model.DataBase;

public class ResponsiveSVGTests {
	 
	/**
	 * Tests that an SVG in A4 format is responsive by calculating
	 * the objects position by hand 
	 *  
	 * @result the position calculated by hand and verified by Google Inspection 
	 * in the browser should be the same as the stored position of the object
	 */
	@Test
	public void testFormatA4WithAll(){
		Settings settings = new Settings(false, false, false, false, false, false, true, 1052, 744);
		TestsDatabase datas = new TestsDatabase(settings);
		List<Formation> formations = datas.getFormations();

		ResponsiveSVG responsive = new ResponsiveSVG();
		responsive.defineObjectsPosition(formations, settings.getWidth(), settings.getHeight(), settings.isHiddenSubject(), settings.isHiddenLicence(), settings.isHiddenMaster());

		assertEquals(formations.get(0).getFullName(),"L3 MIAGE");
		assertEquals(formations.get(2).getFullName(),"M1 MIAGE");
		assertEquals(formations.get(0).getPoint(), new Point(243,74));
		assertEquals(formations.get(1).getPoint(), new Point(634,74));
		assertEquals(formations.get(2).getPoint(), new Point(293,283));
		assertEquals(formations.get(3).getPoint(), new Point(653,283));
		assertEquals(formations.get(4).getPoint(), new Point(91,466));
		assertEquals(formations.get(5).getPoint(), new Point(224,466));
		assertEquals(formations.get(6).getPoint(), new Point(359,466));
		assertEquals(formations.get(7).getPoint(), new Point(508,466));
		assertEquals(formations.get(8).getPoint(), new Point(665,466));
		assertEquals(formations.get(9).getPoint(), new Point(824,466));
	}

	/**
	 * Tests that an SVG in A4 format without Licence is responsive by calculating
	 * the objects position by hand 
	 *  
	 * @result the position calculated by hand and verified by Google Inspection 
	 * in the browser should be the same as the stored position of the object
	 */
	@Test
	public void testFormatA4WithoutLicence(){
		Settings settings = new Settings(true, false, false, false, false, false, true, 1052, 744);
		TestsDatabase datas = new TestsDatabase(settings);
		List<Formation> formations = datas.getFormations();

		ResponsiveSVG responsive = new ResponsiveSVG();
		responsive.defineObjectsPosition(formations, settings.getWidth(), settings.getHeight(), settings.isHiddenSubject(), settings.isHiddenLicence(), settings.isHiddenMaster());

		assertEquals(formations.get(2).getPoint(), new Point(293,74));
		assertEquals(formations.get(3).getPoint(), new Point(653,74));
		assertEquals(formations.get(4).getPoint(), new Point(91,327));
		assertEquals(formations.get(5).getPoint(), new Point(224,327));
		assertEquals(formations.get(6).getPoint(), new Point(359,327));
		assertEquals(formations.get(7).getPoint(), new Point(508,327));
		assertEquals(formations.get(8).getPoint(), new Point(665,327));
		assertEquals(formations.get(9).getPoint(), new Point(824,327));
	}

	/**
	 * Tests that an SVG in A4 format without Master and Courses is responsive by calculating
	 * the objects position by hand 
	 *  
	 * @result the position calculated by hand and verified by Google Inspection 
	 * in the browser should be the same as the stored position of the object
	 */
	@Test
	public void testFormatA4WithoutMasterAndCourses(){
		Settings settings = new Settings(false, true, false, false, true, false, true, 1052, 744);
		TestsDatabase datas = new TestsDatabase(settings);
		List<Formation> formations = datas.getFormations();

		ResponsiveSVG responsive = new ResponsiveSVG();
		responsive.defineObjectsPosition(formations, settings.getWidth(), settings.getHeight(), settings.isHiddenSubject(), settings.isHiddenLicence(), settings.isHiddenMaster());

		assertEquals(formations.get(0).getPoint(), new Point(308,74));
		assertEquals(formations.get(1).getPoint(), new Point(640,74));
	}

	/**
	 * Tests that an SVG in A4 format with only Formations is responsive by calculating
	 * the objects position by hand 
	 *  
	 * @result the position calculated by hand and verified by Google Inspection 
	 * in the browser should be the same as the stored position of the object
	 */
	@Test
	public void testFormatA4WithOnlyFormations(){
		Settings settings = new Settings(false, false, true, true, true, true, true, 1052, 744);
		TestsDatabase datas = new TestsDatabase(settings);
		List<Formation> formations = datas.getFormations();

		ResponsiveSVG responsive = new ResponsiveSVG();
		responsive.defineObjectsPosition(formations, settings.getWidth(), settings.getHeight(), settings.isHiddenSubject(), settings.isHiddenLicence(), settings.isHiddenMaster());


		assertEquals(formations.get(0).getPoint(), new Point(308,74));
		assertEquals(formations.get(1).getPoint(), new Point(640,74));
		assertEquals(formations.get(2).getPoint(), new Point(306,260));
		assertEquals(formations.get(3).getPoint(), new Point(640,260));
		assertEquals(formations.get(4).getPoint(), new Point(91,446));
		assertEquals(formations.get(5).getPoint(), new Point(224,446));
		assertEquals(formations.get(6).getPoint(), new Point(359,446));
		assertEquals(formations.get(7).getPoint(), new Point(508,446));
		assertEquals(formations.get(8).getPoint(), new Point(665,446));
		assertEquals(formations.get(9).getPoint(), new Point(824,446));
	}
}

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
	 * The test allow an uncertainty for the position because GitHub seems to make different rounded
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
		assertEquals(formations.get(0).getPoint().getX(), 243, 5);
		assertEquals(formations.get(1).getPoint().getX(), 634, 5);
		assertEquals(formations.get(2).getPoint().getX(), 293, 5);
		assertEquals(formations.get(3).getPoint().getX(), 653, 5);
		assertEquals(formations.get(4).getPoint().getX(), 91, 5);
		assertEquals(formations.get(5).getPoint().getX(), 224, 5);
		assertEquals(formations.get(6).getPoint().getX(), 359, 5);
		assertEquals(formations.get(7).getPoint().getX(), 508, 5);
		assertEquals(formations.get(8).getPoint().getX(), 665, 5);
		assertEquals(formations.get(9).getPoint().getX(), 824, 5);
		
		assertEquals(formations.get(0).getPoint().getY(), 74, 5);
		assertEquals(formations.get(1).getPoint().getY(), 74, 5);
		assertEquals(formations.get(2).getPoint().getY(), 283, 5);
		assertEquals(formations.get(3).getPoint().getY(), 283, 5);
		assertEquals(formations.get(4).getPoint().getY(), 466, 5);
		assertEquals(formations.get(5).getPoint().getY(), 466, 5);
		assertEquals(formations.get(6).getPoint().getY(), 466, 5);
		assertEquals(formations.get(7).getPoint().getY(), 466, 5);
		assertEquals(formations.get(8).getPoint().getY(), 466, 5);
		assertEquals(formations.get(9).getPoint().getY(), 466, 5);
	}

	/**
	 * Tests that an SVG in A4 format without Licence is responsive by calculating
	 * the objects position by hand 
	 * 
	 * The test allow an uncertainty for the position because GitHub seems to make different rounded
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

		assertEquals(formations.get(2).getPoint().getX(), 293, 5);
		assertEquals(formations.get(3).getPoint().getX(), 653, 5);
		assertEquals(formations.get(4).getPoint().getX(), 91, 5);
		assertEquals(formations.get(5).getPoint().getX(), 224, 5);
		assertEquals(formations.get(6).getPoint().getX(), 359, 5);
		assertEquals(formations.get(7).getPoint().getX(), 508, 5);
		assertEquals(formations.get(8).getPoint().getX(), 665, 5);
		assertEquals(formations.get(9).getPoint().getX(), 824, 5);
		
		assertEquals(formations.get(2).getPoint().getY(), 74, 5);
		assertEquals(formations.get(3).getPoint().getY(), 74, 5);
		assertEquals(formations.get(4).getPoint().getY(), 327, 5);
		assertEquals(formations.get(5).getPoint().getY(), 327, 5);
		assertEquals(formations.get(6).getPoint().getY(), 327, 5);
		assertEquals(formations.get(7).getPoint().getY(), 327, 5);
		assertEquals(formations.get(8).getPoint().getY(), 327, 5);
		assertEquals(formations.get(9).getPoint().getY(), 327, 5);
	}

	/**
	 * Tests that an SVG in A4 format without Master and Courses is responsive by calculating
	 * the objects position by hand 
	 * 
	 * The test allow an uncertainty for the position because GitHub seems to make different rounded
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

		assertEquals(formations.get(0).getPoint().getX(), 308, 5);
		assertEquals(formations.get(1).getPoint().getX(), 640, 5);
		
		assertEquals(formations.get(0).getPoint().getY(), 74, 5);
		assertEquals(formations.get(1).getPoint().getY(), 74, 5);
	}

	/**
	 * Tests that an SVG in A4 format with only Formations is responsive by calculating
	 * the objects position by hand 
	 * 
	 * The test allow an uncertainty for the position because GitHub seems to make different rounded
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


		assertEquals(formations.get(0).getPoint().getX(), 308, 5);
		assertEquals(formations.get(1).getPoint().getX(), 640, 5);
		assertEquals(formations.get(2).getPoint().getX(), 306, 5);
		assertEquals(formations.get(3).getPoint().getX(), 640, 5);
		assertEquals(formations.get(4).getPoint().getX(), 91, 5);
		assertEquals(formations.get(5).getPoint().getX(), 224, 5);
		assertEquals(formations.get(6).getPoint().getX(), 359, 5);
		assertEquals(formations.get(7).getPoint().getX(), 508, 5);
		assertEquals(formations.get(8).getPoint().getX(), 665, 5);
		assertEquals(formations.get(9).getPoint().getX(), 824, 5);
		
		assertEquals(formations.get(0).getPoint().getY(), 74, 5);
		assertEquals(formations.get(1).getPoint().getY(), 74, 5);
		assertEquals(formations.get(2).getPoint().getY(), 260, 5);
		assertEquals(formations.get(3).getPoint().getY(), 260, 5);
		assertEquals(formations.get(4).getPoint().getY(), 446, 5);
		assertEquals(formations.get(5).getPoint().getY(), 446, 5);
		assertEquals(formations.get(6).getPoint().getY(), 446, 5);
		assertEquals(formations.get(7).getPoint().getY(), 446, 5);
		assertEquals(formations.get(8).getPoint().getY(), 446, 5);
		assertEquals(formations.get(9).getPoint().getY(), 446, 5);
	}
}

package com.github.cocolollipop.mido_svg.JUnit;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.xml.crypto.Data;

import org.junit.Test;

import com.github.cocolollipop.mido_svg.model.DataBase;
import com.github.cocolollipop.mido_svg.svg_generator.ResponsiveSVG;
import com.github.cocolollipop.mido_svg.view.GUISVGGeneratorbis;

public class GUIJUnitTest {
	
	
	/**
	 * In this function we will test the function that count the number of formations in Parcours (or Year) "M1"
	 * 
	 * */ 
	
	@Test
	public void GUItest() throws IOException {
		
		
		ResponsiveSVG r = new ResponsiveSVG();
		DataBase data = new DataBase();
		int output = r.countFormations(data.getFormations(), "M1");
		assertEquals(2, output);
		
	}

}

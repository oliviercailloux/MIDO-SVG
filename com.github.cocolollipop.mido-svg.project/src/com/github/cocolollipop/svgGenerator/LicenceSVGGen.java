package com.github.cocolollipop.svgGenerator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.List;
import java.awt.Rectangle;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.batik.svggen.SVGGeneratorContext;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.svggen.font.Font;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import com.github.cocolollipop.univ.Department;
import com.github.cocolollipop.univ.Master;
import com.github.cocolollipop.univ.Teacher;
import com.github.cocolollipop.univ.Licence;
import com.github.cocolollipop.univ.Formation;


/**
 * Based on https://xmlgraphics.apache.org/batik/using/svg-generator.html (with
 * minor modifications).
 *
 */

public class LicenceSVGGen {

	public static void main(String[] args) throws Exception {
		LicenceSVGGen test = new LicenceSVGGen();
		test.paint();
	}

	public void paint() throws IOException, ParserConfigurationException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();

		// Get a DOMImplementation.
		DOMImplementation domImpl = db.getDOMImplementation();

		// Create an instance of org.w3c.dom.Document.
		String svgNS = "http://www.w3.org/2000/svg";
		Document document = domImpl.createDocument(svgNS, "svg", null);

		// Create an instance of the SVG Generator.
		SVGGeneratorContext ctx = SVGGeneratorContext.createDefault(document);
		ctx.setEmbeddedFontsOn(true);
		SVGGraphics2D g = new SVGGraphics2D(ctx, false);
		
		// Create position variables
		
		int lineCENTER=50; // Makes the line arrive in the center of the rectangle
		int lineYDOWN=7;  // Makes the line go DOWN a little so the line is not on the text
		int lineYUP=-20; // Makes the line go UP a little so the line is not on the text
		
		
        //Objects creation
		
		Department MIDO = new Department("MIDO", 500, 20);

		MIDO.setTitle("MIDO");
		
		Licence L3MIAGE = new Licence("L3 MIAGE", 250, 70);
		Licence L3MIAGEApp = new Licence("L3 MIAGE App", 750, 70);

		
		Master M1MIAGE = new Master("M1 MIAGE", 250, 150);
		Master M1MIAGEApp = new Master("M1 MIAGE App", 750, 150);
		
		Master M2MIAGEIF = new Master("M2 MIAGE IF", 100, 300);
		Master M2MIAGEID = new Master("M2 MIAGE ID", 250, 300);
		Master M2MIAGESTIN = new Master("M2 MIAGE STIN", 400, 300);
		
		Master M2MIAGEIFApp = new Master("M2 MIAGE IF App", 600, 300);
		Master M2MIAGEIDApp = new Master("M2 MIAGE ID App", 750, 300);
		Master M2MIAGESTINApp = new Master("M2 MIAGE STIN App", 900, 300);
		
		Teacher Cailloux = new Teacher("Cailloux Olivier", 350, 70);
		
		// List of objets 
		
		LinkedList<Formation> list= new LinkedList<Formation>();

		list.add(L3MIAGE);
		list.add(L3MIAGEApp);
		list.add(M1MIAGE);
		list.add(M1MIAGEApp);
		list.add(M2MIAGEIF);
		list.add(M2MIAGEID);
		list.add(M2MIAGESTIN);
		list.add(M2MIAGEIFApp);
		list.add(M2MIAGEIDApp);
		list.add(M2MIAGESTINApp);
		
		L3MIAGE.addFormationAccessible(M1MIAGE);
		L3MIAGEApp.addFormationAccessible(M1MIAGEApp);
		M1MIAGE.addFormationAccessible(M2MIAGESTIN);
		M1MIAGE.addFormationAccessible(M2MIAGEIF);
		M1MIAGE.addFormationAccessible(M2MIAGEID);
		M1MIAGEApp.addFormationAccessible(M2MIAGESTINApp);
		M1MIAGEApp.addFormationAccessible(M2MIAGEIDApp);
		M1MIAGEApp.addFormationAccessible(M2MIAGEIFApp);



		// Ask the test to render into the SVG Graphics2D implementation.
		g.setPaint(Color.black);
		int canevasX = 2480;
		int canevasY = 3508; //A4 size
		g.setSVGCanvasSize(new Dimension(canevasX, canevasY));
		g.drawString(MIDO.getNomDepartement(), MIDO.getX(), MIDO.getY());
		
        // Drawing of the objects
		
		for(Formation str:list)
			g.drawString( str.getFullName(), str.getPosX(), str.getPosY());

		
		g.setPaint(Color.green);
		//g.setSVGCanvasSize(new Dimension(2,2));
		//Font myFont = new Font("Serif", Font.BOLD, 12);
		
		//g.fillRect(100, 50, 200, 100);
		String fullname = Cailloux.getLastName() + " " + Cailloux.getFirstName();
		g.drawString(fullname , Cailloux.getPosX(), Cailloux.getPosX());
		
		
		g.setPaint(Color.black);

		
		// Drawing of the lines linking the objects
		
		for(Formation str: list){
			for(Formation str2 :str.getListOfAvailableFormations()){
			g.drawLine(str.getPosX() + lineCENTER , str.getPosY()+lineYDOWN, str2.getPosX() + lineCENTER, str2.getPosY()+lineYUP);

			}
		}

		
		/* Dessiner des rectangles autour des formations initiales */
		
		for(Formation str:list){
		Rectangle t = new Rectangle(str.getPosX()-10,str.getPosY()-20,str.getFullName().length()*10,25);
		g.draw(t);
		
		}
		

		// Finally, stream out SVG using UTF-8 encoding.
		boolean useCSS = true; // we want to use CSS style attributes
		try (Writer out = new OutputStreamWriter(new FileOutputStream("outLicence.svg"), "UTF-8")) {
			g.stream(out, useCSS);
		}
	}
}
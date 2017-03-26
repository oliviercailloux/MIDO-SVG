package com.github.cocolollipop.svgGenerator;

import java.awt.Color;
import java.awt.Dimension;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.batik.svggen.SVGGeneratorContext;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import com.github.cocolollipop.univ.Departement;
import com.github.cocolollipop.univ.Master;
import com.github.cocolollipop.univ.Licence;
import com.github.cocolollipop.univ.Formation;
import com.github.cocolollipop.univ.Enseignant;




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
		
        //Objects creation
		
		Departement MIDO = new Departement("MIDO", 500, 20);
		MIDO.setNomDepartement("MIDO");
		
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
		
		Enseignant Cailloux = new Enseignant("Cailloux Olivier", 350, 70);
		
		

		// Ask the test to render into the SVG Graphics2D implementation.
		g.setPaint(Color.black);
		int canevasX = 2480;
		int canevasY = 3508; //A4 size
		g.setSVGCanvasSize(new Dimension(canevasX, canevasY));
		g.drawString(MIDO.getNomDepartement(), MIDO.getX(), MIDO.getY());
		
        // Drawing of the objects

		g.drawString(L3MIAGE.getFullName(), L3MIAGE.getX(), L3MIAGE.getY());
		g.drawString(L3MIAGEApp.getFullName(), L3MIAGEApp.getX(), L3MIAGEApp.getY());
		
		g.drawString(M1MIAGE.getFullName(), M1MIAGE.getX(), M1MIAGE.getY());
		g.drawString(M1MIAGEApp.getFullName(), M1MIAGEApp.getX(), M1MIAGEApp.getY());
		
		g.drawString(M2MIAGEIF.getFullName(), M2MIAGEIF.getX(), M2MIAGEIF.getY());
		g.drawString(M2MIAGEID.getFullName(), M2MIAGEID.getX(), M2MIAGEID.getY());
		g.drawString(M2MIAGESTIN.getFullName(), M2MIAGESTIN.getX(), M2MIAGESTIN.getY());
		
		g.drawString(M2MIAGEIFApp.getFullName(), M2MIAGEIFApp.getX(), M2MIAGEIFApp.getY());
		g.drawString(M2MIAGEIDApp.getFullName(), M2MIAGEIDApp.getX(), M2MIAGEIDApp.getY());
		g.drawString(M2MIAGESTINApp.getFullName(), M2MIAGESTINApp.getX(), M2MIAGESTINApp.getY());
		
		g.setPaint(Color.green);
		
		g.drawString(Cailloux.getNomEnseignant(), Cailloux.getX(), Cailloux.getY());
		
		g.setPaint(Color.black);
		
		// Drawing of the lines linking the objects

		g.drawLine(L3MIAGE.getX() + 50, L3MIAGE.getY(), M1MIAGE.getX() + 50, M1MIAGE.getY());
		g.drawLine(L3MIAGEApp.getX() + 50, L3MIAGEApp.getY(), M1MIAGEApp.getX() + 50, M1MIAGEApp.getY());
		g.drawLine(M1MIAGE.getX() + 50, M1MIAGE.getY(), M2MIAGEIF.getX() + 50, M2MIAGEIF.getY());
		g.drawLine(M1MIAGE.getX() + 50, M1MIAGE.getY(), M2MIAGEID.getX() + 50, M2MIAGEID.getY());
		g.drawLine(M1MIAGE.getX() + 50, M1MIAGE.getY(), M2MIAGESTIN.getX() + 50, M2MIAGESTIN.getY());
		g.drawLine(M1MIAGEApp.getX() + 50, M1MIAGEApp.getY(), M2MIAGEIFApp.getX() + 50, M2MIAGEIFApp.getY());
		g.drawLine(M1MIAGEApp.getX() + 50, M1MIAGEApp.getY(), M2MIAGEIDApp.getX() + 50, M2MIAGEIDApp.getY());
		g.drawLine(M1MIAGEApp.getX() + 50, M1MIAGEApp.getY(), M2MIAGESTINApp.getX() + 50, M2MIAGESTINApp.getY());



		// Finally, stream out SVG using UTF-8 encoding.
		boolean useCSS = true; // we want to use CSS style attributes
		try (Writer out = new OutputStreamWriter(new FileOutputStream("outLicence.svg"), "UTF-8")) {
			g.stream(out, useCSS);
		}
	}
}
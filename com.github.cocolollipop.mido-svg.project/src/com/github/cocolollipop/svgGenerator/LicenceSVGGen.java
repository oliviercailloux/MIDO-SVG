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
		
		// List of objets 
		
		/*LinkedList<Formation> list= new LinkedList<Formation>();
		list.add(L3MIAGE);
		list.add(L3MIAGEApp);
		list.add(M1MIAGEApp);
		list.add(M1MIAGE);
		list.add(M2MIAGEIF);
		
		ListIterator li = list.listIterator();
		while(li.hasNext()){
			g.drawString( ((Formation) li.next()).getFullName(), ((Formation) li.next()).getPosX(), ((Formation) li.next()).getPosY());

		}*/

		// Ask the test to render into the SVG Graphics2D implementation.
		g.setPaint(Color.black);
		int canevasX = 2480;
		int canevasY = 3508; //A4 size
		g.setSVGCanvasSize(new Dimension(canevasX, canevasY));
		g.drawString(MIDO.getNomDepartement(), MIDO.getX(), MIDO.getY());
		
        // Drawing of the objects

		g.drawString(L3MIAGE.getFullName(), L3MIAGE.getPosX(), L3MIAGE.getPosY());
		g.drawString(L3MIAGEApp.getFullName(), L3MIAGEApp.getPosX(), L3MIAGEApp.getPosY());
		
		g.drawString(M1MIAGE.getFullName(), M1MIAGE.getPosX(), M1MIAGE.getPosY());
		g.drawString(M1MIAGEApp.getFullName(), M1MIAGEApp.getPosX(), M1MIAGEApp.getPosY());
		
		g.drawString(M2MIAGEIF.getFullName(), M2MIAGEIF.getPosX(), M2MIAGEIF.getPosY());
		g.drawString(M2MIAGEID.getFullName(), M2MIAGEID.getPosX(), M2MIAGEID.getPosY());
		g.drawString(M2MIAGESTIN.getFullName(), M2MIAGESTIN.getPosX(), M2MIAGESTIN.getPosY());
		
		g.drawString(M2MIAGEIFApp.getFullName(), M2MIAGEIFApp.getPosX(), M2MIAGEIFApp.getPosY());
		g.drawString(M2MIAGEIDApp.getFullName(), M2MIAGEIDApp.getPosX(), M2MIAGEIDApp.getPosY());
		g.drawString(M2MIAGESTINApp.getFullName(), M2MIAGESTINApp.getPosX(), M2MIAGESTINApp.getPosY());
		
		
		g.setPaint(Color.green);
		//g.setSVGCanvasSize(new Dimension(2,2));
		//Font myFont = new Font("Serif", Font.BOLD, 12);
		
		//g.fillRect(100, 50, 200, 100);

		g.drawString(Cailloux.getNomEnseignant(), Cailloux.getX(), Cailloux.getY());
		
		
		g.setPaint(Color.black);

		
		// Drawing of the lines linking the objects
		g.drawLine(L3MIAGE.getPosX() + 50, L3MIAGE.getPosY()+7, M1MIAGE.getPosX() + 50, M1MIAGE.getPosY()-20);
		g.drawLine(L3MIAGEApp.getPosX() + 50, L3MIAGEApp.getPosY()+7, M1MIAGEApp.getPosX() + 50, M1MIAGEApp.getPosY()-20);
		g.drawLine(M1MIAGE.getPosX() + 50, M1MIAGE.getPosY()+7, M2MIAGEIF.getPosX() + 50, M2MIAGEIF.getPosY()-20);
		g.drawLine(M1MIAGE.getPosX() + 50, M1MIAGE.getPosY()+7, M2MIAGEID.getPosX() + 50, M2MIAGEID.getPosY()-20);
		g.drawLine(M1MIAGE.getPosX() + 50, M1MIAGE.getPosY()+7, M2MIAGESTIN.getPosX() + 50, M2MIAGESTIN.getPosY()-20);
		g.drawLine(M1MIAGEApp.getPosX() + 50, M1MIAGEApp.getPosY()+7, M2MIAGEIFApp.getPosX() + 50, M2MIAGEIFApp.getPosY()-20);
		g.drawLine(M1MIAGEApp.getPosX() + 50, M1MIAGEApp.getPosY()+7, M2MIAGEIDApp.getPosX() + 50, M2MIAGEIDApp.getPosY()-20);
		g.drawLine(M1MIAGEApp.getPosX() + 50, M1MIAGEApp.getPosY()+7, M2MIAGESTINApp.getPosX() + 50, M2MIAGESTINApp.getPosY()-20);

		/* Dessiner des rectangles autour des formations initiales */
		
		Rectangle t = new Rectangle(L3MIAGE.getPosX()-10,L3MIAGE.getPosY()-20,L3MIAGE.getFullName().length()*10,25);
		t.getCenterX();
		g.draw(t);
		
		Rectangle t1 = new Rectangle(L3MIAGEApp.getPosX()-10,L3MIAGEApp.getPosY()-20,L3MIAGEApp.getFullName().length()*10,25);
		t1.getCenterX();
		g.draw(t1);
		
		Rectangle t2 = new Rectangle(M1MIAGE.getPosX()-10,M1MIAGE.getPosY()-20,M1MIAGE.getFullName().length()*10,25);
		t2.getCenterX();
		g.draw(t2);
		
		Rectangle t4 = new Rectangle(M2MIAGEID.getPosX()-10,M2MIAGEID.getPosY()-20,M2MIAGEID.getFullName().length()*10,25);
		t4.getCenterX();
		g.draw(t4);
		
		Rectangle t5 = new Rectangle(M2MIAGEIF.getPosX()-10,M2MIAGEIF.getPosY()-20,M2MIAGEIF.getFullName().length()*10,25);
		t5.getCenterX();
		g.draw(t5);
		
		Rectangle t6 = new Rectangle(M2MIAGESTIN.getPosX()-10,M2MIAGESTIN.getPosY()-20,M2MIAGESTIN.getFullName().length()*10,25);
		t6.getCenterX();
		g.draw(t6);
		
		
		/* Dessiner des rectangles autour des formations en apprentissage */

		Rectangle t3 = new Rectangle(M1MIAGEApp.getPosX()-10,M1MIAGEApp.getPosY()-20,M1MIAGEApp.getFullName().length()*10,25);
		t3.getCenterX();
		g.draw(t3);
		
		Rectangle t7 = new Rectangle(M2MIAGESTINApp.getPosX()-10,M2MIAGESTINApp.getPosY()-20,M2MIAGESTINApp.getFullName().length()*10,25);
		t7.getCenterX();
		g.draw(t7);
		
		Rectangle t8 = new Rectangle(M2MIAGEIFApp.getPosX()-10,M2MIAGEIFApp.getPosY()-20,M2MIAGEIFApp.getFullName().length()*10,25);
		t8.getCenterX();
		g.draw(t8);
		
		
		Rectangle t9 = new Rectangle(M2MIAGEIDApp.getPosX()-10,M2MIAGEIDApp.getPosY()-20,M2MIAGEIDApp.getFullName().length()*10,25);
		t9.getCenterX();
		g.draw(t9);
		
		

		// Finally, stream out SVG using UTF-8 encoding.
		boolean useCSS = true; // we want to use CSS style attributes
		try (Writer out = new OutputStreamWriter(new FileOutputStream("outLicence.svg"), "UTF-8")) {
			g.stream(out, useCSS);
		}
	}
}
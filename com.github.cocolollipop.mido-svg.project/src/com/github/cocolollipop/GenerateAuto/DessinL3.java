package com.github.cocolollipop.GenerateAuto;


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

/**
 * Idée de la classe DESSINL3 :
 * Dans cette classe, nous allons créer un CANVAS de taille 1920 x 1080
 * Le but est de trouver à quel endroit nous pouvons écrirer du texte
 * Comme objet nous utiliserons une formation à laquelle nous avons greffé un pere et un fils. Le fils peut contenir une Arraylist d'objets Formation
 *
 */

public class DessinL3 {
	int dimXCanvas = 1920;
	int dimYCanvas = 1080;
	int decalageX;
	int decalageY;
	
	
	
	public void getDecalage(int nbRow, int nbCol){
		this.decalageX = 1920/nbCol;
		this.decalageY = 1080/nbRow;
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

		// Ask the test to render into the SVG Graphics2D implementation.
		
		// On va d'abord fixer la dimension du plan de travails
		g.setSVGCanvasSize(new Dimension(this.dimXCanvas, this.dimYCanvas));

		
		// On fixe la couleur du pinceau à noir
		g.setPaint(Color.black);
		
		g.drawString("MIDO", 300, 20);
		g.drawString("Masters", 300, 50);
	}
	
	public static void main(String[] args) throws Exception {
		DessinL3 test = new DessinL3();
		test.paint();
	}

}
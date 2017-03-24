package com.github.cocolollipop.GenerateAuto;


import java.awt.Color;
import java.awt.Dimension;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.batik.svggen.SVGGeneratorContext;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import com.github.cocolollipop.univ.Licence;

/**
 * Id�e de la classe DESSINL3 :
 * Dans cette classe, nous allons cr�er un CANVAS de taille 1920 x 1080
 * Le but est de trouver � quel endroit nous pouvons �crirer du texte
 * Comme objet nous utiliserons une formation � laquelle nous avons greff� un pere et un fils. Le fils peut contenir une Arraylist d'objets Formation
 *
 */

public class DessinLicence {
	int dimXCanvas = 1920;
	int dimYCanvas = 1080;
	int decalageX;
	int decalageY;
	
	
	/**
	 * getDecalage define decalageX and decalageY according to the number of row  and columns you want to "cut" you canvas
	 * @param nbRow
	 * @param nbCol
	 */
	public void getDecalage(int nbRow, int nbCol){
		this.decalageX = this.dimXCanvas/nbCol;
		this.decalageY = this.dimYCanvas/nbRow;
	}
	
	
	public SVGGraphics2D paint() throws IOException, ParserConfigurationException {
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

		
		// On fixe la couleur du pinceau � noir
		g.setPaint(Color.black);
		
		
		// On cr�e 3 objets : Licence 1, 2 et 3
		
		g.drawString("MIDO", 300, 20);
		g.drawString("Masters", 300, 50);
		return g;
	}
	
	public static void main(String[] args) throws Exception {
		DessinLicence test = new DessinLicence();
		SVGGraphics2D myGraph;
		myGraph = test.paint();
		
		try (Writer out = new OutputStreamWriter(new FileOutputStream("DessinLicenceTest.svg"), "UTF-8")) {
			myGraph.stream(out, true);
		}
		
		// On va demaander � l'utilisateur combien de licence il veut placer, et sur combien d'ann�es
		int nbCol = test.askLicenceNb();
		int nbRows= test.askYearNb();
		
		// on va chercher le decalage en X � cr�er par rapport au canvas
		test.getDecalage(nbRows, nbCol);
		
		// On va cr�er une liste qui contient toutes nos licences
		ArrayList listOfAllLicences = new ArrayList();
		
		// On cr�e nos nbRows*nbCol Licences
		for (int i =0; i<nbCol;i++){
			for (int j=0; j<nbRows; j++){
				Licence myLicence = new Licence(j,"Licence TEST :"+i+" Annee : "+j);
				listOfAllLicences.add(myLicence);
			}
			
		}
		
		// On veut maintenant les placer correctement au bon endroit.
		// Pour cela, on va compter le nombre de licences dont on dispose
		int nbLicences = listOfAllLicences.size();
		System.out.println("J'ai "+nbLicences+" Licences au total");
		
	}


	private int askLicenceNb() {
		System.out.println("Combien de Licences souhaitez-vous afficher ? ");
		Scanner sc = new Scanner ("System.in");
		
		// ici faire un test sur le parseInt
		int nbCol = Integer.parseInt(sc.nextLine());
		sc.close();
		return nbCol;
		
	}
	private int askYearNb() {
		System.out.println("Combien d'ann�es souhaitez-vous afficher ? ");
		 
		Scanner sc = new Scanner ("System.in");
		
		// ici faire un test sur le parseInt
		int nbRow = Integer.parseInt(sc.nextLine());
		sc.close();
		return nbRow;
	}

}
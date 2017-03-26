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
 * Id�e de la classe DESSINL3 : Dans cette classe, nous allons cr�er un CANVAS
 * de taille 1920 x 1080 Le but est de trouver � quel endroit nous pouvons
 * �crirer du texte Comme objet nous utiliserons une formation � laquelle nous
 * avons greff� un pere et un fils. Le fils peut contenir une Arraylist d'objets
 * Formation
 *
 */

public class DessinLicence {
	int dimXCanvas = 1920;
	int dimYCanvas = 1080;
	int decalageX;
	int decalageY;

	/**
	 * getDecalage define decalageX and decalageY according to the number of row
	 * and columns you want to "cut" you canvas
	 * 
	 * @param nbRow
	 * @param nbCol
	 */
	public void getDecalage(int nbRow, int nbCol) {
		this.decalageX = this.dimXCanvas / (nbCol + 1);
		this.decalageY = this.dimYCanvas / (nbRow + 1);
	}

	public void paint(ArrayList<Licence> listOfAllLicences) throws IOException, ParserConfigurationException {
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
		// set the size of SVG
		g.setSVGCanvasSize(new Dimension(this.dimXCanvas, this.dimYCanvas));
		g.setPaint(Color.black); // paint in black
		/**
		 * We starts painting
		 */
		g.drawString("MIDO", 300, 20);
		g.drawString("Masters", 300, 50);
		/**
		 * We creates the licences
		 */
		for (int i = 0; i < listOfAllLicences.size(); i++) {
			g.drawString(listOfAllLicences.get(i).getIntitule(), listOfAllLicences.get(i).getPosX(),
					listOfAllLicences.get(i).getPosY());
		}
		
		/**
		 * Exports the SVG
		 */
		try (Writer out = new OutputStreamWriter(new FileOutputStream("DessinLicenceTest.svg"), "UTF-8")) {
			g.stream(out, true);
		}
	}

	public static void main(String[] args) throws Exception {
		DessinLicence test = new DessinLicence();

		/** We are asking how many rows and how many columns we need **/
		int nbCol = test.askLicenceNb();
		int nbRows = test.askYearNb();

		// on va chercher le decalage en X � cr�er par rapport au canvas
		test.getDecalage(nbRows, nbCol);

		// On va cr�er une liste qui contient toutes nos licences
		ArrayList<Licence> listOfAllLicences = new ArrayList<Licence>();

		// On cr�e nos nbRows*nbCol Licences
		for (int i = 0; i < nbCol; i++) {
			for (int j = 0; j < nbRows; j++) {
				Licence myLicence = new Licence(j, "Licence TEST :" + j + " Annee : " + (i + 1));
				myLicence.setPosX(test.decalageX, i);
				myLicence.setPosY(test.decalageY, j);
				listOfAllLicences.add(myLicence);
			}

		}

		// On veut maintenant les placer correctement au bon endroit.
		// Pour cela, on va compter le nombre de licences dont on dispose
		int nbLicences = listOfAllLicences.size();
		System.out.println("J'ai " + nbLicences + " Licences au total");
		test.paint(listOfAllLicences);

	}

	private int askLicenceNb() {
		System.out.println("Combien de Licences souhaitez-vous afficher ? ");
		Scanner sc = new Scanner(System.in);
		try {
			// ici faire un test sur le parseInt
			String entry = sc.nextLine();
			int nbCol = Integer.parseInt(entry);
			return nbCol;
		} finally {
			if (sc != null) {
				// sc.close();
			}
		}

	}

	private int askYearNb() {

		System.out.println("Combien d'ann�es souhaitez-vous afficher ? ");
		Scanner sc = new Scanner(System.in);
		try {
			// ici faire un test sur le parseInt
			String entry = sc.nextLine();
			int nbRow = Integer.parseInt(entry);
			return nbRow;
		} finally {
			if (sc != null) {
				// sc.close();
			}
		}

	}

}
package com.github.cocolollipop.svgGenerator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.batik.svggen.SVGGeneratorContext;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.commons.io.IOUtils;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import com.github.cocolollipop.univ.Department;
import com.github.cocolollipop.univ.Formation;
import com.github.cocolollipop.univ.Licence;
import com.github.cocolollipop.univ.Master;
import com.github.cocolollipop.univ.Teacher;

/**
 * Based on https://xmlgraphics.apache.org/batik/using/svg-generator.html (with
 * minor modifications).
 *
 */
public class LicenceSVGGen {

	private LinkedList<Formation> list;
	int dimXCanvas = 1920;
	int dimYCanvas = 1080;
	int shiftX;
	int shiftY;

	/**
	 * getDecalage define decalageX and decalageY according to the number of row
	 * and columns you want to "cut" you canvas
	 * 
	 * @param nbRow
	 * @param nbCol
	 */
	public void getDecalage(int nbRow, int nbCol) {
		this.shiftX = this.dimXCanvas / (nbCol + 1);
		this.shiftY = this.dimYCanvas / (nbRow + 1);
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

		int lineCENTER = 50; // Makes the line arrive in the center of the
								// rectangle
		int lineYDOWN = 7; // Makes the line go DOWN a little so the line is not
							// on the text
		int lineYUP = -20; // Makes the line go UP a little so the line is not
							// on the text

		// Objects creation

		Department MIDO = new Department("MIDO", 500, 20);

		MIDO.setTitle("MIDO");

		Licence L3MIAGE = new Licence("L3 MIAGE", 3, 250, 70);
		L3MIAGE.setFullNameWithLink(
				"http://formations.dauphine.fr/offre/fr-FR/fiche/A3INF/programme//#FRUAI0750736TPRPRA3INF");
		Licence L3MIAGEApp = new Licence("L3 MIAGE App", 3, 750, 70);
		L3MIAGEApp.setFullNameWithLink(
				"http://formations.dauphine.fr/offre/fr-FR/fiche/A3INF/programme//#FRUAI0750736TPRPRA3INFAPP");
		Master M1MIAGE = new Master("M1 MIAGE", 4, 250, 150);
		M1MIAGE.setFullNameWithLink(
				"http://formations.dauphine.fr/offre/fr-FR/fiche/A5STI/programme//#FRUAI0750736TPRPRA4MIAI");
		Master M1MIAGEApp = new Master("M1 MIAGE App", 4, 750, 150);

		Master M2MIAGEIF = new Master("M2 MIAGE IF", 5, 100, 300);
		Master M2MIAGEID = new Master("M2 MIAGE ID", 5, 250, 300);
		Master M2MIAGESTIN = new Master("M2 MIAGE STIN", 5, 400, 300);

		Master M2MIAGEIFApp = new Master("M2 MIAGE IF App", 5, 600, 300);
		Master M2MIAGEIDApp = new Master("M2 MIAGE ID App", 5, 750, 300);
		Master M2MIAGESTINApp = new Master("M2 MIAGE STIN App", 5, 900, 300);

		Teacher Cailloux = new Teacher("Cailloux Olivier", 350, 70);

		// List of objets
		this.list = new LinkedList<Formation>();

		this.list.add(L3MIAGE);
		this.list.add(L3MIAGEApp);
		this.list.add(M1MIAGE);
		this.list.add(M1MIAGEApp);
		this.list.add(M2MIAGEIF);
		this.list.add(M2MIAGEID);
		this.list.add(M2MIAGESTIN);
		this.list.add(M2MIAGEIFApp);
		this.list.add(M2MIAGEIDApp);
		this.list.add(M2MIAGESTINApp);

		L3MIAGE.addAvailableFormation(M1MIAGE);
		L3MIAGEApp.addAvailableFormation(M1MIAGEApp);
		M1MIAGE.addAvailableFormation(M2MIAGESTIN);
		M1MIAGE.addAvailableFormation(M2MIAGEIF);
		M1MIAGE.addAvailableFormation(M2MIAGEID);
		M1MIAGEApp.addAvailableFormation(M2MIAGESTINApp);
		M1MIAGEApp.addAvailableFormation(M2MIAGEIDApp);
		M1MIAGEApp.addAvailableFormation(M2MIAGEIFApp);

		/**
		 * To have the number of child of each formation
		 */
		for (int i = 0; i < this.list.size(); i++) {
			System.out.println("Pour l\'annee" + list.get(i).getGrade() + list.get(i).getFullName() + " a "
					+ list.get(i).getListOfAvailableFormations().size());

			if (list.get(i).getListOfAvailableFormations().size() == 0) {
				System.out.println("Pas de formation accessible");
			}
			for (int j = 0; j < list.get(i).getListOfAvailableFormations().size(); j++) {
				System.out.println("Les formations accessibles sont:"
						+ list.get(i).getListOfAvailableFormations().get(j).getFullName());
			}
		}

		/**
		 * Number of licences by grade
		 */
		ArrayList<Integer> keyGrade = new ArrayList<Integer>();
		ArrayList<Integer> nbLicenceByGrade = new ArrayList<Integer>();

		for (Formation str : this.list) {
			if (!keyGrade.contains(str.getGrade())) {
				keyGrade.add(str.getGrade());
				nbLicenceByGrade.add(0);
			}
			int loc = keyGrade.indexOf(str.getGrade());
			int newVal = nbLicenceByGrade.get(loc);
			newVal++;
			nbLicenceByGrade.set(loc, newVal);

		}
		System.out.println("AFFICHAGE DU NOMBRE DE LICENCE PAR GRADE :");
		for (int k = 0; k < keyGrade.size(); k++) {
			System.out.println(
					"Pour l\'annee " + keyGrade.get(k) + " On aura en tout " + nbLicenceByGrade.get(k) + " licences");
		}
		// Ask the test to render into the SVG Graphics2D implementation.
		g.setPaint(Color.black);
		int canevasX = 2480;
		int canevasY = 3508; // A4 size
		g.setSVGCanvasSize(new Dimension(canevasX, canevasY));
		g.drawString(MIDO.getNomDepartement(), MIDO.getX(), MIDO.getY());

		// Drawing of the objects

		for (Formation str : this.list)
			// g.drawString(str.getFullName(), str.getPosX(), str.getPosY());

			g.drawString(str.getFullNameWithLink(), str.getPosX(), str.getPosY());

		g.setPaint(Color.green);
		// g.setSVGCanvasSize(new Dimension(2,2));
		// Font myFont = new Font("Serif", Font.BOLD, 12);

		// g.fillRect(100, 50, 200, 100);

		String fullname = Cailloux.getLastName() + " " + Cailloux.getFirstName();
		g.drawString(fullname, Cailloux.getPosX(), Cailloux.getPosX());

		g.setPaint(Color.black);

		// Drawing of the lines linking the objects

		for (Formation str : this.list) {
			for (Formation str2 : str.getListOfAvailableFormations()) {
				g.drawLine(str.getPosX() + lineCENTER, str.getPosY() + lineYDOWN, str2.getPosX() + lineCENTER,
						str2.getPosY() + lineYUP);

			}
		}

		/* Dessiner des rectangles autour des formations initiales */

		for (Formation str : this.list) {
			Rectangle t = new Rectangle(str.getPosX() - 10, str.getPosY() - 20, str.getFullName().length() * 10, 25);
			g.draw(t);

		}

		// Finally, stream out SVG using UTF-8 encoding.
		boolean useCSS = true; // we want to use CSS style attributes
		try (Writer out = new OutputStreamWriter(new FileOutputStream("outLicence.svg"), "UTF-8")) {
			g.stream(out, useCSS);
		}

		/**
		 * This is to replace "&lt;" by "<" and "&gt;" by ">" because I did not
		 * found how to avoid converting < into &lt; and > into &gt;
		 **/
		String content = IOUtils.toString(new FileInputStream("outLicence.svg"), "UTF-8");
		content = content.replaceAll("&lt;", "<");
		content = content.replaceAll("&gt;", ">");
		// the line below was done because the unicode="&lt;" generated by the
		// g.stream() was first replaced by unicode="<" so we had to remove this
		// because we (hopefully) don't need it
		content = content.replaceAll("unicode=\"<\"", "unicode=\"\"");
		IOUtils.write(content, new FileOutputStream("outLicence.svg"), "UTF-8");

	}

	public static void main(String[] args) throws Exception {
		LicenceSVGGen test = new LicenceSVGGen();
		test.paint();
	}
}
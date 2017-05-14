package com.github.cocolollipop.svgGenerator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.batik.svggen.SVGGeneratorContext;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.commons.io.IOUtils;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import com.github.cocolollipop.dataBase.DataBase;
import com.github.cocolollipop.univ.Formation;
import com.github.cocolollipop.univ.Subject;

public class LicenceSVGGen {

	private DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	private DocumentBuilder db;
	private DOMImplementation domImpl;
	private String svgNS;
	private Document document;
	private SVGGeneratorContext ctx;
	private SVGGraphics2D g;
	private DataBase data;

	public LicenceSVGGen() {
		this.data = new DataBase();
	}

	private DataBase getData() {
		// TODO Auto-generated method stub
		return this.data;
	}

	// ALGO
	public void definirPositionsSimple(LinkedList<Formation> lesFormations, int canvasX) {

		/*
		 * On definit d'abord le decalage initial Pour cela, on va analyser le
		 * contenu de lesFormations On va compter le nombre de L1, L2,...
		 * 
		 */
		int decalageX, decalageY, decalageL2, decalageL3, nbL1, nbL2, nbL3, nbM1, nbM2 = 0;

		// on va d'abord compter le nombre de formation parmi la liste envoy�e
		nbL1 = this.getData().compterFormation(this.getData().getListOfFormations(), "L1");
		nbL2 = this.getData().compterFormation(this.getData().getListOfFormations(), "L2");
		nbL3 = this.getData().compterFormation(this.getData().getListOfFormations(), "L3");
		nbM1 = this.getData().compterFormation(this.getData().getListOfFormations(), "M1");
		nbM2 = this.getData().compterFormation(this.getData().getListOfFormations(), "M2");

		/*
		 * On calcule le decalage en Y ; pour cela il suffit de compter le
		 * nombre de nbL1/nbL2 != 0 POUR l'INSTANT, JAI FIXE A LARRACHE
		 */

		/*
		 * Maintenant on calcule le decalage en X
		 */
		decalageX = canvasX / (nbL1 + 1);
		decalageY = 100; // A changer
		associerPositionX(lesFormations, "L1", decalageX, decalageY);

		decalageX = canvasX / (nbL2 + 1);
		decalageY = decalageY + 200;
		associerPositionX(lesFormations, "L2", decalageX, decalageY);

		decalageX = canvasX / (nbL3 + 1);
		decalageY = decalageY + 200;
		associerPositionX(lesFormations, "L3", decalageX, decalageY);

		decalageX = canvasX / (nbM1 + 1);
		decalageY = decalageY + 200;
		associerPositionX(lesFormations, "M1", decalageX, decalageY);

		decalageX = canvasX / (nbM2 + 1);
		decalageY = decalageY + 200;
		associerPositionX(lesFormations, "M2", decalageX, decalageY);

	}

	private void getPlacement(LinkedList<Formation> lesFormations) {
		for (Formation uneFormation : lesFormations) {
			System.out.println("Pour la formation " + uneFormation.getFullName());
			System.out.println("PosX = " + uneFormation.getPosX());
			System.out.println("PosY = " + uneFormation.getPosY());
			System.out.println("_________________");
		}

	}

	/**
	 * associerPositionX set the posX of each Formation which satisfy
	 * uneFormation.getFullName() == myYear
	 * 
	 * @param lesFormations
	 *            is a LinkedList of Formation
	 * @param myYear
	 *            is a year such as "L3" or "M1"
	 * @param decalage
	 */
	private void associerPositionX(LinkedList<Formation> lesFormations, String myYear, int decalageX, int decalageY) {
		int i = 1;
		for (Formation uneFormation : lesFormations) {
			if (uneFormation.getFullName().indexOf(myYear) != -1) {
				uneFormation.setPosX(decalageX * i);
				uneFormation.setPosY(decalageY);
				i++;
				System.out.println("associerOK : " + uneFormation.getFullName());
			}
		}

	}
	// FIN ALGO

	/**
	 * fillListOfFormationToShow method fills the listOfFormationToShow with
	 * objects from FormationList that are of type typeOfFormation
	 * 
	 * @param typeOfFormation
	 * @return
	 * @throws ClassNotFoundException
	 */
	public LinkedList<Formation> fillListOfFormationToShow(String typeOfFormation) throws ClassNotFoundException {
		LinkedList<Formation> listOfFormationToShow = new LinkedList<Formation>();

		for (Formation formation : this.getData().getListOfFormations()) {
			if (formation.getCategory() == typeOfFormation)
				listOfFormationToShow.add(formation);
		}
		return listOfFormationToShow;
	}

	public void paint(String choiceOfShow) throws Exception {
		String output = "outLicence.svg";

		db = dbf.newDocumentBuilder();
		// Get a DOMImplementation.
		domImpl = db.getDOMImplementation();
		// Create an instance of org.w3c.dom.Document.
		svgNS = "http://www.w3.org/2000/svg";
		document = domImpl.createDocument(svgNS, "svg", null);

		// Create an instance of the SVG Generator.
		ctx = SVGGeneratorContext.createDefault(document);
		ctx.setEmbeddedFontsOn(true);
		g = new SVGGraphics2D(ctx, false);
		// Create position variables
		definirPositionsSimple(this.data.getListOfFormations(), 1920);

		// The tag that the user selected (he wants to see what are the
		// formation that teaches this course)
		// String userSelectedTag = "Probas";
		String userSelectedTags[] = { "Rugby", "ADD", "Espagnol" };

		// Ask the test to render into the SVG Graphics2D implementation.

		g.setPaint(Color.black);

		g.setSVGCanvasSize(
				new Dimension(this.getData().getFormat().getCanevasX(), this.getData().getFormat().getCanevasY()));

		g.drawString(this.data.getDepartment().getNomDepartement(), this.data.getDepartment().getX(),
				this.data.getDepartment().getY());

		this.show(choiceOfShow);

		g.setPaint(Color.green);
		for (Formation f : this.data.getListOfFormations())
			g.drawString(f.getTeacher().getFullNameTeacher(), f.getTeacher().getPosX(), f.getTeacher().getPosY());

		g.setPaint(Color.black);

		// Tag checking
		int cptTags = 0;
		for (Formation f : this.data.getListOfFormations()) {
			cptTags = 0;
			for (String str : userSelectedTags) {

				if (Arrays.asList(f.getTagslist()).contains(str)) {
					cptTags++;

					if (cptTags == userSelectedTags.length) {
						g.setPaint(Color.red);
						g.drawString("(X)", f.getPosX() + 50, f.getPosY() + 20);
					}
				}
			}
		}

		g.setPaint(Color.black);

		// Finally, stream out SVG using UTF-8 encoding.
		boolean useCSS = true; // we want to use CSS style attributes
		try (Writer out = new OutputStreamWriter(new FileOutputStream(output), "UTF-8")) {
			g.stream(out, useCSS);
		}

		String content = this.svgLinkable(output);
		IOUtils.write(content, new FileOutputStream(output), "UTF-8");

	}

	/**
	 * This is to replace "&lt;" by "<" and "&gt;" by ">" because I did not
	 * found how to avoid converting < into &lt; and > into &gt;
	 **/
	public String svgLinkable(String output) throws FileNotFoundException, IOException {
		String content = IOUtils.toString(new FileInputStream(output), "UTF-8");
		content = content.replaceAll("&lt;", "<");
		content = content.replaceAll("&gt;", ">");
		return content = content.replaceAll("unicode=\"<\"", "unicode=\"\"");

	}

	/**
	 * Drawing of the objects the user has the choice between showing all
	 * "formations" or only "licence" or master for that he has to change
	 * the @param showOnly to (licenceOnly, masterOnly, both) then the
	 * rectangles arround the "formations" are drawn, lines also
	 * 
	 * @param lineCENTER
	 * @param lineYDOWN
	 * @param lineYUP
	 * @throws ClassNotFoundException
	 */
	public void show(String showOnly) throws ClassNotFoundException {
		// Makes the line arrive in the center of the rectangle
		int lineCENTER = 50;
		// Makes the line go DOWN a little so the line is not on the text
		int lineYDOWN = 7;
		// Makes the line go UP a little so the line is noton the text
		int lineYUP = -20;
		LinkedList<Formation> listToShow = new LinkedList();
		// showing only licence formations
		if (showOnly == "Licence" || showOnly == "Master") {
			listToShow.addAll(this.fillListOfFormationToShow(showOnly));
		}
		if (showOnly == "both") {
			listToShow = this.getData().getListOfFormations();
		}

		for (Formation l : listToShow) {
			g.setPaint(Color.black);
			g.drawString(l.getFullNameWithLink(), l.getPosX(), l.getPosY());
			// write the name of formation
			Rectangle t = new Rectangle(l.getPosX() - 10, l.getPosY() - 20, l.getFullName().length() * 10, 25); // draw
																												// //
																												// rectangle
			g.draw(t);
			g.setPaint(Color.blue);
			g.drawString(l.getAdmisssion(), l.getPosX() - 30, l.getPosY() - 30);
			g.setPaint(Color.black);
			for (Formation l2 : l.getListOfAvailableFormations()) {
				// draw the lines between the formation and the avalaible
				// formations
				g.drawLine(l.getPosX() + lineCENTER, l.getPosY() + lineYDOWN, l2.getPosX() + lineCENTER,
						l2.getPosY() + lineYUP);
			}
			for (Subject s : l.getListOfSubjects()) {
				g.drawString(s.getTitle(), s.getPosX(), s.getPosY());
			}
			g.setPaint(Color.red);
			for (Subject s : l.getListOfSubjects()) {
				java.awt.Font font = new java.awt.Font("TimesRoman", 10, 10);
				g.setFont(font);
				g.drawString(s.getResponsible().getLastName(), s.getPosX() + (s.getTitle().length() * 7), s.getPosY());
				java.awt.Font font1 = new java.awt.Font("TimesRoman", 12, 12);
				g.setFont(font1);

			}
		}

		g.setPaint(Color.green);
		for (Formation f : listToShow)
			g.drawString(f.getTeacher().getFullNameTeacher(), f.getTeacher().getPosX(), f.getTeacher().getPosY());

		g.setPaint(Color.black);

	}

	public static void main(String[] args) throws Exception {
		LicenceSVGGen test = new LicenceSVGGen();
		// On choisit ce qu'on veut afficher + format
		test.paint("both");

	}
}
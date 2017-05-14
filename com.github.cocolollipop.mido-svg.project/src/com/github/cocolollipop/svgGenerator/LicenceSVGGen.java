package com.github.cocolollipop.svgGenerator;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
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

	/**
	 * defineObjectsPosition determine the position of each Formation in the
	 * someFormations List
	 * 
	 * @param someFormations
	 *            : LinkedList of all the formations available in the University
	 * @param canvasX
	 *            : abscissa size of the actual Canval
	 */
	public void defineObjectsPosition(LinkedList<Formation> someFormations, int canvasX, int canvasY) {

		/*
		 * We define initial offset In order to it, we must count number of each
		 * Formation in someFormations Then calculate Y offset depending on
		 * which formation exists (Only L1 or all ?) Finally we calculate X
		 * offset
		 */

		int offsetX = 0;
		int offsetY = 0;
		int nbL1 = 0;
		int nbL2 = 0;
		int nbL3 = 0;
		int nbM1 = 0;
		int nbM2 = 0;
		int totalCptY = 0; // O<=totalCptY<=5 // tal number of potential
							// "stairs" in Y

		int cptY[] = new int[5];// trigger ; cpunt if there is an object in
								// Formation ; cptYL1 =0, else the postion of
								// first "seen" 0<=cptY[i]<=totalcptY
		for (int i = 0; i < 5; i++) {
			cptY[i] = 0;
		}

		// First we count number of each formation
		nbL1 = this.getData().countFormations(someFormations, "L1");
		nbL2 = this.getData().countFormations(someFormations, "L2");
		nbL3 = this.getData().countFormations(someFormations, "L3");
		nbM1 = this.getData().countFormations(someFormations, "M1");
		nbM2 = this.getData().countFormations(someFormations, "M2");

		/*
		 * We calculate Y offset
		 */
		int tempCpt = 0;
		if (nbL1 != 0) {
			totalCptY = totalCptY + 1;
			cptY[0] = tempCpt + 1;
			tempCpt++;
		}
		if (nbL2 != 0) {
			totalCptY = totalCptY + 1;
			cptY[1] = tempCpt + 1;
			tempCpt++;
		}
		if (nbL3 != 0) {
			totalCptY = totalCptY + 1;
			cptY[2] = tempCpt + 1;
			tempCpt++;
		}
		if (nbM1 != 0) {
			totalCptY = totalCptY + 1;
			cptY[3] = tempCpt + 1;
			tempCpt++;
		}
		if (nbM2 != 0) {
			totalCptY = totalCptY + 1;
			cptY[4] = tempCpt + 1;
			tempCpt++;
		}

		/*
		 * Now we calculate X and Y offset
		 */
		offsetX = canvasX / (nbL1 + 1);
		offsetY = canvasY / (totalCptY + 1) * cptY[0];
		associatePositionX(someFormations, "L1", offsetX, offsetY);

		offsetX = canvasX / (nbL2 + 1);
		offsetY = canvasY / (totalCptY + 1) * cptY[1];
		associatePositionX(someFormations, "L2", offsetX, offsetY);

		offsetX = canvasX / (nbL3 + 1);
		offsetY = canvasY / (totalCptY + 1) * cptY[2];
		associatePositionX(someFormations, "L3", offsetX, offsetY);

		offsetX = canvasX / (nbM1 + 1);
		offsetY = canvasY / (totalCptY + 1) * cptY[3];
		associatePositionX(someFormations, "M1", offsetX, offsetY);

		offsetX = canvasX / (nbM2 + 1);
		offsetY = canvasY / (totalCptY + 1) * cptY[4];
		associatePositionX(someFormations, "M2", offsetX, offsetY);

	}

	/**
	 * associatePositionX set the posX of each Formation which satisfy
	 * uneFormation.getFullName() == myYear
	 * 
	 * @param someFormations
	 *            is a LinkedList of Formation
	 * @param myYear
	 *            is a year such as "L3" or "M1"
	 * @param decalage
	 */
	private void associatePositionX(LinkedList<Formation> someFormations, String myYear, int decalageX, int decalageY) {
		int i = 1;
		for (Formation aFormation : someFormations) {
			if (aFormation.getFullName().indexOf(myYear) != -1) {
				aFormation.setPosX(decalageX * i);
				aFormation.setPosY(decalageY);
				i++;
				System.out.println("associerOK : " + aFormation.getFullName());
			}
		}
	}

	private void getPlacement(LinkedList<Formation> someFormations) {
		for (Formation aFormation : someFormations) {
			System.out.println("Pour la formation " + aFormation.getFullName());
			System.out.println("PosX = " + aFormation.getPosX());
			System.out.println("PosY = " + aFormation.getPosY());
			System.out.println("_________________");
		}

	}

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

	public void paint(boolean affFormationLicence, boolean affFormationMaster, boolean affResponsable,
			boolean affMatieres, boolean affAdmission, boolean affSubject, boolean affTeacher) throws Exception {
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

		//this.defineObjectsPosition(this.data.getListOfFormations(), 1920, 1080);

		this.showAdmission(affAdmission);

		this.showFormation(affFormationLicence, affFormationMaster);

		this.showResponsable(affResponsable);
		this.showSubjectTeacher(affSubject, affTeacher);
		// The tag that the user selected (he wants to see what are the
		// formation that teaches this course)
		// String userSelectedTag = "Probas";
		// String userSelectedTags[] = { "Rugby", "ADD", "Espagnol" };

		// Ask the test to render into the SVG Graphics2D implementation.

		// Tag checking
		/*
		 * int cptTags = 0; for (Formation f : this.data.getListOfFormations())
		 * { cptTags = 0; for (String str : userSelectedTags) {
		 * 
		 * if (Arrays.asList(f.getTagslist()).contains(str)) { cptTags++;
		 * 
		 * if (cptTags == userSelectedTags.length) { g.setPaint(Color.red);
		 * g.drawString("(X)", f.getPosX() + 50, f.getPosY() + 20); } } } }
		 */

		// g.setPaint(Color.black);

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
	public void showFormation(boolean affFormationLicence, boolean affFormationMaster) throws ClassNotFoundException {
		String showOnly = "";
		if (affFormationLicence == true && affFormationMaster == true) {
			showOnly = "both";
		} else if (affFormationLicence == true && affFormationMaster == false) {
			showOnly = "Licence";
		} else if (affFormationLicence == false && affFormationMaster == true) {
			showOnly = "Master";
		}
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
			l.setShown(true);
			g.setPaint(Color.black);
			g.drawString(l.getFullNameWithLink(), l.getPosX(), l.getPosY());
			// write the name of formation
			Rectangle t = new Rectangle(l.getPosX() - 10, l.getPosY() - 20, l.getFullName().length() * 10, 25); // draw
			// rectangle
			g.draw(t);
			g.setPaint(Color.blue);
			for (Formation l2 : l.getListOfAvailableFormations()) {
				// draw the lines between the formation and the avalaible
				// formations
				g.drawLine(l.getPosX() + lineCENTER, l.getPosY() + lineYDOWN, l2.getPosX() + lineCENTER,
						l2.getPosY() + lineYUP);

			}

		}

	}

	/**
	 * This function shows the Admission of a "formation" (if this one is SHOWN
	 * in the SVG)
	 * 
	 * The admission is written in BLUE
	 * 
	 * @param admission
	 * 
	 */

	public void showAdmission(boolean admission) {

		if (admission == true) {

			for (Formation f : this.getData().getListOfFormations()) {
				if (f.isShown() == true) {
					g.setPaint(Color.blue);
					g.drawString(f.getAdmisssion(), f.getPosX() - 30, f.getPosY() - 30);
				}
			}
		}

	}

	/**
	 * This function shows the name of the responsable of a formation (if this
	 * one is SHOWN in the SVG)
	 * 
	 * The responsable name is written in GREEN
	 * 
	 * @param responsable
	 * 
	 */

	public void showResponsable(boolean reponsable) {

		if (reponsable == true) {

			g.setPaint(Color.green);

			for (Formation f : this.getData().getListOfFormations()) {
				if (f.isShown() == true) {
					if (f.hasGotATeacher(f) == true)
						g.drawString(f.getTeacher().getFullNameTeacher(),
								f.getPosX() - (g.getFontMetrics().stringWidth(f.getTeacher().getFullNameTeacher()) + 5),
								f.getPosY());
				}
			}

			g.setPaint(Color.black);

		}

	}

	/**
	 * This function shows the name of the subjects or teachers of a formation
	 * (if this one is SHOWN in the SVG)
	 * 
	 * If the subjects are not shown, teachers wont appear in the SVG also
	 * 
	 * Either we'll have both, or only subjects
	 * 
	 * The size of teachers is smaller and in RED
	 * 
	 * 
	 * @param subject
	 * @param teacher
	 * 
	 */

	public void showSubjectTeacher(boolean subject, boolean teacher) {

		if (subject == true) {
			int decY = 0;
			for (Formation f : this.getData().getListOfFormations()) {
				if (f.isShown() == true) {
					for (Subject s : f.getListOfSubjects()) {
						g.drawString(s.getTitle(), f.getPosX() + 100, f.getPosY() + decY);
						s.setPosX(f.getPosX() + 100);
						s.setPosY(f.getPosY() + decY);
						decY += 15;

					}
				}
			}

			if (teacher == true) {
				g.setPaint(Color.red);

				for (Formation f : this.getData().getListOfFormations()) {
					if (f.isShown() == true) {

						for (Subject s : f.getListOfSubjects()) {

							java.awt.Font font = new java.awt.Font("TimesRoman", 9, 9);
							g.setFont(font);
							g.drawString(s.getResponsible().getLastName(),
									s.getPosX() + (g.getFontMetrics().stringWidth(s.getTitle()) + 30), s.getPosY());
							decY += 15;

						}
					}

				}
			}

		}

	}
	
	
	public static void main(String[] args) throws Exception {
		LicenceSVGGen test = new LicenceSVGGen();
		test.paint(false, true,  true, true, true, true, true);

	}

}
package com.github.cocolollipop.mido_svg.svg_generator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.batik.svggen.SVGGeneratorContext;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.commons.io.IOUtils;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import com.github.cocolollipop.mido_svg.model.DataBase;
import com.github.cocolollipop.mido_svg.university.components.Formation;
import com.github.cocolollipop.mido_svg.university.components.Subject;

public class DrawerSVGGen {

	private DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	private DocumentBuilder db;
	private DOMImplementation domImpl;
	private String svgNS;
	private Document document;
	private SVGGeneratorContext ctx;
	private SVGGraphics2D g;
	private DataBase data;
	private Enum drawOnly;

	private enum DrawOnly {
		LICENCE, MASTER, BOTH
	}

	/**
	 * fillListOfFormationToShow method fills the listOfFormationToShow with
	 * objects from FormationList that are of type typeOfFormation
	 * 
	 * @param typeOfFormation
	 * @return
	 * @throws ClassNotFoundException
	 */
	public List<Formation> fillListOfFormationToShow(String type) throws ClassNotFoundException {
		List<Formation> listOfFormationToShow = new LinkedList<Formation>();

		for (Formation formation : this.data.getFormations()) {
			if (formation.getCategory().toString() == type)
				listOfFormationToShow.add(formation);
		}
		return listOfFormationToShow;
	}

	public void paint(Settings settings) throws Exception {
		String output = "./svg/outLicence.svg";

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

		// this.defineObjectsPosition(this.data.getFormations(), 1920, 1080);

		this.drawAdmission(settings);
		this.drawFormation(settings);
		this.drawResponsable(settings);
		this.drawSubjectTeacher(settings);
		data.getFormat().changeFormat(settings.getFormat());

		g.setSVGCanvasSize(new Dimension(data.getFormat().getCanevasX(), data.getFormat().getCanevasY()));
		g.drawString(this.data.getDepartment().getNomDepartement(), this.data.getDepartment().getX(),
				this.data.getDepartment().getY());

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
		 * g.drawString("(X)", f.getPoint().x + 50, f.getPoint().y + 20); } } }
		 * }
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
	public void drawFormation(Settings settings) throws ClassNotFoundException {
		if (settings.isHiddenLicence() == false && settings.isHiddenMaster() == false) {
			this.drawOnly = DrawOnly.BOTH;
		} else if (settings.isHiddenLicence() == false && settings.isHiddenMaster() == true) {
			this.drawOnly = DrawOnly.LICENCE;
		} else if (settings.isHiddenLicence() == true && settings.isHiddenMaster() == false) {
			this.drawOnly = DrawOnly.MASTER;
		}
		// Makes the line arrive in the center of the rectangle
		int lineCENTER = 50;
		// Makes the line go DOWN a little so the line is not on the text
		int lineYDOWN = 7;
		// Makes the line go UP a little so the line is noton the text
		int lineYUP = -20;
		List<Formation> listToDraw = new LinkedList();
		// showing only licence formations
		if (this.drawOnly == DrawOnly.LICENCE || this.drawOnly == DrawOnly.MASTER) {
			listToDraw.addAll(this.fillListOfFormationToShow(drawOnly.toString()));
		}
		if (this.drawOnly == DrawOnly.BOTH) {
			listToDraw = this.data.getFormations();
		}

		for (Formation l : listToDraw) {
			l.setShown(true);
			g.setPaint(Color.black);
			g.drawString(l.getFullNameWithLink(), l.getPoint().x, l.getPoint().y);
			// write the name of formation
			Rectangle t = new Rectangle(l.getPoint().x - 10, l.getPoint().y - 20, l.getFullName().length() * 10, 25); // draw
			// rectangle
			g.draw(t);
			g.setPaint(Color.blue);
			for (Formation l2 : l.getAvailableFormations()) {
				// draw the lines between the formation and the avalaible
				// formations
				g.drawLine(l.getPoint().x + lineCENTER, l.getPoint().y + lineYDOWN, l2.getPoint().x + lineCENTER,
						l2.getPoint().y + lineYUP);

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

	public void drawAdmission(Settings settings) {

		if (settings.isHiddenAdmission() == false) {

			for (Formation f : this.data.getFormations()) {
				if (f.isShown() == true) {
					g.setPaint(Color.blue);
					g.drawString(f.getAdmisssion(), f.getPoint().x - 30, f.getPoint().y - 30);
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

	public void drawResponsable(Settings settings) {

		if (settings.isHiddenResponsable() == false) {

			g.setPaint(Color.green);

			for (Formation f : this.data.getFormations()) {
				if (f.isShown() == true) {
					if (f.hasGotATeacher(f) == true)
						g.drawString(f.getTeacher().getFullNameTeacher(),
								f.getPoint().x
										- (g.getFontMetrics().stringWidth(f.getTeacher().getFullNameTeacher()) + 5),
								f.getPoint().y);
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

	public void drawSubjectTeacher(Settings settings) {

		if (settings.isHiddenSubject() == false) {
			int decY = 0;
			for (Formation f : this.data.getFormations()) {
				if (f.isShown() == true) {
					for (Subject s : f.getSubjects()) {
						g.drawString(s.getTitle(), f.getPoint().x + 100, f.getPoint().y + decY);
						s.setPosX(f.getPoint().x + 100);
						s.setPosY(f.getPoint().y + decY);
						decY += 15;

					}
				}
			}

			if (settings.isHiddenTeacher() == false) {
				g.setPaint(Color.red);

				for (Formation f : this.data.getFormations()) {
					if (f.isShown() == true) {

						for (Subject s : f.getSubjects()) {

							java.awt.Font font = new java.awt.Font("TimesRoman", 9, 9);
							g.setFont(font);
							g.drawString(s.getResponsible().getLastName(),
									s.getPoint().x + (g.getFontMetrics().stringWidth(s.getTitle()) + 30),
									s.getPoint().y);
							decY += 15;

						}
					}

				}
			}

		}

	}

}

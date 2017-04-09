package com.github.cocolollipop.svgGenerator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;

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

import java.io.BufferedReader;
import java.io.File;


/**
 * Based on https://xmlgraphics.apache.org/batik/using/svg-generator.html (with
 * minor modifications).
 *
 */
public class LicenceSVGGen {

	private LinkedList<Formation> formationList;
	int dimXCanvas = 1920;
	int dimYCanvas = 1080;
	int shiftX;
	int shiftY;

	/**
	 * readTagListL3MIAGE read a file entered as a paramater and
	 * return a table of String which contains each worlds of the file  
	 * 
	 * @param fileName
	 * @return tagsList
	 */
	
	public String[] readTagsList(String fileName){
		String chaine = "";
		
		try
		{
			InputStream ips= new FileInputStream(new File(fileName)); 
			InputStreamReader ipsr= new InputStreamReader(ips);
			BufferedReader br= new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				//System.out.println(ligne);
				chaine+=ligne+" ";
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}

		String[] tagsList = chaine.split(",");		
		return tagsList;
		
		}

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
		this.formationList = new LinkedList<Formation>();

		this.formationList.add(L3MIAGE);
		this.formationList.add(L3MIAGEApp);
		this.formationList.add(M1MIAGE);
		this.formationList.add(M1MIAGEApp);
		this.formationList.add(M2MIAGEIF);
		this.formationList.add(M2MIAGEID);
		this.formationList.add(M2MIAGESTIN);
		this.formationList.add(M2MIAGEIFApp);
		this.formationList.add(M2MIAGEIDApp);
		this.formationList.add(M2MIAGESTINApp);

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
		for (int i = 0; i < this.formationList.size(); i++) {
			System.out.println("Pour l\'annee" + formationList.get(i).getGrade() + formationList.get(i).getFullName() + " a "
					+ formationList.get(i).getListOfAvailableFormations().size());

			if (formationList.get(i).getListOfAvailableFormations().size() == 0) {
				System.out.println("Pas de formation accessible");
			}
			for (int j = 0; j < formationList.get(i).getListOfAvailableFormations().size(); j++) {
				System.out.println("Les formations accessibles sont:"
						+ formationList.get(i).getListOfAvailableFormations().get(j).getFullName());
			}
		}

		/**
		 * Number of licences by grade
		 */
		ArrayList<Integer> keyGrade = new ArrayList<Integer>();
		ArrayList<Integer> nbLicenceByGrade = new ArrayList<Integer>();

		for (Formation str : this.formationList) {
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
		
        // Fill the M2MIAGEID list of tags		

	    M2MIAGEID.setTagsList(this.readTagsList("M2MIAGEID.txt"));
	    
	    
	    // The tag that the user selected (he wants to see what are the formation that teaches this course)
	    String userSelectedTag = "Socio";
	    
		// Ask the test to render into the SVG Graphics2D implementation.
		g.setPaint(Color.black);
		int canevasX = 2480;
		int canevasY = 3508; // A4 size
		g.setSVGCanvasSize(new Dimension(canevasX, canevasY));
		g.drawString(MIDO.getNomDepartement(), MIDO.getX(), MIDO.getY());

		// Drawing of the objects

		for (Formation f : this.formationList)
			// g.drawString(str.getFullName(), str.getPosX(), str.getPosY());

			g.drawString(f.getFullNameWithLink(), f.getPosX(), f.getPosY());

		g.setPaint(Color.green);
		// g.setSVGCanvasSize(new Dimension(2,2));
		// Font myFont = new Font("Serif", Font.BOLD, 12);

		// g.fillRect(100, 50, 200, 100);

		String fullname = Cailloux.getLastName() + " " + Cailloux.getFirstName();
		g.drawString(fullname, Cailloux.getPosX(), Cailloux.getPosX());

		g.setPaint(Color.black);

		// Drawing of the lines linking the objects

		for (Formation f : this.formationList) {
			for (Formation f2 : f.getListOfAvailableFormations()) {
				g.drawLine(f.getPosX() + lineCENTER, f.getPosY() + lineYDOWN, f2.getPosX() + lineCENTER,
						f2.getPosY() + lineYUP);

			}
		}

		/* Dessiner des rectangles autour des formations initiales */

		for (Formation f : this.formationList) {
			Rectangle t = new Rectangle(f.getPosX() - 10, f.getPosY() - 20, f.getFullName().length() * 10, 25);
			g.draw(t);

		}
		
		// Tag checking
		if (userSelectedTag != null){
			for (Formation f : this.formationList) {
				if(Arrays.asList(f.getTagslist()).contains(userSelectedTag)){
						g.setPaint(Color.red);
						g.drawString("(X)", f.getPosX() + 50, f.getPosY() + 20);
						System.out.println("trofort");
						
					}
				}
			}

				
		
		
		// Printing the tag selected by the user
		g.drawString("Tags selected : " + userSelectedTag + " (X)", 20, 900);
		
		
		g.setPaint(Color.black);
		
			

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
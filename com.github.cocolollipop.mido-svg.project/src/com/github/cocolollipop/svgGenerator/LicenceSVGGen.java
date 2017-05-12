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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.batik.svggen.SVGGeneratorContext;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.commons.io.IOUtils;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import com.github.cocolollipop.GenerateAuto.Format;
import com.github.cocolollipop.univ.Department;
import com.github.cocolollipop.univ.Formation;
import com.github.cocolollipop.univ.Licence;
import com.github.cocolollipop.univ.Master;
import com.github.cocolollipop.univ.Subject;
import com.github.cocolollipop.univ.Teacher;

public class LicenceSVGGen {

	private LinkedList<Formation> formationList;
	private LinkedList<Licence> licenceList;
	private LinkedList<Master> masterList;
	private LinkedList<Subject> subjectList;
	private Format format = new Format();
	
	private DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	private DocumentBuilder db ;
	private DOMImplementation domImpl;
	private String svgNS;
	private Document document;
	private SVGGeneratorContext ctx;
	private SVGGraphics2D g;
	

	

	// ALGO
	public void definirPositionsSimple(LinkedList<Formation> lesFormations, int canvasX){

		/* On definit d'abord le decalage initial
		Pour cela, on va analyser le contenu de lesFormations
		On va compter le nombre de L1, L2,...

		 */
		
		int decalageX=0;
		int decalageY = 0;
		int decalageL2=0;
		int decalageL3=0;
		int nbL1=0;
		int nbL2=0;
		int nbL3=0;
		int nbM1=0;
		int nbM2=0;


		// on va d'abord compter le nombre de formation parmi la liste envoy�e
		nbL1 = compterFormation(lesFormations,"L1");
		nbL2 = compterFormation(lesFormations,"L2");
		nbL3 = compterFormation(lesFormations,"L3");
		nbM1 = compterFormation(lesFormations,"M1");
		nbM2 = compterFormation(lesFormations,"M2");


		/*
		 * On calcule le decalage en Y ; pour cela il suffit de compter le nombre de nbL1/nbL2 != 0
		 * POUR l'INSTANT, JAI FIXE A LARRACHE 
		 */


		/*
		 * Maintenant on calcule le decalage en X
		 */
		decalageX = canvasX/(nbL1+1);
		decalageY = 100; // A changer
		associerPositionX(lesFormations, "L1", decalageX,decalageY);

		decalageX = canvasX/(nbL2+1);
		decalageY = decalageY+200;
		associerPositionX(lesFormations, "L2", decalageX,decalageY);

		decalageX = canvasX/(nbL3+1);
		decalageY = decalageY+200;
		associerPositionX(lesFormations, "L3", decalageX,decalageY);

		decalageX = canvasX/(nbM1+1);
		decalageY = decalageY+200;
		associerPositionX(lesFormations, "M1", decalageX,decalageY);

		decalageX = canvasX/(nbM2+1);
		decalageY = decalageY+200;
		associerPositionX(lesFormations, "M2", decalageX,decalageY);

	}
	
	private void getPlacement(LinkedList<Formation> lesFormations) {
		for(Formation uneFormation : lesFormations){
			System.out.println("Pour la formation "+uneFormation.getFullName());
			System.out.println("PosX = "+uneFormation.getPosX());
			System.out.println("PosY = "+uneFormation.getPosY());
			System.out.println("_________________");
		}
		
	}
	
	/**
	 * compterFormation count the number of "myYear" in lesFormations.getFullName()
	 * 
	 * @param lesFormations is a LinkedList of Formation
	 * @param myYear is a year such as "L3" or "M1"
	 * @return an integer or a negative if myYear isn't in the List
	 */
	private int compterFormation(LinkedList<Formation> lesFormations, String myYear) {
		int nb = 0;
		for(Formation uneFormation : lesFormations){
			if(uneFormation.getFullName().indexOf(myYear) != -1){
				nb++;
			}
			
		}
		return nb;
	}
	
	/**
	 * associerPositionX set the posX of each Formation which satisfy uneFormation.getFullName() == myYear
	 * 
	 * @param lesFormations is a LinkedList of Formation
	 * @param myYear is a year such as "L3" or "M1"
	 * @param decalage
	 */
	private void associerPositionX(LinkedList<Formation> lesFormations, String myYear, int decalageX, int decalageY){
		int i = 1;
		for(Formation uneFormation : lesFormations){
			if(uneFormation.getFullName().indexOf(myYear) != -1){
				uneFormation.setPosX(decalageX*i);
				uneFormation.setPosY(decalageY);
				i++;
				System.out.println("associerOK : "+uneFormation.getFullName());
			}
		}

	}
	// FIN ALGO
	
	/**
	 * fillLicenceList method fills the licenceList with objects from
	 * FormationList that are of type Licence
	 * 
	 */

	public void fillLicenceList() {
		this.licenceList = new LinkedList<Licence>();

		for (Formation formation : formationList) {
			if (formation.getClass().getSimpleName().equals("Licence"))
				licenceList.add((Licence) formation);
		}
	}

	/**
	 * fillMasterList method fills the MasterList with objects from
	 * FormationList that are of type Master
	 * 
	 */

	public void fillMasterList() {
		this.masterList = new LinkedList<Master>();

		for (Formation formation : formationList) {
			if (formation.getClass().getSimpleName().equals("Master"))
				masterList.add((Master) formation);
		}
	}

	/**
	 * To have the number of child of each formation
	 */
	public void nbOfChildOfEachFormation() {

		for (int i = 0; i < this.formationList.size(); i++) {
			System.out.println("Pour l\'annee" + formationList.get(i).getGrade() + formationList.get(i).getFullName()
					+ " a " + formationList.get(i).getListOfAvailableFormations().size());

			if (formationList.get(i).getListOfAvailableFormations().size() == 0) {
				System.out.println("Pas de formation accessible");
			}
			for (int j = 0; j < formationList.get(i).getListOfAvailableFormations().size(); j++) {
				System.out.println("Les formations accessibles sont:"
						+ formationList.get(i).getListOfAvailableFormations().get(j).getFullName());
			}
		}
	}

	/**
	 * Number of licences by grade
	 */
	public void nbOfFormationByGrade() {

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
	}

	public void paint() throws Exception {
		String output = "outLicence.svg";
	
		db= dbf.newDocumentBuilder();
		
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

	
		/*************** OBJECTS CREATION ***************/

		Department MIDO = new Department("MIDO", 500, 20);

		MIDO.setTitle("MIDO");

		// Teachers
		Teacher Mayag = new Teacher("Mayag", "Brice ", 150, 70);
		Teacher Pigozzi = new Teacher("Pigozzi", "Gabriella ", 650, 70);
		Teacher Cailloux = new Teacher("Cailloux", "Ollivier ", 150, 150);

		// Subjects
		Subject proba = new Subject("Probabilités et Statistiques", Mayag, 3, 350, 70);
		Subject java = new Subject("POO Java", Cailloux, 3, 350, 85);
		Subject logique = new Subject("Logique", Pigozzi, 3, 350, 100);

		// L3MIAGE
		Licence L3MIAGE = new Licence("L3 MIAGE", 3, 250, 70);
		L3MIAGE.setTeacher(Mayag);
		L3MIAGE.setFullNameWithLink(
				"http://formations.dauphine.fr/offre/fr-FR/fiche/A3INF/programme//#FRUAI0750736TPRPRA3INF");
		L3MIAGE.setAdmisssion("selection sur dossier");

		// L3MIAGEApp
		Licence L3MIAGEApp = new Licence("L3 MIAGE App", 3, 750, 70);
		L3MIAGEApp.setTeacher(Pigozzi);
		L3MIAGEApp.setFullNameWithLink(
				"http://formations.dauphine.fr/offre/fr-FR/fiche/A3INF/programme//#FRUAI0750736TPRPRA3INFAPP");
		L3MIAGEApp.setAdmisssion("selection sur entretien");

		// M1MIAGE
		Master M1MIAGE = new Master("M1 MIAGE", 4, 250, 150);
		M1MIAGE.setTeacher(Cailloux);
		M1MIAGE.setFullNameWithLink(
				"http://formations.dauphine.fr/offre/fr-FR/fiche/A5STI/programme//#FRUAI0750736TPRPRA4MIAI");

		Master M1MIAGEApp = new Master("M1 MIAGE App", 4, 750, 150);

		Master M2MIAGEIF = new Master("M2 MIAGE IF", 5, 100, 300);
		Master M2MIAGEID = new Master("M2 MIAGE ID", 5, 250, 300);
		Master M2MIAGESTIN = new Master("M2 MIAGE STIN", 5, 400, 300);

		Master M2MIAGEIFApp = new Master("M2 MIAGE IF App", 5, 600, 300);
		Master M2MIAGEIDApp = new Master("M2 MIAGE ID App", 5, 750, 300);
		Master M2MIAGESTINApp = new Master("M2 MIAGE STIN App", 5, 900, 300);

		// List of formations
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
		
		definirPositionsSimple(this.formationList, 1920);

		
		L3MIAGE.addAvailableFormation(M1MIAGE);
		L3MIAGEApp.addAvailableFormation(M1MIAGEApp);
		M1MIAGE.addAvailableFormation(M2MIAGESTIN);
		M1MIAGE.addAvailableFormation(M2MIAGEIF);
		M1MIAGE.addAvailableFormation(M2MIAGEID);
		M1MIAGEApp.addAvailableFormation(M2MIAGESTINApp);
		M1MIAGEApp.addAvailableFormation(M2MIAGEIDApp);
		M1MIAGEApp.addAvailableFormation(M2MIAGEIFApp);

		// List of formations licence and master
		fillLicenceList();
		fillMasterList();

		// List of Subjects
		L3MIAGE.addSubjectsOfFormation(java);
		L3MIAGE.addSubjectsOfFormation(logique);
		L3MIAGE.addSubjectsOfFormation(proba);

		// Fill the M2MIAGEID list of tags
		L3MIAGE.setTagsList("L3MIAGE.txt");
		M2MIAGEIF.setTagsList("M2MIAGEIF.txt");
		M2MIAGEID.setTagsList("M2MIAGEID.txt");
		M2MIAGESTIN.setTagsList("M2MIAGESTIN.txt");
		
		//createTagList
		Licence LiccreateTagTest = new Licence("L3 Test Create Tag App", 3, 750, 70);
		ArrayList<String> listOfTags = new ArrayList();
		listOfTags.add("toto");
		listOfTags.add("hello");
		LiccreateTagTest.createTagList(listOfTags);
		System.out.println("passé");

		// The tag that the user selected (he wants to see what are the
		// formation that teaches this course)
		// String userSelectedTag = "Probas";
		String userSelectedTags[] = { "Rugby", "ADD", "Espagnol" };

		// Ask the test to render into the SVG Graphics2D implementation.

		g.setPaint(Color.black);
		format.changeFormat("A4"); // size A4
		g.setSVGCanvasSize(new Dimension(format.getCanevasX(), format.getCanevasY()));
		g.drawString(MIDO.getNomDepartement(), MIDO.getX(), MIDO.getY());

		this.show("both");
		this.ShowAdmission(true);
		this.ShowResponsable(true);
		this.ShowSubjectTeacher(false,true);

	
		// Tag checking
		int cptTags = 0;
		for (Formation f : this.formationList) {
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
	public String svgLinkable (String output) throws FileNotFoundException, IOException{
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
	 * @param lineCENTER 
	 * @param lineYDOWN 
	 * @param lineYUP 
	 */	
public void show(String showOnly){
	
	int lineCENTER = 50; 	// Makes the line arrive in the center of the rectangle
	int lineYDOWN = 7; 		// Makes the line go DOWN a little so the line is not on the text
	int lineYUP = -20; 		// Makes the line go UP a little so the line is not on the text

	
	// showing only licence formations
			if (showOnly == "licenceOnly") {

				for (Formation l : this.licenceList) {

					g.setPaint(Color.black);

					g.drawString(l.getFullNameWithLink(), l.getPosX(), l.getPosY()); 
					// write the name of formation
					Rectangle t = new Rectangle(l.getPosX() - 10, l.getPosY() - 20, l.getFullName().length() * 10, 25); // draw
																														// rectangle
					g.draw(t);

					g.setPaint(Color.blue);
					g.drawString(l.getAdmisssion(), l.getPosX() - 30, l.getPosY() - 30);
					g.setPaint(Color.black);

					for (Formation l2 : l.getListOfAvailableFormations()) { 
						// draw the lines between the formation and the avalaible formations
						g.drawLine(l.getPosX() + lineCENTER, l.getPosY() + lineYDOWN, l2.getPosX() + lineCENTER,
								l2.getPosY() + lineYUP);

					}

					for (Subject s : l.getListOfsubjects()) {
						g.drawString(s.getTitle(), s.getPosX(), s.getPosY());

					}

					g.setPaint(Color.red);

					for (Subject s : l.getListOfsubjects()) {
						java.awt.Font font = new java.awt.Font("TimesRoman", 10, 10);
						g.setFont(font);
						g.drawString(s.getResponsible().getLastName(), s.getPosX() + (s.getTitle().length() * 7),
								s.getPosY());
						java.awt.Font font1 = new java.awt.Font("TimesRoman", 12, 12);
						g.setFont(font1);

					}

				}

				g.setPaint(Color.green);
				for (Formation f : this.licenceList)
					g.drawString(f.getTeacher().getFullNameTeacher(), f.getTeacher().getPosX(), f.getTeacher().getPosY());

				g.setPaint(Color.black);

			}

			// showing only master formations

			else if (showOnly == "masterOnly") {
				for (Formation m : this.masterList) {
					g.setPaint(Color.black);

					g.drawString(m.getFullNameWithLink(), m.getPosX(), m.getPosY());
					Rectangle t = new Rectangle(m.getPosX() - 10, m.getPosY() - 20, m.getFullName().length() * 10, 25);
					g.draw(t);

					g.setPaint(Color.blue);
					g.drawString(m.getAdmisssion(), m.getPosX() - 30, m.getPosY() - 30);
					g.setPaint(Color.black);

					for (Formation l2 : m.getListOfAvailableFormations()) {
						g.drawLine(m.getPosX() + lineCENTER, m.getPosY() + lineYDOWN, l2.getPosX() + lineCENTER,
								l2.getPosY() + lineYUP);

					}

					for (Subject s : m.getListOfsubjects()) {
						g.drawString(s.getTitle(), s.getPosX(), s.getPosY());

					}

					g.setPaint(Color.red);

					for (Subject s : m.getListOfsubjects()) {
						java.awt.Font font = new java.awt.Font("TimesRoman", 10, 10);
						g.setFont(font);
						g.drawString(s.getResponsible().getLastName(), s.getPosX() + (s.getTitle().length() * 7),
								s.getPosY());
						java.awt.Font font1 = new java.awt.Font("TimesRoman", 12, 12);
						g.setFont(font1);

					}
				}

				g.setPaint(Color.green);
				for (Formation f : this.masterList)
					g.drawString(f.getTeacher().getFullNameTeacher(), f.getTeacher().getPosX(), f.getTeacher().getPosY());

				g.setPaint(Color.black);

			}

			// showing both master and licence formations

			else if (showOnly == "both") {
				for (Formation f : this.formationList) {
					g.setPaint(Color.black);

					g.drawString(f.getFullNameWithLink(), f.getPosX(), f.getPosY());
					Rectangle t = new Rectangle(f.getPosX() - 10, f.getPosY() - 20, f.getFullName().length() * 10, 25);
					g.draw(t);
					//g.setPaint(Color.blue);

					//g.drawString(f.getAdmisssion(), f.getPosX() - 30, f.getPosY() - 30);
					//g.setPaint(Color.black);

					for (Formation l2 : f.getListOfAvailableFormations()) {
						g.drawLine(f.getPosX() + lineCENTER, f.getPosY() + lineYDOWN, l2.getPosX() + lineCENTER,
								l2.getPosY() + lineYUP);

					}
					
					/*int decY=0;
					for (Subject s : f.getListOfsubjects()) {
						g.drawString(s.getTitle(), f.getPosX()+100, f.getPosY()+decY);
						s.setPosX(f.getPosX()+100);
						s.setPosY(f.getPosY()+decY);
						decY+=15;

					}*/

					/*g.setPaint(Color.red);

					for (Subject s : f.getListOfsubjects()) {
						java.awt.Font font = new java.awt.Font("TimesRoman", 10, 10);
						g.setFont(font);
						g.drawString(s.getResponsible().getLastName(), s.getPosX() + (s.getTitle().length() * 7),s.getPosY());
						java.awt.Font font1 = new java.awt.Font("TimesRoman", 12, 12);
						g.setFont(font1);

					}*/
					
					/*g.setPaint(Color.green);   
					for (Formation f : this.formationList)
						g.drawString(f.getTeacher().getFullNameTeacher(), f.getTeacher().getPosX(), f.getTeacher().getPosY());

					g.setPaint(Color.black);*/	


				}}
			}



/**
 * This function shows the Admission 
 * 
 * 		The admission is written in BLUE
 * 
 * */

public void ShowAdmission(boolean admission){ 
	
	if(admission==true){
	
	for (Formation f : this.formationList) {
		
	g.setPaint(Color.blue);
	g.drawString(f.getAdmisssion(), f.getPosX() - 30, f.getPosY() - 30);
	
		}
	}
		
}


/**
 * This function shows the name of the responsable of a formation
 * 
 * 		The responsable name is written in GREEN
 * 
 * */

public void ShowResponsable(boolean reponsable){
	
		if(reponsable==true){
			
			g.setPaint(Color.green); 
			
			for (Formation f : this.formationList){
				if(f.hasGotATeacher(f)==true) 
				g.drawString(f.getTeacher().getFullNameTeacher(), f.getPosX()-(g.getFontMetrics().stringWidth(f.getTeacher().getFullNameTeacher())+5), f.getPosY());
					
					}
			
				g.setPaint(Color.black);
				
			}
				
	}
	


/**
 * This function shows the name of the subjects or teachers of a formation
 * 
 * 	If the subjects are not shown, teachers wont appear in the SVG also
 * 
 * 		Either we'll have both, or only subjects
 * 
 * 			The size of teachers is smaller and in RED
 * 
 * */

public void ShowSubjectTeacher(boolean subject,boolean teacher){
	
	
	
	if(subject==true){
		int decY=0;
		for (Formation f : this.formationList) {
		for (Subject s : f.getListOfsubjects()) {
			g.drawString(s.getTitle(), f.getPosX()+100, f.getPosY()+decY);
			s.setPosX(f.getPosX()+100);
			s.setPosY(f.getPosY()+decY);
			decY+=15;

			}
		}
		
		
		if(teacher==true){
			g.setPaint(Color.red);
		
			for (Formation f : this.formationList) {
			for (Subject s : f.getListOfsubjects()) {
				
				java.awt.Font font = new java.awt.Font("TimesRoman", 9, 9);
				g.setFont(font);
				g.drawString(s.getResponsible().getLastName(), s.getPosX()+(g.getFontMetrics().stringWidth(s.getTitle())+30),s.getPosY());
				decY+=15;
				
				java.awt.Font font1 = new java.awt.Font("TimesRoman", 12, 12);
				g.setFont(font1);

				}
			
			}
		}
	
	}
	
}



	public static void main(String[] args) throws Exception {
		LicenceSVGGen test = new LicenceSVGGen();
		test.paint();

	}
}
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
import org.apache.batik.svggen.font.Font;
import org.apache.commons.io.IOUtils;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import com.github.cocolollipop.univ.Department;
import com.github.cocolollipop.univ.Formation;
import com.github.cocolollipop.univ.Licence;
import com.github.cocolollipop.univ.Master;
import com.github.cocolollipop.univ.Subject;
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
	private LinkedList<Licence> licenceList;
	private LinkedList<Master> masterList;
	private LinkedList<Subject> subjectList;


	String format="";
	int dimXCanvas = 1920;
	int dimYCanvas = 1080;
	int canevasX = 0;
	int canevasY = 0;
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
	 * fillLicenceList method fills the licenceList with objects from FormationList that are of type Licence
	 * 
	 */
	
	public void fillLicenceList(){
		this.licenceList = new LinkedList<Licence>();

		for (Formation formation: formationList){
			if(formation.getClass().getSimpleName().equals("Licence"))
				licenceList.add((Licence) formation);				
		}		
	}
	
	/** 
	 * fillMasterList method fills the MasterList with objects from FormationList that are of type Master
	 * 
	 */
	
	public void fillMasterList(){
		this.masterList = new LinkedList<Master>();

		for (Formation formation: formationList){
			if(formation.getClass().getSimpleName().equals("Master"))
				masterList.add((Master) formation);
		}		
	}
	
	
	
	// GETTERS

	public int getCanevasX() {
		return canevasX;
	}

	public int getCanevasY() {
		return canevasY;
	}

	// SETTERS

	public void setCanevasX(int canevasX) {
		this.canevasX = canevasX;
	}


	public void setCanevasY(int canevasY) {
		this.canevasY = canevasY;
	}


	
	/** 
	 * changeFormat method change the format of the canevas to A3 or A4 and throws an exception if it's neither A4 nor A3
	 * @param format would be equal to A3/a3 or A4/a4
	 * @throws Exception
	 */
	
	void changeFormat(String format) throws Exception{
		
		 if(format=="A4"|| format=="a4"){
			 setCanevasX(2480);
			 setCanevasY(3508);
		 }
		 
		 else if (format=="A3" || format=="a3"){
			 setCanevasX(3508); //3508
			 setCanevasY(4961); //4961
		 }
		 else throw new Exception("This size isn't availaible, please choose between A3 or A4");
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

	public void paint() throws Exception {
		
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

		/*************** OBJECTS CREATION ***************/

		Department MIDO = new Department("MIDO", 500, 20);

		MIDO.setTitle("MIDO");
		
		//Teachers
		Teacher Mayag = new Teacher("Mayag","Brice ",150,70);
		Teacher Pigozzi = new Teacher("Pigozzi","Gabriella ",650, 70);
		Teacher Cailloux = new Teacher("Cailloux","Ollivier ",150, 150);

		
		// Subjects

		Subject proba = new Subject("Probabilités et Statistiques", Mayag, 3, 350, 70);
		Subject java = new Subject("POO Java", Cailloux, 3, 350, 85);
		Subject logique = new Subject("Logique", Pigozzi, 3, 350, 100);

		
		//L3MIAGE

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
		
		//M1MIAGE
		
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
		L3MIAGE.setTagsList(this.readTagsList("L3MIAGE.txt"));
		M2MIAGEIF.setTagsList(this.readTagsList("M2MIAGEIF.txt"));
		M2MIAGEID.setTagsList(this.readTagsList("M2MIAGEID.txt"));
	    M2MIAGESTIN.setTagsList(this.readTagsList("M2MIAGESTIN.txt"));
	    
	    
	    // The tag that the user selected (he wants to see what are the formation that teaches this course)
	    
	    //String userSelectedTag = "Probas";
	    String userSelectedTags[] = {"Logique"};
	    
		// Ask the test to render into the SVG Graphics2D implementation.
	    
		g.setPaint(Color.black);
		changeFormat("A4"); // size A4 
		g.setSVGCanvasSize(new Dimension(canevasX, canevasY));
		g.drawString(MIDO.getNomDepartement(), MIDO.getX(), MIDO.getY());

		
		/**
		 * Drawing of the objects
		 *the user has the choice between showing all "formations" or only "licence" or master 
		 *for that he has to change the @param showOnly to (licenceOnly, masterOnly, both)
		 *then the rectangles arround the "formations" are drawn, lines also 
		*/
		
		String showOnly="both";
		
		// showing only licence formations
		
		if(showOnly=="licenceOnly"){

			for (Formation l : this.licenceList){			
				
				g.setPaint(Color.black);

				g.drawString(l.getFullNameWithLink(), l.getPosX(), l.getPosY()); // write the name of formation
				Rectangle t = new Rectangle(l.getPosX() - 10, l.getPosY() - 20, l.getFullName().length() * 10, 25); // draw rectangle
				g.draw(t);
				
				g.setPaint(Color.blue);
				g.drawString(l.getAdmisssion(), l.getPosX()-30, l.getPosY()-30);
				g.setPaint(Color.black);
				
				for (Formation l2 : l.getListOfAvailableFormations()) { // draw the lines between the formation and the avalaible formations
				g.drawLine(l.getPosX() + lineCENTER, l.getPosY() + lineYDOWN, l2.getPosX() + lineCENTER,
						l2.getPosY() + lineYUP);

			}
				
			for (Subject s : l.getListOfsubjects()) {
					g.drawString(s.getTitle(),s.getPosX(),s.getPosY());
								
			}
			
			g.setPaint(Color.red);

			for (Subject s : l.getListOfsubjects()) {	
				java.awt.Font font = new java.awt.Font("TimesRoman", 10, 10);
				g.setFont(font);
				g.drawString(s.getResponsible().getLastName(), s.getPosX()+(s.getTitle().length()*7), s.getPosY());
				java.awt.Font font1 = new java.awt.Font("TimesRoman", 12, 12);
				g.setFont(font1);
			
		}
				
			}
			
			g.setPaint(Color.green);
				for(Formation f:this.licenceList)
				g.drawString(f.getTeacher().getFullNameTeacher(), f.getTeacher().getPosX(), f.getTeacher().getPosY());
				
				g.setPaint(Color.black);

			
		}
		
		// showing only master formations 
		
		else if(showOnly=="masterOnly"){			
			for (Formation  m : this.masterList){
				g.setPaint(Color.black);

				g.drawString(m.getFullNameWithLink(), m.getPosX(), m.getPosY());
				Rectangle t = new Rectangle(m.getPosX() - 10, m.getPosY() - 20, m.getFullName().length() * 10, 25);
				g.draw(t);
				
				g.setPaint(Color.blue);
				g.drawString(m.getAdmisssion(), m.getPosX()-30, m.getPosY()-30);
				g.setPaint(Color.black);
				
				for (Formation l2 : m.getListOfAvailableFormations()) {
					g.drawLine(m.getPosX() + lineCENTER, m.getPosY() + lineYDOWN, l2.getPosX() + lineCENTER,
							l2.getPosY() + lineYUP);

				}
				
				for (Subject s : m.getListOfsubjects()) {
					g.drawString(s.getTitle(),s.getPosX(),s.getPosY());
								
			}
				
				g.setPaint(Color.red);

				for (Subject s : m.getListOfsubjects()) {	
					java.awt.Font font = new java.awt.Font("TimesRoman", 10, 10);
					g.setFont(font);
					g.drawString(s.getResponsible().getLastName(), s.getPosX()+(s.getTitle().length()*7), s.getPosY());
					java.awt.Font font1 = new java.awt.Font("TimesRoman", 12, 12);
					g.setFont(font1);
				
			}
			}
			
			g.setPaint(Color.green);
			for(Formation f:this.masterList)
			g.drawString(f.getTeacher().getFullNameTeacher(), f.getTeacher().getPosX(), f.getTeacher().getPosY());
			
			g.setPaint(Color.black);

			
		}
			
		// showing both master and licence formations
		
		else if(showOnly=="both"){	
			for (Formation f : this.formationList){
				g.setPaint(Color.black);

				g.drawString(f.getFullNameWithLink(), f.getPosX(), f.getPosY());	
				Rectangle t = new Rectangle(f.getPosX() - 10, f.getPosY() - 20, f.getFullName().length() * 10, 25);
				g.draw(t);
				g.setPaint(Color.blue);

				g.drawString(f.getAdmisssion(), f.getPosX()-30, f.getPosY()-30);
				g.setPaint(Color.black);

				for (Formation l2 : f.getListOfAvailableFormations()) {
					g.drawLine(f.getPosX() + lineCENTER, f.getPosY() + lineYDOWN, l2.getPosX() + lineCENTER,
							l2.getPosY() + lineYUP);

				}
				
				for (Subject s : f.getListOfsubjects()) {
					g.drawString(s.getTitle(),s.getPosX(),s.getPosY());
								
			}
				
				g.setPaint(Color.red);

				for (Subject s : f.getListOfsubjects()) {	
					java.awt.Font font = new java.awt.Font("TimesRoman", 10, 10);
					g.setFont(font);
					g.drawString(s.getResponsible().getLastName(), s.getPosX()+(s.getTitle().length()*7), s.getPosY());
					java.awt.Font font1 = new java.awt.Font("TimesRoman", 12, 12);
					g.setFont(font1);

				
			}
				
			}
			
			g.setPaint(Color.green);
			for(Formation f:this.formationList)
			g.drawString(f.getTeacher().getFullNameTeacher(), f.getTeacher().getPosX(), f.getTeacher().getPosY());
			
			g.setPaint(Color.black);

		}
	
		
		
		
		
		// Tag checking
		
		for (String str : userSelectedTags){
			for (Formation f : this.formationList) {
				if(Arrays.asList(f.getTagslist()).contains(str)){
						g.setPaint(Color.red);
						g.drawString("(X)", f.getPosX() + 50, f.getPosY() + 20);						
					}
				}
			}
		
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

	
	// main function 
	
	public static void main(String[] args) throws Exception {
		LicenceSVGGen test = new LicenceSVGGen();
		test.paint();
		
	}
}
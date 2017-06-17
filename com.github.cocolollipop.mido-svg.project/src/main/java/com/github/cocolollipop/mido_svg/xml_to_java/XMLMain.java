package com.github.cocolollipop.mido_svg.xml_to_java;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.github.cocolollipop.mido_svg.university.components.*;

public class XMLMain {
	/**
	 * myXMLDocument contient après appel de getXMLFile() le contenu du fichier XML
	 */
	private Document myXMLDocument;
	
	public Document getMyXMLDocument() {
		return myXMLDocument;
	}

	/**
	 * getXMLFile() permet de rapatrier le fichier XML contenant les données sur les formation, matières, prof. Le contenu est stocké dans l'objet XMLMain.document.
	 * Si on veut travailler avec le contenu du fichier, on passera par l'attribut myXMLDocument de la classe
	 * Le contenu est deja parsé
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public void getXMLFile() throws ParserConfigurationException, SAXException, IOException {
		// D'abord on pointe sur l'URL
		URL myXML = new URL("https://raw.githubusercontent.com/oliviercailloux/projets/master/Voeux/OF_MEA5STI.xml");

		// Ensuite on parse
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document document = dBuilder.parse(myXML.openStream());

		// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		document.getDocumentElement().normalize();
		this.myXMLDocument = document;
	}
	
	/**
	 * fillSubjectsXML permet remplir la hashmap transmise en parametre, en lui ajoutant la liste de toutes les matières trouvées dans l'attribut myXMLDocument
	 * @param mapSubjects
	 * @param document
	 */
	public void fillSubjectsXML(HashMap mapSubjects){
		
	    final Element racine = this.myXMLDocument.getDocumentElement();
	    final NodeList racineNoeuds = racine.getChildNodes();
	    final int nbRacineNoeuds = racineNoeuds.getLength();
			
	    for (int i = 0; i<nbRacineNoeuds; i++) {
	    	
	    	
	        if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
	        	
	        	final Element subject = (Element) racineNoeuds.item(i);
	            
	            //Looking for subjects
	            if(subject.getNodeName() == "ns3:course"){
	            	String courseTitle = "courseTitleBlank";
			    	double courseCredit=0;
			    	
			    	// we get the name of the course here
	            	courseTitle = subject.getChildNodes().item(5).getFirstChild().getNodeValue();
	            	
	            	// we get ECTS of the course
	            	if (subject.getChildNodes().item(23).getAttributes().getNamedItem("ECTScredits").getNodeValue() != null){
	            		courseCredit = Double.parseDouble(subject.getChildNodes().item(23).getAttributes().getNamedItem("ECTScredits").getNodeValue());
	            	}
	            	// we create a new subject
	            	Subject sub = new Subject(courseTitle, courseCredit);
	            	mapSubjects.put(sub.getTitle(), sub); // mapSubjects correspond à la hashmap ; on la passera en parametre au tt debut de la fonction
	            }
	        }
	    }

		
		
	}
	public void fillTeachersXML(HashMap mapTeachers){
    
	final Element racine = this.myXMLDocument.getDocumentElement();
	
	//Affichage de l'élément racine
	System.out.println("\n*************RACINE************");
	System.out.println(racine.getNodeName());
	
	/*
	 * Etape 5 : récupération des matières
	 */
	final NodeList racineNoeuds = racine.getChildNodes();
	final int nbRacineNoeuds = racineNoeuds.getLength();
		
	for (int i = 0; i<nbRacineNoeuds; i++) {
	    if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
	        final Element teacher = (Element) racineNoeuds.item(i);
	        String fname = "Jules";
	        String lname = "Scohy";
	        String mail = "a@a.fr";
	        String tel = "000";
	        //Looking for subjects
	        if(teacher.getNodeName() == "ns3:person"){
	        	fname = teacher.getElementsByTagName("ns3:given").item(0).getFirstChild().getNodeValue();
	        	lname = teacher.getElementsByTagName("ns3:family").item(0).getFirstChild().getNodeValue();
	        	mail = teacher.getElementsByTagName("ns3:email").item(0).getFirstChild().getNodeValue();
	        	tel = teacher.getElementsByTagName("ns3:telephone").item(0).getTextContent();
	        	}
	        Teacher tch = new Teacher();
	        tch.setFirstName(fname);
	        tch.setLastName(lname);
	        tch.setAddress(mail);
	        tch.setPhone(tel);
	        }	
	    }				
} // fin for	FIN COPIE ICI	
	public void fillFormationsXML(HashMap mapFormations){
	 
	    final Element racine = this.myXMLDocument.getDocumentElement();
		
	    final NodeList racineNoeuds = racine.getChildNodes();
	    final int nbRacineNoeuds = racineNoeuds.getLength();
			
	    for (int i = 0; i<nbRacineNoeuds; i++) {
	        if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
	            final Element program = (Element) racineNoeuds.item(i);
	            
	            //Looking for subjects
	            if(program.getNodeName() == "ns3:program"){
	            	// on recupère si ça vérifie L1 L2 L3 M1 ou M2
	            	String intitule = program.getElementsByTagName("ns2:text").item(0).getTextContent();
	            	if(intitule.matches("[L][123]\\s.*|[M][12]\\s.*")){
	            		switch(intitule.substring(0,1)){
	            		
	            		case "L1":
	            			Licence L1 = new Licence(intitule, 1, 0, 0);
	            			mapFormations.put(intitule, L1);
	            		break;
	            				            		
	            		case "L2":
	            			Licence L2 = new Licence(intitule, 2, 0, 0);
	            			mapFormations.put(intitule, L2);
	            	    break;
	            	    
	            		case "L3":
	            			Licence L3 = new Licence(intitule, 3, 0, 0);
	            			mapFormations.put(intitule, L3);
	            		break;
	            		
	            		case "M1":
	            			Master M1 = new Master(intitule, 1, 0, 0);
	            			mapFormations.put(intitule, M1);
	            		break;
	            			
	            		case "M2":
	            			Master M2 = new Master(intitule, 2, 0, 0);
	            			mapFormations.put(intitule, M2);
	            	    break;
	            	    
	            	    default:
	            	    	System.out.println("erreur import formations");
	            	    break;
	            			
	            			
	            			
	            		
	            		
	            		
	    	            	}
	            	}
	            	
	            }	
	        }				
	    }
	}
	
	
    public static void main(final String[] args) throws ParserConfigurationException, SAXException, IOException {
			XMLMain myTestXMLMain = new XMLMain();
			myTestXMLMain.getXMLFile();
	    
	    final Element racine = myTestXMLMain.myXMLDocument.getDocumentElement();
		
	    //Affichage de l'élément racine
	    System.out.println("\n*************RACINE************");
	    System.out.println(racine.getNodeName());
		
	    /*
	     * Etape 5 : récupération des matières
	     */
	    final NodeList racineNoeuds = racine.getChildNodes();
	    final int nbRacineNoeuds = racineNoeuds.getLength();
			
	    for (int i = 0; i<nbRacineNoeuds; i++) {
	    	
	    	
	        if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
	        	
	        	final Element subject = (Element) racineNoeuds.item(i);
	            
	            //Looking for subjects
	            if(subject.getNodeName() == "ns3:course"){
	            	String courseTitle = "courseTitleBlank";
			    	double courseCredit=0;
			    	
			    	// we get the name of the course here
	            	courseTitle = subject.getChildNodes().item(5).getFirstChild().getNodeValue();
	            	
	            	// we get ECTS of the course
	            	if (subject.getChildNodes().item(23).getAttributes().getNamedItem("ECTScredits").getNodeValue() != null){
	            		courseCredit = Double.parseDouble(subject.getChildNodes().item(23).getAttributes().getNamedItem("ECTScredits").getNodeValue());
	            	}
	            	Subject sub = new Subject(courseTitle, courseCredit);
	            	//mapSubject.put(sub.getTitle(), sub); // mapSubject correspond à la hashmap ; on la passera en parametre au tt debut de la fonction
	            }
	        }
	        
	        
	    }	
	    
    }
}

package com.github.cocolollipop.mido_svg.xml_to_java;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.github.cocolollipop.mido_svg.university.components.Subject;

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
	            	Subject sub = new Subject(courseTitle, courseCredit);
	            	mapSubjects.put(sub.getTitle(), sub); // mapSubjects correspond à la hashmap ; on la passera en parametre au tt debut de la fonction
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
